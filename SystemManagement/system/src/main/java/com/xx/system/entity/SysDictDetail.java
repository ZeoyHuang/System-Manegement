package com.xx.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("sys_dict_detail")
public class SysDictDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long detailId;

    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典值
     */
    private String value;

    /**
     * 排序
     */
    private Integer dictSort;

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
