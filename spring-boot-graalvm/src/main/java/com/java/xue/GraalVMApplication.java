package com.java.xue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xue.zeng
 * @date 2021/1/6 12:01 PM
 */
@SpringBootApplication(proxyBeanMethods = false)
public class GraalVMApplication {
  public static void main(final String[] args) {
    SpringApplication.run(GraalVMApplication.class);
  }
}
