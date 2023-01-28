package com.reactjavacrudapp.demo.react.java.crud.app.registration;

import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.repository.UserRepository;
import com.reactjavacrudapp.demo.react.java.crud.app.service.api.UserServiceAPI;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController{
    
    @Autowired
    private final RegistrationService registrationService;
    
    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

//  @Autowired
//  private final UserServiceAPI userServiceAPI;
//  @Autowired
//  private final UserRepository userRepository;
////  private String USER_NOT_FOUND = "user with email %s not found";
//
//  @Transactional
//  public LoginUser signUp(LoginUser user) throws Exception {
//    check(user.getName(), user.getEmail(), user.getPassword());
//
//    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//
//    return userServiceAPI.save(user);
//  }
//
//  private void check(String nombre, String email, String password) throws Exception {
//    if (nombre.isEmpty() || nombre == null) {
//      throw new Exception("El nombre no puede estar vacio");
//    }
//    if (email.isEmpty() || email == null) {
//      throw new Exception("El email no puede estar vacio");
//    }
//    if (password.isEmpty() || password == null || password.length() <= 5) {
//      throw new Exception("La contraseña no puede estar vacio o ser menor a 5 caracteres");
//    }
//  }

//  @Override
//  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
////    LoginUser usuario = userRepository.getUserByEmail(email);
//
////    if (usuario != null) {
////
////      List<GrantedAuthority> permissions = new ArrayList();
////
////      GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
////
////      permissions.add(p);
////
////      return userRepository.findUserByEmail(email);
////    }
////    else{
////        return null;
////    }
//    return userRepository.findUserByEmail(email);
//  }
}
