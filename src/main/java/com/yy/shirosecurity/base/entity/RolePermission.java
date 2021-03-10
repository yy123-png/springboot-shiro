package com.yy.shirosecurity.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yeyu
 * @since 2021-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long permissionId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
