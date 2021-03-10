package com.yy.shirosecurity.base.controller;


import com.yy.shirosecurity.base.constant.Result;
import com.yy.shirosecurity.base.entity.User;
import com.yy.shirosecurity.base.service.IRoleService;
import com.yy.shirosecurity.base.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;



    @PostMapping("/create/employee")
    @RequiresRoles("admin")
    @RequiresPermissions("modify")
    public Result create(@RequestBody User user){
        userService.createEmployee(user);
        return Result.success();
    }

    @GetMapping("/all")
    @RequiresPermissions("query")
    public Result all(){
        List<User> result = userService.list();
        return Result.success(result);
    }
}

