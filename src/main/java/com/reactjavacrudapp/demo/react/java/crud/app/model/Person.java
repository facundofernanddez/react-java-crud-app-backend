package com.reactjavacrudapp.demo.react.java.crud.app.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String name;
  @Column
  private String lastname;
  @Column
  private String address;
  @Column
  private String cellphone;

}
