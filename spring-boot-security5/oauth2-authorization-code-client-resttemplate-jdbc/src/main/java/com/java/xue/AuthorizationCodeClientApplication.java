package com.java.xue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author xue.zeng
 * @date 2021/1/19 2:25 PM
 */
@SpringBootApplication
public class AuthorizationCodeClientApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthorizationCodeClientApplication.class);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
