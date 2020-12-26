package com.java.xue.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class HoverflyRestController {
  @Autowired
  RestTemplate restTemplate;

  @RequestMapping("/invoke")
  private String invoke() {
    log.info("Inside HoverflyRestController::invoke");
    String url = "http://127.0.0.1:9080/service/hoverfly";
    String response = restTemplate.exchange(url, HttpMethod.GET, null,
            new ParameterizedTypeReference<String>() {
            }).getBody();
    log.info("Actual Response : {}", response);
    return response;
  }
}
