package com.example.demo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：ghd
 * @date ：Created in 2019-12-01 16:21
 * @description：
 * @modified By：
 * @version: $
 */
@Target(ElementType.METHOD)   //定义是用在方法上
@Retention(RetentionPolicy.RUNTIME)  // 定义是在运行时生效
public @interface DbAction {
    String transactionManagerName();
    String rollback();
}
