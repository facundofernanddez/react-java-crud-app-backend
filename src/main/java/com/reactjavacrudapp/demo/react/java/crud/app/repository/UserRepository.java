package com.reactjavacrudapp.demo.react.java.crud.app.repository;

import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<LoginUser, Long> {

  @Query("SELECT a FROM User a WHERE a.email = :email")
  public LoginUser getUserByEmail(@Param("email") String email);

}
