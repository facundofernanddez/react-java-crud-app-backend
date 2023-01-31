package com.reactjavacrudapp.demo.react.java.crud.app.registration;

import com.reactjavacrudapp.demo.react.java.crud.app.enumerations.Role;
import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import com.reactjavacrudapp.demo.react.java.crud.app.registration.token.ConfirmationToken;
import com.reactjavacrudapp.demo.react.java.crud.app.registration.token.ConfirmationTokenService;
import com.reactjavacrudapp.demo.react.java.crud.app.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistrationService {

  @Autowired
  private final EmailValidator emailValidator;
  @Autowired
  private final UserServiceImpl userServiceImpl;
  @Autowired
  private final ConfirmationTokenService confirmationTokenService;

  public String register(RegistrationRequest request) {
    boolean isValidEmail = emailValidator.test(request.getEmail());

    if (!isValidEmail) {
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

  @Transactional
  public String confirmToken(String token) {
    ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);

    if (confirmationToken == null) {
      throw new IllegalStateException("Token not found");
    }

    if (confirmationToken.getConfirmedAt() != null) {
      throw new IllegalStateException("email already confirmed");
    }

    LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("token expired");
    }

    confirmationTokenService.setConfirmedAt(token);

    userServiceImpl.enableLoginUser(confirmationToken.getLoginUser().getEmail());

    return "confirmed";
  }

}
