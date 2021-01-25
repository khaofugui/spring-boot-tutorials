package com.java.xue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author xue.zeng
 * @date 2021/1/18 4:32 PM
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter: off
    auth.inMemoryAuthentication()
        .withUser("hellxz")
        .password(passwordEncoder().encode("xyz"))
        .authorities(Collections.emptyList());
    // @formatter: on
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests() // 开启授权配置
        .anyRequest().authenticated() // 所有请求都需要通过认证
        .and()
        .httpBasic() // Basic提交
        .and()
        .csrf()
        .disable(); // 关跨域保护
  }
}
