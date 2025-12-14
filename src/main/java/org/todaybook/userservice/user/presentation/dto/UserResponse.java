package org.todaybook.userservice.user.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.todaybook.userservice.user.domain.Role;
import org.todaybook.userservice.user.domain.User;

public record UserResponse(
    UUID id,
    Long kakaoId,
    String nickname,
    List<String> role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {
  public static UserResponse from(User user) {
    return new UserResponse(
        user.getId().toUUID(),
        user.getKakaoId().getId(),
        user.getNickname(),
        user.getRole().stream().map(Role::name).toList(),
        user.getCreatedAt(),
        user.getUpdatedAt());
  }
}
