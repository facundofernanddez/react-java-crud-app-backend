package com.reactjavacrudapp.demo.react.java.crud.app.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

  @Query("SELECT a FROM ConfirmationToken a WHERE a.token = :token")
  Optional<ConfirmationToken> findByToken(@Param("token") String token);

  @Transactional
  @Modifying
  @Query("UPDATE a FROM ConfirmationToken a SET a.confirmedAt = :confirmedAt WHERE a.token = :token")
  int updateConfirmedAt(@Param("token") String token, @Param("confirmedAt") LocalDateTime confirmedAt);
}
