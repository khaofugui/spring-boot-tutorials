package com.java.xue;

import com.java.xue.service.WorldClockServiceGateway;
import com.java.xue.service.impl.ExternalWorldClockServiceGateway;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit.dsl.HoverflyDsl;
import io.specto.hoverfly.junit.verification.HoverflyVerifications;
import io.specto.hoverfly.junit5.HoverflyExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

@Slf4j
@ExtendWith(HoverflyExtension.class)
public class ExternalWorldClockServiceDelayRespGatewayTest {
  private static final String OUTPUT =
      "{\n"
          + "  \"$id\": \"1\",\n"
          + "  \"currentDateTime\": \"2020-12-09T10:05+01:00\",\n"
          + "  \"utcOffset\": \"01:00:00\",\n"
          + "  \"isDayLightSavingsTime\": false,\n"
          + "  \"dayOfTheWeek\": \"Wednesday\",\n"
          + "  \"timeZoneName\": \"Central Europe Standard Time\",\n"
          + "  \"currentFileTime\": 132519819030108720,\n"
          + "  \"ordinalDate\": \"2020-344\",\n"
          + "  \"serviceResponse\": null\n"
          + "}";

  @Test
  public void should_get_time_from_external_service(Hoverfly hoverfly) {
    // Given 模拟延迟并测试我们的服务是否正确地处理了延迟
    log.info("start time: {}", LocalTime.now());
    hoverfly.simulate(
        SimulationSource.dsl(
            HoverflyDsl.service("http://worldclockapi.com")
                .get("/api/json/cet/now")
                .willReturn(success(OUTPUT, "application/json").withDelay(5, TimeUnit.SECONDS))));

    final WorldClockServiceGateway worldClockServiceGateway =
            new ExternalWorldClockServiceGateway();

    // When
    LocalTime time = worldClockServiceGateway.getTime("cet");

    log.info("delay time: {}", LocalTime.now());

    // 验证是否已经向远程服务端点发出特定请求
    hoverfly.verify(
        HoverflyDsl.service("http://worldclockapi.com").get("/api/json/cet/now"),
        HoverflyVerifications.times(1));
  }
}
