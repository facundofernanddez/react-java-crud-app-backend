package com.reactjavacrudapp.demo.react.java.crud.app.service.impl;

import com.reactjavacrudapp.demo.react.java.crud.app.commons.GenericServiceImpl;
import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api.UserDaoAPI;
import com.reactjavacrudapp.demo.react.java.crud.app.service.api.UserServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class UserServiceImpl extends GenericServiceImpl<LoginUser, Long> implements UserServiceAPI {

  @Autowired
  private UserDaoAPI userDaoAPI;

  @Override
  public CrudRepository<LoginUser, Long> getDao() {
    return userDaoAPI;
  }

}
