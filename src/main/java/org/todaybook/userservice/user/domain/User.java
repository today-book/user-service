package org.todaybook.userservice.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@ToString
@Table(schema = "member", name = "p_users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @EmbeddedId private UserId id;

  @Embedded private KakaoId kakaoId;

  @Column(nullable = false)
  private String nickname;

  @JdbcTypeCode(SqlTypes.ARRAY)
  @Column(columnDefinition = "text[]", nullable = false)
  private List<String> role;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime updatedAt;

  public static User create(KakaoId kakaoId, String nickname) {
    validateKakaoId(kakaoId);
    validateNickname(nickname);

    User user = new User();

    user.id = UserId.generateId();
    user.kakaoId = kakaoId;
    user.nickname = nickname;
    user.setRole(List.of(Role.USER));

    return user;
  }

  private void setRole(List<Role> role) {
    this.role = role.stream().map(Role::name).toList();
  }

  public List<Role> getRole() {
    return this.role.stream().map(Role::fromString).toList();
  }

  private static void validateKakaoId(KakaoId kakaoId) {
    if (kakaoId == null || kakaoId.getId() == null) {
      throw new IllegalArgumentException("카카오 아이디(kakaoId)는 빈 값일 수 없습니다.");
    }
  }

  private static void validateNickname(String nickname) {
    if (nickname == null || nickname.isBlank()) {
      throw new IllegalArgumentException("닉네임(nickname)은 빈 값일 수 없습니다.");
    }
  }
}
