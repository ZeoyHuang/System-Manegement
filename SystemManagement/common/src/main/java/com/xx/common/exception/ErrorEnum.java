package com.xx.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    RESP_CODE_SUCC("0","请求成功"),
    RESP_CODE_FAIL("1","请求失败"),
    RESP_CODE_NO_TOKEN("-1","TOKEN为空"),
    RESP_CODE_TIMEOUT("-2","会话超时或非法请求");

    /**
     * 错误码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;
}
