package org.yjsq.wk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yjsq.wk.bean.param.AddUserParam;
import org.yjsq.wk.bean.param.LoginParam;
import org.yjsq.wk.bean.param.UpdatePwdParam;
import org.yjsq.wk.bean.param.UserListParam;
import org.yjsq.wk.bean.vo.ListVo;
import org.yjsq.wk.bean.vo.LoginVo;
import org.yjsq.wk.bean.vo.UserVo;
import org.yjsq.wk.common.Result;
import org.yjsq.wk.service.UserService;

/**
 * TODO 权限还没做
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public Result<String> add(@RequestBody AddUserParam param){
        return userService.addUser(param);
    }

    @PostMapping("login")
    public Result<LoginVo> login(@RequestBody LoginParam param){
        return userService.login(param);
    }

    @PostMapping("pwd/update")
    public Result<Boolean> updatePwd(@RequestBody UpdatePwdParam param){
        return userService.updatePwd(getToken(), param);
    }

    @PostMapping("logout")
    public Result<Boolean> logout(){
        return userService.logout(getToken());
    }

    @PostMapping("info")
    public Result<UserVo> info(){
        return userService.infoByToken(getToken());
    }

    @PostMapping("list")
    public Result<ListVo<UserVo>> list(UserListParam param){
        return userService.list(param);
    }

    // TODO 封禁，解封，重置密码……
}
