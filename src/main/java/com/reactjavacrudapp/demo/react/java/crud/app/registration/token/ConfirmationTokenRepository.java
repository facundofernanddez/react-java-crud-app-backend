package com.reactjavacrudapp.demo.react.java.crud.app.registration.token;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

  @Query("SELECT a FROM ConfirmationToken WHERE a.token = :token")
  Optional<ConfirmationToken> findByToken(@Param("token") String token);

}
