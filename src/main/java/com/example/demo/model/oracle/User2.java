package com.example.demo.model.oracle;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_user")
@Entity
@Data
public class User2 implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "address")
  private String address;

  @Column(name = "user_name")
  private String userName;

  
}