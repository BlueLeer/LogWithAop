package com.lee.logwithaop.anno;

import java.lang.annotation.*;

/**
 * @author WangLe
 * @date 2019/12/12 16:21
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
