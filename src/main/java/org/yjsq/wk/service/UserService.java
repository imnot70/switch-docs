package org.yjsq.wk.service;

import org.yjsq.wk.bean.param.AddUserParam;
import org.yjsq.wk.bean.param.LoginParam;
import org.yjsq.wk.bean.param.UpdatePwdParam;
import org.yjsq.wk.bean.param.UserListParam;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.bean.vo.LoginVo;
import org.yjsq.wk.bean.vo.UserVo;
import org.yjsq.wk.common.Result;

public interface UserService {

    Result<String> addUser(AddUserParam param);

    Result<Boolean> updatePwd(String token, UpdatePwdParam param);

    Result<LoginVo> login(LoginParam param);

    Result<Boolean> logout(String token);

    Result<UserVo> infoByToken(String token);

    Result<ListVo<UserVo>> list(UserListParam param);
}
