package com.reactjavacrudapp.demo.react.java.crud.app.service.api;

import com.reactjavacrudapp.demo.react.java.crud.app.commons.GenericServiceAPI;
import com.reactjavacrudapp.demo.react.java.crud.app.model.User;

public interface UserServiceAPI extends GenericServiceAPI<User, Long> {

  public User getUserByEmail(String email);
}
