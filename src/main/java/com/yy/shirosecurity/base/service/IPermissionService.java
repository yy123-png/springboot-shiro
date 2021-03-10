package com.yy.shirosecurity.base.service;

import com.yy.shirosecurity.base.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
public interface IPermissionService extends IService<Permission> {
    Set<Permission> getPermissionSetByRoleId(Long roleId);
}
