package org.todaybook.userservice.user.domain.service;

import org.todaybook.userservice.user.domain.KakaoId;
import org.todaybook.userservice.user.domain.User;
import org.todaybook.userservice.user.domain.UserId;

public interface UserQueryService {
  User getUserById(UserId userId);

  User getUserByKakaoId(KakaoId kakaoId);
}
