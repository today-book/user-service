package org.todaybook.userservice.share.domain;

import java.util.UUID;

public record ShareToken(UUID token) {
  public static ShareToken create() {
    return new ShareToken(UUID.randomUUID());
  }
}
