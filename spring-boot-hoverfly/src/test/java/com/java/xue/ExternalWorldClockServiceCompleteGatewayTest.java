package com.java.xue;

import com.java.xue.service.WorldClockServiceGateway;
import com.java.xue.service.impl.ExternalWorldClockServiceGateway;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyCapture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalTime;

/** 服务已经开发完成并处于运行中，通过服务虚拟化工具：Hoverfly，使用捕获模式来生成初始模拟数据集，不用手工生成 */
@ExtendWith(HoverflyExtension.class)
@HoverflyCapture(path = "target/hoverfly", filename = "simulation.json")
public class ExternalWorldClockServiceCompleteGatewayTest {
  @Test
  public void should_get_time_from_external_service() {

    // Given

    final WorldClockServiceGateway worldClockServiceGateway =
        new ExternalWorldClockServiceGateway();

    // When
    LocalTime time = worldClockServiceGateway.getTime("cet");

    // Then
    Assertions.assertThat(time).isNotNull();
  }
}
