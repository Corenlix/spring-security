package ru.denisqaa.learning.security.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity //TODO Помечен как Deprecated, переделать под Spring 2.7.0
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final SuccessUserLoginHandler successUserLoginHandler;

  public WebSecurityConfiguration(
      SuccessUserLoginHandler successUserHandler) {
    this.successUserLoginHandler = successUserHandler;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/index").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().successHandler(successUserLoginHandler)
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }

  // TODO на текущий момент In-Memory аутентификация, нужно сделать чтобы все подтяигвалос с базы, + пароли никак не шифруется, добавить Bcrypt
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails user =
        User.withDefaultPasswordEncoder() //TODO Deprecated, сделать конфиг на новом спринге
            .username("user")
            .password("user")
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(user);
  }
}
