package com.yy.shirosecurity.base.service;

import com.yy.shirosecurity.base.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
public interface IUserService extends IService<User> {
    User getByUsername(String username);

    void createEmployee(User user);
}
