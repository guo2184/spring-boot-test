package com.example.demo.repository.oracle;

import com.example.demo.model.oracle.User2RollbackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRollbackLogDao extends JpaRepository<User2RollbackLog, String>, JpaSpecificationExecutor<User2RollbackLog> {

}