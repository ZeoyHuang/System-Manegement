package com.xx.logging.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog {

    String opType() default "";  // 操作类型

    String opDesc() default "";  // 操作说明
}
