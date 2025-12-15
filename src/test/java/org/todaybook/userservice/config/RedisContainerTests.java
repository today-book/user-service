package org.todaybook.userservice.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import({PostgresContainerConfig.class, RedisContainerConfig.class})
public class RedisContainerTests {

  @Autowired private StringRedisTemplate redisTemplate;

  @Test
  @DisplayName("Redis Container 연결 및 set/get 테스트")
  void test1() {
    String key = "test:key";
    String value = "Hello redis";

    redisTemplate.opsForValue().set(key, value);

    String result = redisTemplate.opsForValue().get(key);
    assertEquals(value, result);
  }
}
