package com.yy.shirosecurity.shiro;


import com.yy.shirosecurity.base.entity.Permission;
import com.yy.shirosecurity.base.entity.Role;
import com.yy.shirosecurity.base.entity.User;
import com.yy.shirosecurity.base.service.IPermissionService;
import com.yy.shirosecurity.base.service.IRoleService;
import com.yy.shirosecurity.base.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class CustomRealm extends AuthorizingRealm {
    @Resource
    protected IUserService userService;
    @Resource
    protected IRoleService roleService;
    @Resource
    protected IPermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户名称
        User user = userService.getByUsername(name);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        user.setRoleSet(roleService.getRolesByUserId(user.getId()));
        for (Role role : user.getRoleSet()) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            // 添加权限
            role.setPermissionSet(permissionService.getPermissionSetByRoleId(role.getId()));
            for (Permission permission : role.getPermissionSet()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(StringUtils.isEmpty(authenticationToken.getPrincipal())){
            return null;
        }
        // 获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = userService.getByUsername(name);
        if(user == null){
            return null;
        }else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(name, user.getPassword(), getName());
        }
    }
}
