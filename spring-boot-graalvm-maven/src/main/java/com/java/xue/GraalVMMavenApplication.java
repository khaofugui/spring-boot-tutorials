package com.java.xue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xue.zeng
 * @date 2021/1/6 5:17 PM
 */
@RestController
@SpringBootApplication(proxyBeanMethods = false)
public class GraalVMMavenApplication {
  public static void main(final String[] args) {
    SpringApplication.run(GraalVMMavenApplication.class);
  }

  @RequestMapping("/hello/graalvm")
  public String helloGraalVM() {
    return "Hello GraalVM";
  }
}
