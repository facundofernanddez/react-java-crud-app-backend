package com.reactjavacrudapp.demo.react.java.crud.app.service.impl;

import com.reactjavacrudapp.demo.react.java.crud.app.commons.GenericServiceImpl;
import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api.UserDaoAPI;
import com.reactjavacrudapp.demo.react.java.crud.app.repository.UserRepository;
import com.reactjavacrudapp.demo.react.java.crud.app.service.api.UserServiceAPI;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<LoginUser, Long> implements UserServiceAPI, UserDetailsService {

  @Autowired
  private final UserDaoAPI userDaoAPI;
  @Autowired
  private final UserRepository userRepository;

  @Override
  public CrudRepository<LoginUser, Long> getDao() {
    return userDaoAPI;
  }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }
    

}
