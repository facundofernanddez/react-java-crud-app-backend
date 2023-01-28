package com.reactjavacrudapp.demo.react.java.crud.app.registration.token;

import com.reactjavacrudapp.demo.react.java.crud.app.model.LoginUser;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmationToken {

  @Id
  @SequenceGenerator(
          name = "confirmation_token_sequence",
          sequenceName = "confirmation_token_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "confirmation_token_sequence"
  )
  private Long id;
  @Column(nullable = false)
  private String token;
  @Column(nullable = false)
  private LocalDateTime createAt;
  @Column(nullable = false)
  private LocalDateTime expiresAt;

  @ManyToOne
  @JoinColumn(
          nullable = false,
          name = "login_user_id")
  private LoginUser loginUser;

  public ConfirmationToken(
          String token,
          LocalDateTime createAt,
          LocalDateTime expiredAt,
          LoginUser loginUser) {
    this.token = token;
    this.createAt = createAt;
    this.expiresAt = expiredAt;
    this.loginUser = loginUser;
  }

}
