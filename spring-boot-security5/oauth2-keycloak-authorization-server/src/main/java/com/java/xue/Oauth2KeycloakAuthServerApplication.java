package com.java.xue;

import com.java.xue.config.KeycloakServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * @author xue.zeng
 */
@Slf4j
@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
@EnableConfigurationProperties({KeycloakServerProperties.class})
public class Oauth2KeycloakAuthServerApplication {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Oauth2KeycloakAuthServerApplication.class, args);
  }

  @Bean
  ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(
      ServerProperties serverProperties, KeycloakServerProperties keycloakServerProperties) {

    return (evt) -> {
      Integer port = serverProperties.getPort();
      String keycloakContextPath = keycloakServerProperties.getContextPath();

      log.info(
          "Embedded Keycloak started: http://localhost:{}{} to use keycloak",
          port,
          keycloakContextPath);
    };
  }
}
