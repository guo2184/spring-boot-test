package com.example.demo.controller;

import com.example.demo.model.mysql.User;
import com.example.demo.model.oracle.User2;
import com.example.demo.repository.mysql.UserRepository;
import com.example.demo.repository.oracle.UserDao;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserDao userDao;
    @Autowired
    private UserService userService;

    @RequestMapping("/user/{action}")
    public String hello(@PathVariable("action") String action) throws Exception {
        userService.tryConfirm(action);
        return "success";
    }

//    @RequestMapping("/user/roll")
//    public String hello1() {
//        userService.insertUser(true);
//        return "success";
//    }

//    @RequestMapping("/user")
//    public List<User> hello() {
//        User user = new User();
//        user.setUserName("demo");
//        user.setAddress("深圳");
//        userRepository.save(user);
//        return userRepository.findAll();
//    }
//
//    @RequestMapping("/user1")
//    public List<User2> user1() {
//        User2 user21 = new User2();
//        user21.setId(1);
//        user21.setUserName("demo1");
//        user21.setAddress("深圳");
//        userDao.save(user21);
//        return userDao.findAll();
//    }
//
//
//    @RequestMapping("/tanMysql")
//    @Transactional
//    public List<User> tanMysql() {
//        User user = new User();
//        user.setUserName("demo");
//        user.setAddress("深圳");
//        userRepository.save(user);
//
//        int i = 1 /0 ;
//        return userRepository.findAll();
//    }
//
//    @RequestMapping("/tanOracle")
//    @Transactional(value = "oracleTransactionManager")
//    public List<User2> tanOracle() {
//        User2 user21 = new User2();
//        user21.setId(2);
//        user21.setUserName("demo1");
//        user21.setAddress("深圳");
//        userDao.save(user21);
//        int i = 1 /0 ;
//        return userDao.findAll();
//    }
}
