package com.example.demo.repository.oracle;

import com.example.demo.model.oracle.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User2, Integer>, JpaSpecificationExecutor<User2> {

}