package com.reactjavacrudapp.demo.react.java.crud.app.service.impl;

import com.reactjavacrudapp.demo.react.java.crud.app.commons.GenericServiceImpl;
import com.reactjavacrudapp.demo.react.java.crud.app.model.User;
import com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api.UserDaoAPI;
import com.reactjavacrudapp.demo.react.java.crud.app.service.api.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserServiceAPI {

  @Autowired
  private UserDaoAPI userDaoAPI;

  @Override
  public CrudRepository<User, Long> getDao() {
    return userDaoAPI;
  }

  @Override
  public User getUserByEmail(String email) {
    //completar la funcion
    return User;
  }

}
