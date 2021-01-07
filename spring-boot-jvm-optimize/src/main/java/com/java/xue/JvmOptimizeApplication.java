package com.java.xue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Set VM Args: -Xmx5440M -Xms5440M -Xss256k -XX:MaxMetaspaceSize=128M -XX:MetaspaceSize=128M
 * -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+ParallelRefProcEnabled
 * -XX:+ExplicitGCInvokesConcurrent -XX:CMSInitiatingOccupancyFraction=70 -Xlog:gc*
 * -XX:+UseCMSInitiatingOccupancyOnly
 *
 * @author xue.zeng
 * @date 2021/1/6 3:35 PM
 */
@SpringBootApplication
public class JvmOptimizeApplication {
  public static void main(final String[] args) {
    SpringApplication.run(JvmOptimizeApplication.class);
  }
}
