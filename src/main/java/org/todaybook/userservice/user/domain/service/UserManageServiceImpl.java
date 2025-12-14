package org.todaybook.userservice.user.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.domain.KakaoId;
import org.todaybook.userservice.user.domain.User;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.user.domain.exception.UserAlreadyExistsException;
import org.todaybook.userservice.user.domain.exception.UserNotFoundException;
import org.todaybook.userservice.user.domain.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserManageServiceImpl implements UserManageService {

  private final UserRepository repository;

  @Override
  public User save(KakaoId kakaoId, String nickname) {
    repository
        .findByKakaoId(kakaoId)
        .ifPresent(
            user -> {
              throw new UserAlreadyExistsException(kakaoId);
            });

    User user = User.create(kakaoId, nickname);

    return repository.save(user);
  }

  @Override
  public void deleteById(UserId userId) {
    repository
        .findById(userId)
        .orElseThrow(() -> new UserNotFoundException("userId", userId.toString()));

    repository.deleteById(userId);
  }
}
