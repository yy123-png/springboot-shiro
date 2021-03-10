package com.yy.shirosecurity.base.mapper;

import com.yy.shirosecurity.base.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
public interface UserMapper extends BaseMapper<User> {
    User getByUsername(@Param("username") String username);
}
