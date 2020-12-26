package com.java.xue;

import com.java.xue.service.WorldClockServiceGateway;
import com.java.xue.service.impl.ExternalWorldClockServiceGateway;
import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflyDiff;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalTime;

/**
 * Hoverfly的Diff模式：将请求转发给远程服务，并与本地所存的模拟数据的响应进行比较。<br>
 * 当Hoverfly结束两个响应的比较后，存储两者的差异，并向新的请求提供来自远程服务的实际响应<br>
 * 工作流程：
 * <p>运行Diff模式测试。
 *
 * <p>如果发生失败的话，那么删除过时的模拟数据。
 *
 * <p>触发一个新的服务构建任务，迫使Hoverfly重新捕获模拟数据。
 *
 * <p>构建可能失败，原因是新捕获的数据与之前捕获的数据不同。然后，开发人员观察到服务出现故障，他们可以开始修复代码以适应新的服务输出。
 */
@ExtendWith(HoverflyExtension.class)
@HoverflyDiff(
    source =
        @HoverflySimulate.Source(
            value = "target/hoverfly/simulation.json",
            type = HoverflySimulate.SourceType.CLASSPATH))
public class ExternalWorldClockServiceDiffGatewayTest {
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
