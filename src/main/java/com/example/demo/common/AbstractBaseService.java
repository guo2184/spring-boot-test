package com.example.demo.common;

import org.springframework.aop.framework.AopContext;

/**
 * @author ：ghd
 * @date ：Created in 2019-12-01 17:34
 * @description：
 * @modified By：
 * @version: $
 */
public abstract class AbstractBaseService<K, T, V> implements BaseService<T, V> {

    public K getCurrent() {
        return AopContext.currentProxy() != null ?
                (K) AopContext.currentProxy() : (K) this;
    }
}
