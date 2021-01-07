package com.java.xue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Slf4j
@SpringBootApplication
public class HoverflyActuralServiceClientApplication {
  private static final int HOVERFLY_PORT = 8500;
  private static final String HOVERFLY_HOST = "localhost";
  private static final String PROXY = "proxy";

  public static void main(String[] args) {
    SpringApplication.run(HoverflyActuralServiceClientApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {

    String mode = System.getProperty("mode");
    log.info("##################### Mode ################# {}", mode);

    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOVERFLY_HOST, HOVERFLY_PORT));
    requestFactory.setProxy(proxy);
    RestTemplate template = null;

    if (mode != null && mode.equalsIgnoreCase(PROXY)) {
      log.info("######### Running application in PROXY mode so that we can use simulated hoverfly server!!!!");
      template = new RestTemplate(requestFactory);
    } else {
      log.info("######### Running application in PRODUCTION mode so that we can use simulated hoverfly server!!!!");
      template = new RestTemplate();
    }

    return template;
  }
}
