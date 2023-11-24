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
@TableName("tool_email_config")
public class ToolEmailConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long configId;

    /**
     * 收件人
     */
    private String fromUser;

    /**
     * 邮件服务器SMTP地址
     */
    private String host;

    /**
     * 密码
     */
    private String pass;

    /**
     * 端门
     */
    private String port;

    /**
     * 发件者用户名
     */
    private String user;
}
