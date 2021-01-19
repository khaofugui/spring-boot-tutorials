package com.java.xue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 授权服务器
 *
 * @author xue.zeng
 */
@Slf4j
@SpringBootApplication()
public class Oauth2AuthorizationCodeServerApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Oauth2AuthorizationCodeServerApplication.class, args);
  }
}
