package com.reactjavacrudapp.demo.react.java.crud.app.controller;

import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.service.impl.SignUpService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class LoginController {

  @Autowired
  private SignUpService signUpService;

  @PostMapping("/signup")
  public ResponseEntity signUp(@RequestBody LoginUser user) {

    try {
      signUpService.signUp(user.getName(), user.getEmail(), user.getPassword(), user.getPassword());

      return new ResponseEntity(HttpStatus.OK);
    } catch (Exception ex) {
      Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
