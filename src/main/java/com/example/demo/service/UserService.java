package com.example.demo.service;

import com.example.demo.common.AbstractBaseService;
import com.example.demo.common.DbAction;
import com.example.demo.constants.DBConstants;
import com.example.demo.model.mysql.User;
import com.example.demo.model.oracle.User2;
import com.example.demo.repository.mysql.UserRepository;
import com.example.demo.repository.oracle.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：ghd
 * @date ：Created in 2019-11-29 23:24
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class UserService extends AbstractBaseService<UserService, String, String> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Override
    public boolean check(String s) {
        return true;
    }

    @Override
    public String tryConfirm(String s) throws Exception {
//        getCurrent().insertMysql(s);
//        getCurrent().insertOracle(s);
        getCurrent().insertMysql(s);
        getCurrent().insertOracle(s);
        if (s.equals("rollAll")) {
            throw new Exception();
        }
        return "success";
    }

    @DbAction(transactionManagerName = DBConstants.MYSQL_TRANSACTION_MANAGER,
        rollback = "rollbackInsertMysql")
    protected User insertMysql(String s) throws Exception {
        User user = new User();
        user.setUserName(s);
        user.setAddress("mysql");
        userRepository.save(user);
        if (s.equals("rollMysql")) {
            throw new Exception();
        }
        return user;
    }

    @DbAction(transactionManagerName = DBConstants.ORACLE_TRANSACTION_MANAGER,
        rollback = "rollbackInsertOracle")
    protected User2 insertOracle(String s) throws Exception {
        User2 user = new User2();
        user.setUserName(s);
        user.setAddress("oracle");
        userDao.save(user);
        if (s.equals("rollOracle")) {
            throw new Exception();
        }
        return user;
    }

    public void rollbackInsertMysql(User user) {
        userRepository.delete(user);
    }

    public void rollbackInsertOracle(User2 user) {
        userDao.delete(user);
    }

    @Override
    public boolean cancel(String s) {
        return true;
    }

    @Override
    public void postConfirm(String s) {

    }
}
