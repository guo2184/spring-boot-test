package com.example.demo.model.mysql;

import com.example.demo.model.RollbackLog;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author ：ghd
 * @date ：Created in 2019-12-01 19:42
 * @description：
 * @modified By：
 * @version: $
 */
@Entity
@Table(name = "t_user_log")
@Data
public class UserRollbackLog extends RollbackLog implements Serializable {
}
