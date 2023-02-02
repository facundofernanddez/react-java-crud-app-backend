package com.reactjavacrudapp.demo.react.java.crud.app.repository;

import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<LoginUser, Long> {

  @Query("SELECT a FROM LoginUser a WHERE a.email = :email")
  public LoginUser findUserByEmail(@Param("email") String email);

  @Transactional
  @Modifying
  @Query("UPDATE LoginUser a SET a.enabled = true WHERE a.email = :email")
  int enableLoginUser(@Param("email") String email);

}
