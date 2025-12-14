package org.todaybook.userservice.user.exception;

import org.todaybook.commoncore.error.AbstractServiceException;

public class UserAlreadyExistsException extends AbstractServiceException {

  public UserAlreadyExistsException(Object... args) {
    super(UserErrorCode.ALREADY_EXISTS, args);
  }
}
