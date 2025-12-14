package org.todaybook.userservice.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.KakaoId;
import org.todaybook.userservice.user.User;
import org.todaybook.userservice.user.UserId;
import org.todaybook.userservice.user.exception.UserAlreadyExistsException;
import org.todaybook.userservice.user.exception.UserNotFoundException;
import org.todaybook.userservice.user.repository.UserRepository;

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
