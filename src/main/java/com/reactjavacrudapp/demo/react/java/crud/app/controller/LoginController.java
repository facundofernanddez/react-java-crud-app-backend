package com.reactjavacrudapp.demo.react.java.crud.app.controller;

import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.registration.RegistrationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class LoginController {

  @Autowired
  private RegistrationController signUpService;

  @PostMapping("/signup")
  public ResponseEntity signUp(@RequestBody LoginUser user) {
    try {
      LoginUser obj = signUpService.signUp(user);

      return new ResponseEntity(obj, HttpStatus.OK);

    } catch (Exception ex) {
      System.out.println(ex);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/login")
  public void Login(@RequestBody(required = false) LoginUser user) {

  }

}
