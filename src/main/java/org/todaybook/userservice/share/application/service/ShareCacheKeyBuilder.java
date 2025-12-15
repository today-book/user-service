package org.todaybook.userservice.share.application.service;

import java.util.UUID;

public class ShareCacheKeyBuilder {

  private static final String KEY_PREFIX = "share:";

  public static String tokenKey(UUID token) {
    if (token == null) {
      throw new IllegalArgumentException("");
    }
    return KEY_PREFIX + token;
  }
}
