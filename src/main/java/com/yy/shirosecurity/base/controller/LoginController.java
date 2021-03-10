package com.yy.shirosecurity.base.controller;

import com.yy.shirosecurity.base.constant.Result;
import com.yy.shirosecurity.base.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public Result login(String username,String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.failed("参数错误");
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
            username,password
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return Result.failed("用户名不存在");
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return Result.failed("账号或密码错误");
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return Result.failed("没有权限");
        }
        return Result.success();
    }

    @GetMapping("/error")
    public Result error(){
        return Result.failed("未授权");
    }
}
