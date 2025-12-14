package org.todaybook.userservice.userbook.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserId {

  @Column(name = "user_id", nullable = false)
  private UUID id;

  protected UserId(UUID id) {
    this.id = id;
  }

  public static UserId of(UUID id) {
    return new UserId(id);
  }

  public static UserId generateId() {
    return UserId.of(UUID.randomUUID());
  }

  public UUID toUUID() {
    return id;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
