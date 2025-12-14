package org.todaybook.userservice.user.domain.service;

import org.todaybook.userservice.user.domain.KakaoId;
import org.todaybook.userservice.user.domain.User;
import org.todaybook.userservice.user.domain.UserId;

public interface UserManageService {
  User save(KakaoId kakaoId, String nickname);

  void deleteById(UserId userId);
}
