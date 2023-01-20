package com.reactjavacrudapp.demo.react.java.crud.app.service.impl;

import com.reactjavacrudapp.demo.react.java.crud.app.enumerations.Role;
import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.model.dao.api.UserDaoAPI;
import com.reactjavacrudapp.demo.react.java.crud.app.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@NoArgsConstructor
public class SingUpService implements UserDetailsService {

  @Autowired
  private final UserDaoAPI userDaoAPI;
  @Autowired
  private final UserRepository userRepository;

  //terminar de completar el service
  @Transactional
  public void signUp(String name, String email, String password, String password2) throws Exception {
    check(name, email, password, password2);

    LoginUser user = new LoginUser();
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
    LoginUser usuario = userRepository.getUserByEmail(email);

    if (usuario != null) {

      List<GrantedAuthority> permissions = new ArrayList();

      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

      permissions.add(p);

      return new User(usuario.getEmail(), usuario.getPassword(), permissions);
    } else {
      return null;
    }
  }
}
