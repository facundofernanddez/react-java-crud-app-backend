package com.reactjavacrudapp.demo.react.java.crud.app.model;

import com.reactjavacrudapp.demo.react.java.crud.app.enumerations.Role;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LoginUser implements UserDetails {

  @Id
  @SequenceGenerator(
          name = "users_sequence",
          sequenceName = "users_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "users_sequence"
  )
  private Long id;
  @Column
  private String username;
  @Column
  private String email;
  @Column
  private String password;
  @Enumerated(EnumType.STRING)
  private Role rol;
  private Boolean locked;
  private Boolean enabled;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rol.name());

    return Collections.singletonList(authority);
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
