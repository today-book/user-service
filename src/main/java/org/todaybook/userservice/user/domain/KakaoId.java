package org.todaybook.userservice.user.domain;

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
public class KakaoId {

  @Column(name = "kakao_id", nullable = false)
  private Long id;

  protected KakaoId(Long id) {
    this.id = id;
  }

  public static KakaoId of(Long id) {
    return new KakaoId(id);
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
