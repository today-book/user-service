package org.todaybook.userservice.user.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;
import org.todaybook.userservice.global.exception.ErrorIdentifier;
import org.todaybook.userservice.user.domain.KakaoId;
import org.todaybook.userservice.user.domain.UserId;

public class UserNotFoundException extends AbstractServiceException {

  public UserNotFoundException(UserId userId) {
    super(UserErrorCode.NOT_FOUND, ErrorIdentifier.of("userId", userId));
  }

  public UserNotFoundException(KakaoId kakaoId) {
    super(UserErrorCode.NOT_FOUND, ErrorIdentifier.of("kakaoId", kakaoId));
  }
}
