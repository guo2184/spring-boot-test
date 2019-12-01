package com.example.demo.model.oracle;

import com.example.demo.model.RollbackLog;

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
public class User2RollbackLog extends RollbackLog implements Serializable {
}
