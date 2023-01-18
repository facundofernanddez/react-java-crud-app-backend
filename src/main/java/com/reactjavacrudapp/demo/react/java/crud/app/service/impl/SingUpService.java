package com.reactjavacrudapp.demo.react.java.crud.app.service.impl;

import com.reactjavacrudapp.demo.react.java.crud.app.enumerations.Role;
import com.reactjavacrudapp.demo.react.java.crud.app.model.User;
import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SingUpService {

  //terminar de completar el service
  @Transactional
  public void signUp(String name, String email, String password, String password2) throws Exception {
    check(name, email, password, password2);

    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setRol(Role.USER);

    userDaoAPI.save(user);

  }

  public void check(String nombre, String email, String password, String password2) throws Exception {
    if (nombre.isEmpty() || nombre == null) {
      throw new Exception("El nombre no puede estar vacio");
    }
    if (email.isEmpty() || email == null) {
      throw new Exception("El email no puede estar vacio");
    }
    if (password.isEmpty() || password == null || password.length() <= 5) {
      throw new Exception("La contraseña no puede estar vacio o ser menor a 5 caracteres");
    }
    if (!password.equals(password2)) {
      throw new Exception("Las contraseñas deben ser iguales");
    }
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userDaoAPI.getUserByEmail(email);
  }
}
