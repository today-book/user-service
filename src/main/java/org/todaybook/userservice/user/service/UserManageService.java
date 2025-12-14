package org.todaybook.userservice.user.service;

import org.todaybook.userservice.user.KakaoId;
import org.todaybook.userservice.user.User;
import org.todaybook.userservice.user.UserId;

public interface UserManageService {
  User save(KakaoId kakaoId, String nickname);

  void deleteById(UserId userId);
}
