package ru.denisqaa.learning.security.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.denisqaa.learning.security.springsecurity.service.UserService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //TODO Помечен как Deprecated, переделать под Spring 2.7.0
public class WebSecurityConfiguration {
  private final SuccessUserLoginHandler successUserLoginHandler;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;


  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http  
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user").hasAnyRole("ADMIN", "USER")
        .antMatchers("/", "/index").permitAll()
        .anyRequest().authenticated()
        .and()
          .formLogin().successHandler(successUserLoginHandler)
          .permitAll()
        .and()
          .logout()
          .permitAll()
        .and()
          .csrf()
          .disable();
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return userService;
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    authenticationProvider.setUserDetailsService(userService);
    return authenticationProvider;
  }
}
