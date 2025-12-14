package org.todaybook.userservice.user.service;

import org.todaybook.userservice.user.KakaoId;
import org.todaybook.userservice.user.User;
import org.todaybook.userservice.user.UserId;

public interface UserQueryService {
  User getUserById(UserId userId);

  User getUserByKakaoId(KakaoId kakaoId);
}
