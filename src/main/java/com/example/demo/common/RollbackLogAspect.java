package com.example.demo.common;

import com.example.demo.constants.DBConstants;
import com.example.demo.model.RollbackLog;
import com.example.demo.model.mysql.UserRollbackLog;
import com.example.demo.model.oracle.User2RollbackLog;
import com.example.demo.repository.mysql.UserRollbackLogRepository;
import com.example.demo.repository.oracle.UserRollbackLogDao;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author ：ghd
 * @date ：Created in 2019-12-01 18:06
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@Aspect
@Slf4j
public class RollbackLogAspect implements ApplicationContextAware {

    @Autowired
    private UserRollbackLogRepository userRollbackLogRepository;

    @Autowired
    private UserRollbackLogDao userRollbackLogDao;

    private ApplicationContext applicationContext;

    @Around("@annotation(dbAction)")
    public Object doAround(ProceedingJoinPoint joinPoint, DbAction dbAction) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DbAction action = method.getAnnotation(DbAction.class);

        PlatformTransactionManager transactionManager =
                (PlatformTransactionManager) applicationContext.getBean(
                action.transactionManagerName());

        //1.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //2.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //3.获得事务状态
        TransactionStatus status = transactionManager.getTransaction(def);

        Object obj = null;
        try {
            obj = joinPoint.proceed();
            insertLog(obj, action.transactionManagerName());

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }


        return obj;
    }


    private void insertLog(Object obj, String transactionManagerName) {
        RollbackLog baseRollbackLog = this.getRollbackLog(transactionManagerName);
        baseRollbackLog.setId(UUID.randomUUID().toString().replaceAll("-",""));
        baseRollbackLog.setOrder(1);
        baseRollbackLog.setSuppmentId("test");
        baseRollbackLog.setData(new Gson().toJson(obj));
        this.getRepository(transactionManagerName).save(baseRollbackLog);
    }

    private RollbackLog getRollbackLog(String transactionManagerName) {
        switch (transactionManagerName) {
            case DBConstants
                    .MYSQL_TRANSACTION_MANAGER:
                return new UserRollbackLog();
            case DBConstants.ORACLE_TRANSACTION_MANAGER:
                return new User2RollbackLog();
            default:
                return null;
        }
    }

    private JpaRepository getRepository(String transactionManagerName) {
        switch (transactionManagerName) {
            case DBConstants
                    .MYSQL_TRANSACTION_MANAGER:
                return userRollbackLogRepository;
            case DBConstants.ORACLE_TRANSACTION_MANAGER:
                return userRollbackLogDao;
            default:
                return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

}
