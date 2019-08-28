package com.example.demo.controller;

import com.example.demo.model.mysql.User;
import com.example.demo.repository.mysql.UserRepository;
import com.example.demo.repository.oracle.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/jtaUser1")
    @Transactional
    public List<User> jtaUser1() {
        User user = new User();
        user.setUserName("demo");
        user.setAddress("深圳");
        userRepository.save(user);

        com.example.demo.model.oracle.User user1 = new com.example.demo.model.oracle.User();
        user1.setUserName("demo2");
        user1.setAddress("深圳");
        userDao.save(user1);

        return userRepository.findAll();
    }

    @RequestMapping("/jtaUser")
    @Transactional
    public List<User> jtaUser() {
        com.example.demo.model.oracle.User user1 = new com.example.demo.model.oracle.User();
        user1.setUserName("demo2");
        user1.setAddress("深圳");
        userDao.save(user1);

        User user = new User();
        user.setUserName("demo");
        user.setAddress("深圳");
        userRepository.save(user);

        int i = 1 /0;
        return userRepository.findAll();
    }

    @RequestMapping("/user")
    public List<User> hello() {
        User user = new User();
        user.setUserName("demo");
        user.setAddress("深圳");
        userRepository.save(user);
        return userRepository.findAll();
    }

    @RequestMapping("/user1")
    public List<com.example.demo.model.oracle.User> user1() {
        com.example.demo.model.oracle.User user1 = new com.example.demo.model.oracle.User();
        user1.setId(1);
        user1.setUserName("demo1");
        user1.setAddress("深圳");
        userDao.save(user1);
        return userDao.findAll();
    }


    @RequestMapping("/tanMysql")
    @Transactional
    public List<User> tanMysql() {
        User user = new User();
        user.setUserName("demo");
        user.setAddress("深圳");
        userRepository.save(user);

        int i = 1 /0 ;
        return userRepository.findAll();
    }

    @RequestMapping("/tanOracle")
    @Transactional(value = "oracleTransactionManager")
    public List<com.example.demo.model.oracle.User> tanOracle() {
        com.example.demo.model.oracle.User user1 = new com.example.demo.model.oracle.User();
        user1.setId(2);
        user1.setUserName("demo1");
        user1.setAddress("深圳");
        userDao.save(user1);
        int i = 1 /0 ;
        return userDao.findAll();
    }
}
