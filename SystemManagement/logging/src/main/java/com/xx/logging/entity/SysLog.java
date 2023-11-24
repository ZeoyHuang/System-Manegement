package com.xx.logging.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-08-12 12:23
 */
@Getter
@Setter
@TableName("sys_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "log_id", type = IdType.ASSIGN_ID)
    private Long logId;

    private String description;

    private String logType;

    private String method;

    private String params;

    private String requestIp;

    private Long time;

    private String username;

    private String address;

    private String browser;

    private String exceptionDetail;

    private LocalDateTime createTime;
}
