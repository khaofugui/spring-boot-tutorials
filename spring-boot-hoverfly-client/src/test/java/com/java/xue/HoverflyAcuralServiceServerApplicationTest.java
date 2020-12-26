package com.java.xue;

import io.specto.hoverfly.junit.rule.HoverflyRule;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.HttpBodyConverter.json;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

@ExtendWith(HoverflyExtension.class)
public class HoverflyAcuralServiceServerApplicationTest {
  @Autowired private TestRestTemplate restTemplate;

  @ClassRule
  public static HoverflyRule hoverflyRule =
      HoverflyRule.inSimulationMode(
          dsl(
              service("spring-boot-hoverfly")
                  .get("/service/hoverfly")
                  .willReturn(success(json(ProductsObjectMother.createVehicleProducts())))));
}
