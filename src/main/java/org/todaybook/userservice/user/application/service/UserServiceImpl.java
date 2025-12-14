package org.todaybook.userservice.user.application.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.domain.KakaoId;
import org.todaybook.userservice.user.domain.User;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.user.domain.service.UserManageService;
import org.todaybook.userservice.user.domain.service.UserQueryService;
import org.todaybook.userservice.user.presentation.dto.UserResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserQueryService userQueryService;
  private final UserManageService userManageService;

  @Override
  @Transactional(readOnly = true)
  public UserResponse getUserById(UUID userId) {
    User user = userQueryService.getUserById(UserId.of(userId));
    return UserResponse.from(user);
  }

  @Override
  @Transactional(readOnly = true)
  public UserResponse getUserByKakaoId(Long kakaoId) {
    User user = userQueryService.getUserByKakaoId(KakaoId.of(kakaoId));
    return UserResponse.from(user);
  }

  @Override
  public UserResponse register(Long kakaoId, String nickname) {
    User user = userManageService.save(KakaoId.of(kakaoId), nickname);
    return UserResponse.from(user);
  }

  @Override
  public void deleteById(UUID userId) {
    userManageService.deleteById(UserId.of(userId));
  }
}
