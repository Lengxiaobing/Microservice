package com.cloud.dynamic.annotation;

import java.lang.annotation.*;

/**
 * @Description: 切换数据注解
 * 可以用于类或者方法级别：方法级别优先级 > 类级别
 * @Author: ZX
 * @Date: 2019/3/16 16:47
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /**
     * 该值即key值
     *
     * @return
     */
    String value() default "master";
}