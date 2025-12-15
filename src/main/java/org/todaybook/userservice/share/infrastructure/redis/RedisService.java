package org.todaybook.userservice.share.infrastructure.redis;

import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.todaybook.userservice.share.application.service.ShareCacheService;

@Service
@RequiredArgsConstructor
public class RedisService implements ShareCacheService {

  private final StringRedisTemplate redisTemplate;

  @Override
  public void save(String key, String value) {
    redisTemplate.opsForValue().set(key, value, Duration.ofDays(7));
  }

  @Override
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public Optional<String> find(String key) {
    return Optional.ofNullable(redisTemplate.opsForValue().get(key));
  }
}
