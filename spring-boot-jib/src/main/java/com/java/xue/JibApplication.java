package com.java.xue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xue.zeng
 * @date 2020/12/30 4:53 PM
 */
@RestController
@SpringBootApplication
public class JibApplication {
  public static void main(final String[] args) {
    SpringApplication.run(JibApplication.class);
  }

  @GetMapping("/jib/hello")
  public String helloJib() {
    return "hello jib";
  }
}
