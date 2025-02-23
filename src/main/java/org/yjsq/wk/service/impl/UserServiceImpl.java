package org.yjsq.wk.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yjsq.wk.bean.entity.User;
import org.yjsq.wk.bean.param.AddUserParam;
import org.yjsq.wk.bean.param.LoginParam;
import org.yjsq.wk.bean.param.UpdatePwdParam;
import org.yjsq.wk.bean.param.UserListParam;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.bean.vo.LoginVo;
import org.yjsq.wk.bean.vo.UserVo;
import org.yjsq.wk.common.CommonCode;
import org.yjsq.wk.common.Constants;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.common.StringConstant;
import org.yjsq.wk.exception.BizException;
import org.yjsq.wk.mapper.UserMapper;
import org.yjsq.wk.service.UserService;
import org.yjsq.wk.utils.Cache;
import org.yjsq.wk.utils.SecurityUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Value("${system.login.cache}")
    private Integer expiryTime;

    @Override
    public Result<String> addUser(AddUserParam param) {
        checkAddParam(param);

        User user = null;
        if (!StringUtils.isEmpty(param.getEmail())) {
            user = userMapper.findUserByEmail(param.getEmail());
        }
        if (user != null) {
            throw new BizException(CommonCode.USER_EXIST);
        }
        if (!StringUtils.isEmpty(param.getLoginName())) {
            user = userMapper.findUserByLoginName(param.getLoginName());
        }
        if (user != null) {
            throw new BizException(CommonCode.USER_EXIST);
        }

        user = new User();
        user.setLoginName(param.getLoginName());
        user.setEmail(param.getEmail());
        user.setPhone(param.getPhone());
        user.setGender(param.getGender());
        user.setAvatar(param.getAvatar());
        user.setSource(User.SOURCE_ADD);
        user.setStatus(User.STATUS_NORMAL);
        user.setCreateTime(new Date());
        user.setCreateBy("admin");
        String pwd = StringConstant.DEFAULT_PWD_MD5;
        long id = IdUtil.getSnowflake().nextId();
        user.setPassword(pwd);
        user.setId(id);
        log.info("addUser, pwd:{}, id:{}", pwd, id);
        userMapper.insert(user);
        return Result.success(String.valueOf(id));
    }

    private void checkAddParam(AddUserParam param) {
        if (StringUtils.isEmpty(param.getLoginName()) && StringUtils.isEmpty(param.getEmail())) {
            throw new BizException(CommonCode.PARAM_IS_NULL.getCode(), "邮箱和用户名不可都为空值");
        }
    }

    @Override
    public Result<Boolean> updatePwd(String token, UpdatePwdParam param) {
        checkUpdatePwdParam(param);
        User userInfo = Cache.getUserInfo(token);
        Long id = SecurityUtil.getIdFromToken(token);
        if (userInfo == null) {
            log.info("updatePwd, id:{}", id);
            userInfo = userMapper.findUserById(id);
        }
        if (userInfo == null) {
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        if (!userInfo.getId().equals(id)) {
            throw new BizException(CommonCode.PERMISSION_DENIED);
        }

        String oldPwdStr = SecurityUtil.createPwd(param.getOldPwd());
        if (!userInfo.getPassword().equals(oldPwdStr)) {
            throw new BizException(CommonCode.OLD_PWD_WRONG);
        }
        String newPwdStr = SecurityUtil.createPwd(param.getNewPwd());
        if (newPwdStr.equals(oldPwdStr)) {
            throw new BizException(CommonCode.TWO_PWD_WRONG);
        }
        userMapper.updatePassword(newPwdStr, id);
        return Result.success(true);
    }

    private void checkUpdatePwdParam(UpdatePwdParam param) {
        if (StringUtils.isEmpty(param.getOldPwd())) {
            throw new BizException(CommonCode.OLD_PWD_EMPTY);
        }
        if (StringUtils.isEmpty(param.getNewPwd())) {
            throw new BizException(CommonCode.NEW_PWD_EMPTY);
        }
    }

    @Override
    public Result<LoginVo> login(LoginParam param) {
        checkLoginParam(param);
        User user = userMapper.findUserByEmail(param.getEmail());
        if (user == null) {
            user = userMapper.findUserByLoginName(param.getLoginName());
        }
        if (user == null) {
            log.info("login, user is null, email:{}, loginName:{}", param.getEmail(), param.getLoginName());
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        checkUserStatus(user);
        String token = SecurityUtil.createToken(String.valueOf(user.getId()));
        Cache.cacheUser(token, user, expiryTime * 60 * 60 * 1000);
        return Result.success(new LoginVo(token));
    }

    private void checkUserStatus(User user) {
        if (User.STATUS_BLOCKED.equals(user.getStatus())) {
            throw new BizException(CommonCode.USER_BLOCKED);
        }
    }

    private void checkLoginParam(LoginParam param) {
        if (StringUtils.isEmpty(param.getPwd())) {
            throw new BizException(CommonCode.PARAM_IS_NULL.getCode(), "密码不可为空");
        }
        if (StringUtils.isEmpty(param.getEmail()) && StringUtils.isEmpty(param.getLoginName())) {
            throw new BizException(CommonCode.PARAM_IS_NULL.getCode(), "邮箱和用户名不可都为空值");
        }
    }

    @Override
    public Result<Boolean> logout(String token) {
        Cache.remove(token);
        return Result.success(true);
    }

    @Override
    public Result<UserVo> infoByToken(String token) {
        User userInfo = Cache.getUserInfo(token);
        if (userInfo == null) {
            throw new BizException(CommonCode.DATA_NOT_FOUNT);
        }
        UserVo userVo = new UserVo(userInfo);
        // 是否需要重置密码
        userVo.setResetPwd(StringConstant.DEFAULT_PWD_MD5.equals(userInfo.getPassword()));
        return Result.success(userVo);
    }

    @Override
    public Result<ListVo<UserVo>> list(UserListParam param) {
        Integer pageNum = param.getPageNum() == null || param.getPageNum() <= 0 ? Constants.PAGE_NUM : param.getPageNum();
        Integer pageSize = param.getPageSize() == null || param.getPageSize() <= 0 ? Constants.PAGE_SIZE : param.getPageSize();
        Integer startNum = (pageNum - 1) * pageSize;
        List<User> users = userMapper.selectList(startNum, pageSize);
        if(users == null || users.isEmpty()){
            ListVo<UserVo> list = new ListVo<>();
            list.setTotalCount(0);
            list.setList(new ArrayList<>());
            return Result.success(list);
        }
        List<UserVo> list = new ArrayList<>(users.size());
        for (User user : users) {
            list.add(new UserVo(user));
        }
        ListVo<UserVo> result = new ListVo<>();
        result.setList(list);
        result.setTotalCount(userMapper.selectCount());
        return Result.success(result);
    }
}
