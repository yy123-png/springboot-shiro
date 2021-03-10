package com.yy.shirosecurity.base.service.impl;

import com.yy.shirosecurity.base.entity.Permission;
import com.yy.shirosecurity.base.entity.Role;
import com.yy.shirosecurity.base.entity.User;
import com.yy.shirosecurity.base.entity.UserRole;
import com.yy.shirosecurity.base.mapper.UserMapper;
import com.yy.shirosecurity.base.mapper.UserRoleMapper;
import com.yy.shirosecurity.base.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createEmployee(User user) {
        user.setCreateBy(SecurityUtils.getSubject().getPrincipal().toString());
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(3L);
        userRole.setCreateBy(SecurityUtils.getSubject().getPrincipal().toString());
        userRole.setCreateTime(LocalDateTime.now());
        userRoleMapper.insert(userRole);
    }
}
