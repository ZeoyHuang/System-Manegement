package com.xx.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@ApiModel(value = "permission info")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_permission")
public class Permission {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 角色id
    private Long roleId;
    // 菜单id
    private Long menuId;

    private Timestamp createTime;

    public Permission(Long roleId, Long menuId, Timestamp createTime) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.createTime = createTime;
    }
}
