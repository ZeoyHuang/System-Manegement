package com.xx.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 7133651385712198609L;
    private String code;

    /**
     * 使用已有的错误类型
     * @param type 枚举类中的错误类型
     */
    public ServiceException(ErrorEnum type){
        super(type.getMsg());
        this.code = type.getCode();
    }

    /**
     * 自定义错误类型
     * @param code 自定义的错误码
     * @param msg 自定义的错误提示
     */
    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
