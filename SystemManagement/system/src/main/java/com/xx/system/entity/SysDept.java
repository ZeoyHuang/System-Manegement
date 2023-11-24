package com.xx.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */

@Data
@Getter
@Setter
@TableName("sys_dept")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long deptId;

    /**
     * 上级部门
     */
    private Long pid;

    /**
     * 子部门数目
     */
    private Integer subCount;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 状态
     */
    private Boolean enabled;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
