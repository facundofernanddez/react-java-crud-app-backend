package com.reactjavacrudapp.demo.react.java.crud.app.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

  @Autowired
  private final ConfirmationTokenRepository confirmationTokenRepository;

  public void saveConfirmationToken(ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }

  public ConfirmationToken getToken(String token) {
    Optional<ConfirmationToken> tokenFinded = confirmationTokenRepository.findByToken(token);

    return tokenFinded.get();
  }

  public int setConfirmedAt(String token) {
    return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
  }
}
