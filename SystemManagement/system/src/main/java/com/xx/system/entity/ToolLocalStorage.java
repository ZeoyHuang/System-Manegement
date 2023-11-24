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
@TableName("tool_local_storage")
public class ToolLocalStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long storageId;

    /**
     * 文件真实的名称
     */
    private String realName;

    /**
     * 文件名
     */
    private String name;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 路径
     */
    private String path;

    /**
     * 类型
     */
    private String type;

    /**
     * 大小
     */
    private String size;

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
