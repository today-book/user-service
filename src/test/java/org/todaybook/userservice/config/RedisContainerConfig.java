package org.todaybook.userservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration
public class RedisContainerConfig {

  @Bean
  @ServiceConnection(name = "redis")
  public GenericContainer<?> redisContainer() {
    return new GenericContainer<>("redis:7").withExposedPorts(6379);
  }
}
