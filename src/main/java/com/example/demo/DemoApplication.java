package com.example.demo;

import com.example.demo.model.mysql.User;
import com.example.demo.repository.mysql.UserRepository;
import com.example.demo.repository.oracle.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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
		user1.setUserName("demo1");
		user1.setAddress("深圳");
		userDao.save(user1);
		return userDao.findAll();
	}
}
