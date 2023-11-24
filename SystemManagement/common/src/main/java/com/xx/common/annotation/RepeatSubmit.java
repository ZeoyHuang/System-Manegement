package com.xx.common.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {
    /**
     * 防重提交，支持两种，一个方法参数，一个是令牌
     */
    enum  Type {PARAM,TOKEN }

    /**
     * 默认防重提交是方法参数
     */
    Type limitType() default Type.PARAM;

    /**
     * Max number of accesses allowed within the time window.
     * Default value: 10
     */
    int maxAccesses() default 10;

    /**
     * Time window in seconds.
     * Default value: 60
     */
    int timeWindow() default 60;
}
