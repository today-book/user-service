package org.todaybook.userservice.share.application.service;

import java.util.UUID;

public class ShareCacheKeyBuilder {

  private static final String KEY_PREFIX = "share:";

  public static String tokenKey(UUID token) {
    if (token == null) {
      throw new IllegalArgumentException("토큰(token)은 빈 값일 수 없습니다.");
    }
    return KEY_PREFIX + token;
  }
}
