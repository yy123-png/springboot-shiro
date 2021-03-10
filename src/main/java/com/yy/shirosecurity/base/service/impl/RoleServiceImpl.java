package com.yy.shirosecurity.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.shirosecurity.base.entity.Role;
import com.yy.shirosecurity.base.entity.User;
import com.yy.shirosecurity.base.entity.UserRole;
import com.yy.shirosecurity.base.mapper.RoleMapper;
import com.yy.shirosecurity.base.mapper.UserMapper;
import com.yy.shirosecurity.base.mapper.UserRoleMapper;
import com.yy.shirosecurity.base.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Set<Role> getRolesByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> userRoleWrapper = new QueryWrapper<UserRole>().lambda();
        userRoleWrapper
                .eq(!ObjectUtils.isEmpty(userId),UserRole::getUserId,userId);
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleWrapper);
        Set<Long> roleIdSet = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        Set<Role> result = new HashSet<>();
        for (Long roleId : roleIdSet) {
            Role role = roleMapper.selectById(roleId);
            result.add(role);
        }
        return result;
    }

}
