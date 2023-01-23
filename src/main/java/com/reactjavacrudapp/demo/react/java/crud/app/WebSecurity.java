package com.reactjavacrudapp.demo.react.java.crud.app;

import com.reactjavacrudapp.demo.react.java.crud.app.service.impl.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  public SignUpService signUpService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(signUpService)
            .passwordEncoder(new BCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/css/*", "/js/*", "/img/*", "/**")
            .permitAll()
            .and().formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/logincheck")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/") //cambiar bien esto
            .permitAll()
            .and().logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .permitAll();
  }

}
