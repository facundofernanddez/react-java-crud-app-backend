package com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api;

import com.reactjavacrudapp.demo.react.java.crud.app.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDaoAPI extends CrudRepository<User, Long> {

  public User getUserByEmail(String email);
}
