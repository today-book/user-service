package org.todaybook.userservice.user.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;
import org.todaybook.userservice.global.exception.ErrorIdentifier;

public class UserInvalidRoleException extends AbstractServiceException {

  public UserInvalidRoleException() {
    super(UserErrorCode.INVALID_ROLE, "역할(role)은 빈 값일 수 없습니다.");
  }

  public UserInvalidRoleException(String value) {
    super(UserErrorCode.INVALID_ROLE, ErrorIdentifier.of("value", value));
  }
}
