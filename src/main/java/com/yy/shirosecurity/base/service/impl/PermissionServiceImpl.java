package com.yy.shirosecurity.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yy.shirosecurity.base.entity.Permission;
import com.yy.shirosecurity.base.entity.RolePermission;
import com.yy.shirosecurity.base.mapper.PermissionMapper;
import com.yy.shirosecurity.base.mapper.RolePermissionMapper;
import com.yy.shirosecurity.base.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Set<Permission> getPermissionSetByRoleId(Long roleId) {
        LambdaQueryWrapper<RolePermission> rolePermissionWrapper = new QueryWrapper<RolePermission>().lambda();
        rolePermissionWrapper
                .eq(!ObjectUtils.isEmpty(roleId),RolePermission::getRoleId,roleId);

        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionWrapper);
        Set<Long> permissionIdSet = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
        Set<Permission> result = new HashSet<>();
        for (Long permissionId : permissionIdSet) {
            Permission permission = permissionMapper.selectById(permissionId);
            result.add(permission);
        }
        return result;
    }
}
