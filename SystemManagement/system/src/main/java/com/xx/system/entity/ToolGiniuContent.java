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
@TableName("tool_giniu_content")
public class ToolGiniuContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long contentId;

    /**
     * Bucket 识别符
     */
    private String bucket;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 文件类型: 私有或公开
     */
    private String type;

    /**
     * 文件url
     */
    private String url;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 上传或同步的时间
     */
    private LocalDateTime updateTime;
}
