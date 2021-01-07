package com.java.xue;

import com.java.xue.service.WorldClockServiceGateway;
import com.java.xue.service.impl.ExternalWorldClockServiceGateway;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit.dsl.HoverflyDsl;
import io.specto.hoverfly.junit5.HoverflyExtension;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalTime;

import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

/** 当服务还未开发，使用服务虚拟化工具：Hoverfly，并提供预设响应 */
@Slf4j
@ExtendWith(HoverflyExtension.class)
public class ExternalWorldClockServiceGatewayTest {
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
    // Given
    hoverfly.simulate(
        SimulationSource.dsl(
            HoverflyDsl.service("http://worldclockapi.com")
                .get("/api/json/cet/now")
                .willReturn(success(OUTPUT, "application/json"))));

    final WorldClockServiceGateway worldClockServiceGateway =
        new ExternalWorldClockServiceGateway();

    // When
    LocalTime time = worldClockServiceGateway.getTime("cet");

    log.info("time: {}", time);

    Assertions.assertThat(time.getHour()).isEqualTo(10);
    Assertions.assertThat(time.getMinute()).isEqualTo(5);
  }
}
