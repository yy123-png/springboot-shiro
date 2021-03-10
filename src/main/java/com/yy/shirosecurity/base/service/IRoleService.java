package com.yy.shirosecurity.base.service;

import com.yy.shirosecurity.base.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shirosecurity.base.entity.User;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
public interface IRoleService extends IService<Role> {
    Set<Role> getRolesByUserId(Long userId);

}
