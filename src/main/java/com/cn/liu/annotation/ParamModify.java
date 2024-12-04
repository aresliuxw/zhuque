package com.cn.liu.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ParamModify {

    String[] fields() default {};

    String[] values() default {};

}
