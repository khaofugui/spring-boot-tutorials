package com.java.xue.config;

import com.java.xue.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xue.zeng
 * @date 2021/1/8 1:26 PM
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
  @Bean
  public LoginInterceptor getLoginInterceptor() {
    return new LoginInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(getLoginInterceptor()) // 指定拦截器类
        .addPathPatterns("/**"); // 指定该类拦截的url
  }
}
