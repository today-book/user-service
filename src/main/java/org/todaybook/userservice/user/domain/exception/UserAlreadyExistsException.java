package org.todaybook.userservice.user.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;
import org.todaybook.userservice.global.exception.ErrorIdentifier;
import org.todaybook.userservice.user.domain.KakaoId;
import org.todaybook.userservice.user.domain.UserId;

public class UserAlreadyExistsException extends AbstractServiceException {

  public UserAlreadyExistsException(UserId userId) {
    super(UserErrorCode.ALREADY_EXISTS, ErrorIdentifier.of("userId", userId));
  }

  public UserAlreadyExistsException(KakaoId kakaoId) {
    super(UserErrorCode.ALREADY_EXISTS, ErrorIdentifier.of("kakaoId", kakaoId));
  }
}
