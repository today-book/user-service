package org.todaybook.userservice.user.exception;

import org.todaybook.commoncore.error.AbstractServiceException;

public class UserInvalidRoleException extends AbstractServiceException {

  public UserInvalidRoleException(String message) {
    super(UserErrorCode.INVALID_ROLE, message);
  }

  public UserInvalidRoleException(Throwable cause, Object... args) {
    super(UserErrorCode.INVALID_ROLE, cause, args);
  }
}
