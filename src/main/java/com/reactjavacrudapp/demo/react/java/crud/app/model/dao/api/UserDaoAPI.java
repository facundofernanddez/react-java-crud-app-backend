package com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api;

import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import org.springframework.data.repository.CrudRepository;

public interface UserDaoAPI extends CrudRepository<LoginUser, Long> {

}
