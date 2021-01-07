package com.java.xue;

import com.java.xue.service.WorldClockServiceGateway;
import com.java.xue.service.impl.ExternalWorldClockServiceGateway;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalTime;

/**
 * 当存储了模拟数据之后，就可以转换到模拟模式，不再与实际服务进一步通信 <br/>
 * 设置enableAutoCapture=true可以把HoverflyExtension设置为在模拟和捕获模式之间自动切换。如果没有找到源，那么，它将运行于捕获模式，否则，使用模拟模式
 */
@Slf4j
@ExtendWith(HoverflyExtension.class)
@HoverflySimulate(
    source =
        @HoverflySimulate.Source(
            value = "target/hoverfly/simulation.json",
            type = HoverflySimulate.SourceType.FILE), enableAutoCapture = true)
public class ExternalWorldClockServiceOfflineGatewayTest {
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
