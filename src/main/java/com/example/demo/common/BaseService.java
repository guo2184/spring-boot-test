package com.example.demo.common;

/**
 * @author ：ghd
 * @date ：Created in 2019-12-01 16:21
 * @description：
 * @modified By：
 * @version: $
 */
public interface BaseService<T, V> {

    boolean check(T t);

    V tryConfirm(T t) throws Exception;

    boolean cancel(T t);

    void postConfirm(T t);
}
