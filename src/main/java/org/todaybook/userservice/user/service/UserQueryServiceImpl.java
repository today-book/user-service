package org.todaybook.userservice.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.KakaoId;
import org.todaybook.userservice.user.User;
import org.todaybook.userservice.user.UserId;
import org.todaybook.userservice.user.exception.UserNotFoundException;
import org.todaybook.userservice.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

  private final UserRepository repository;

  @Override
  public User getUserById(UserId userId) {
    return repository
        .findById(userId)
        .orElseThrow(() -> new UserNotFoundException("userId", userId.toString()));
  }

  @Override
  public User getUserByKakaoId(KakaoId kakaoId) {
    return repository
        .findByKakaoId(kakaoId)
        .orElseThrow(() -> new UserNotFoundException("kakaoId", kakaoId.toString()));
  }
}
