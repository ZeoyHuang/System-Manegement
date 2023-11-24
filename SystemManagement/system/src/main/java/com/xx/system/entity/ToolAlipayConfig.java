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
@TableName("tool_alipay_config")
public class ToolAlipayConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    private Long configId;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 编码
     */
    private String charset;

    /**
     * 类型 固定格式ison
     */
    private String format;

    /**
     * 网关地址
     */
    private String gatewayUrl;

    /**
     * 异步回调
     */
    private String notifyUrl;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 回调地址
     */
    private String returnUrl;

    /**
     * 签名方式
     */
    private String signType;

    /**
     * 商户号
     */
    private String sysServiceProviderId;
}
