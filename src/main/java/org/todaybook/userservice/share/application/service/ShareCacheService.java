package org.todaybook.userservice.share.application.service;

import java.util.Optional;

public interface ShareCacheService {
  void save(String key, String value);

  void delete(String key);

  Optional<String> find(String key);
}
