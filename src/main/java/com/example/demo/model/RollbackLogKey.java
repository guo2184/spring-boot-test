package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author ：ghd
 * @date ：Created in 2019-12-01 19:35
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class RollbackLogKey implements Serializable {
    private String id;

    private Integer order;

    private String suppmentId;

}
