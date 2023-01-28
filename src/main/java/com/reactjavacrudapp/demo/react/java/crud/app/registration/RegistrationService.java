package com.reactjavacrudapp.demo.react.java.crud.app.registration;

import com.reactjavacrudapp.demo.react.java.crud.app.enumerations.Role;
import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    
    @Autowired
    private final EmailValidator emailValidator;
    @Autowired
    private final UserServiceImpl userServiceImpl;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        
        return userServiceImpl.signUpUser(
                new LoginUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        Role.USER
                )
        );
    }

}
