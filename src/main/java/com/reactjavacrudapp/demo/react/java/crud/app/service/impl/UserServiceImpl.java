package com.reactjavacrudapp.demo.react.java.crud.app.service.impl;

import com.reactjavacrudapp.demo.react.java.crud.app.commons.GenericServiceImpl;
import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api.UserDaoAPI;
import com.reactjavacrudapp.demo.react.java.crud.app.registration.token.ConfirmationToken;
import com.reactjavacrudapp.demo.react.java.crud.app.registration.token.ConfirmationTokenService;
import com.reactjavacrudapp.demo.react.java.crud.app.repository.UserRepository;
import com.reactjavacrudapp.demo.react.java.crud.app.service.api.UserServiceAPI;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends GenericServiceImpl<LoginUser, Long> implements UserServiceAPI, UserDetailsService {

  @Autowired
  private final UserDaoAPI userDaoAPI;
  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private final ConfirmationTokenService confirmationTokenService;

  @Override
  public CrudRepository<LoginUser, Long> getDao() {
    return userDaoAPI;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findUserByEmail(email);
  }

  public String signUpUser(LoginUser loginUser) {
    Boolean userExists = userRepository.findUserByEmail(loginUser.getEmail()) != null;

    if (userExists) {
//    TODO: hacer que si el usuario no confirmó el mail, se pueda volver a registrar sin tirar error.
      throw new IllegalStateException("Email ya existe");
    }

    String encodedPassword = bCryptPasswordEncoder.encode(loginUser.getPassword());

    loginUser.setPassword(encodedPassword);

    userRepository.save(loginUser);

    String token = UUID.randomUUID().toString();
    ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            loginUser);

    confirmationTokenService.saveConfirmationToken(confirmationToken);

    return token;
  }

  public int enableLoginUser(String email) {
    return userRepository.enableLoginUser(email);
  }

}
