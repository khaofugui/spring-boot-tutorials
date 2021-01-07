package com.java.xue.controller;

import com.java.xue.model.dto.HoverflyServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class HoverflyRestController {
  @RequestMapping(value = "/service/hoverfly")
  public HoverflyServiceResponse getSampleResponse() {
    log.info("Inside HoverflyActualServiceApplication::getSampleResponse()");
    return new HoverflyServiceResponse("returned value from HoverflyActualServiceApplication", new Date().toString(), UUID.randomUUID().toString());
  }
}
