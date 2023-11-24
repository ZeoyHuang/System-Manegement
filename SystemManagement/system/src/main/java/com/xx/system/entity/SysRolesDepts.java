package com.xx.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
@Getter
@Setter
@TableName("sys_roles_depts")
public class SysRolesDepts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long roleId;

    private Long deptId;
}
