package org.todaybook.userservice.share.infrastructure.redis;

import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.todaybook.userservice.share.application.ShareCacheRepository;

@Service
@RequiredArgsConstructor
public class RedisRepository implements ShareCacheRepository {

  private final StringRedisTemplate redisTemplate;

  @Override
  public void save(String key, String value) {
    save(key, value, Duration.ofDays(7));
  }

  @Override
  public void save(String key, String value, Duration duration) {
    redisTemplate.opsForValue().set(key, value, duration);
  }

  @Override
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public Optional<String> get(String key) {
    return Optional.ofNullable(redisTemplate.opsForValue().get(key));
  }
}
