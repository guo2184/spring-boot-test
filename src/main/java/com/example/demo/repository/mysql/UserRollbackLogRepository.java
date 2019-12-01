package com.example.demo.repository.mysql;

import com.example.demo.model.mysql.UserRollbackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRollbackLogRepository extends JpaRepository<UserRollbackLog, String>, JpaSpecificationExecutor<UserRollbackLog> {

}