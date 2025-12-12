package org.todaybook.userservice.userbook.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
  private Long id;

  protected UserId(Long id) {
    this.id = id;
  }

  public static UserId of(Long id) {
    return new UserId(id);
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
