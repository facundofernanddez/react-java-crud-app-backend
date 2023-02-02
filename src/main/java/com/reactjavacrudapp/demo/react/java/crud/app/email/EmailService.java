package com.reactjavacrudapp.demo.react.java.crud.app.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

  @Autowired
  private final JavaMailSender javaMailSender;
  @Autowired
  private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  @Override
  @Async
  public void send(String to, String email) {
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
      helper.setText(email, true);
      helper.setTo(to);
      helper.setSubject("Confirm your email");
      helper.setFrom("no-reply@facundocrudapp.com");
    } catch (MessagingException e) {
      LOGGER.error("failed to send email", email);
      throw new IllegalStateException("filed to send email");
    }
  }

}