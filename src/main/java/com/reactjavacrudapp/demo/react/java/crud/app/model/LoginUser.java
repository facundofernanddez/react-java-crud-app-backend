package com.reactjavacrudapp.demo.react.java.crud.app.model;

import com.reactjavacrudapp.demo.react.java.crud.app.enumerations.Role;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String name;
  @Column
  private String email;
  @Column
  private String password;
  @Enumerated(EnumType.STRING)
  private Role rol;
}
