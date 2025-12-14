package org.todaybook.userservice.user.application.service;

import java.util.UUID;
import org.todaybook.userservice.user.presentation.dto.UserResponse;

public interface UserService {
  UserResponse getUserById(UUID userId);

  UserResponse getUserByKakaoId(Long kakaoId);

  UserResponse register(Long kakaoId, String nickname);

  void deleteById(UUID userId);
}
