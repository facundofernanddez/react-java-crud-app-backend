package com.reactjavacrudapp.demo.react.java.crud.app.security.config;

import com.reactjavacrudapp.demo.react.java.crud.app.registration.RegistrationController;
import com.reactjavacrudapp.demo.react.java.crud.app.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public RegistrationController signUpService;

    @Autowired
    private final UserServiceImpl userServiceImpl;
    
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userServiceImpl);

        return provider;
    }

  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**")
                .permitAll()
                .anyRequest()
                .authenticated()
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
