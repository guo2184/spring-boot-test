package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author ：ghd
 * @date ：Created in 2019-12-01 19:35
 * @description：
 * @modified By：
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@IdClass(RollbackLogKey.class)
public class RollbackLog {

    @Id
    @Column(name = "id", insertable = false, nullable = false)
    private String id;

    @Id
    @Column(name = "roll_order", insertable = false, nullable = false)
    private Integer order;

    @Id
    @Column(name = "suppment_id", insertable = false, nullable = false)
    private String suppmentId;

    @Column(name = "address")
    private String data;

}
