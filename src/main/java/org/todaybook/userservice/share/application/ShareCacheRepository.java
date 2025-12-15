package org.todaybook.userservice.share.application;

import java.time.Duration;
import java.util.Optional;

public interface ShareCacheRepository {
  void save(String key, String value);

  void save(String key, String value, Duration duration);

  void delete(String key);

  Optional<String> get(String key);
}
