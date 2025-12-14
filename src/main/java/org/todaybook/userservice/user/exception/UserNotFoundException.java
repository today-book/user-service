package org.todaybook.userservice.user.exception;

import org.todaybook.commoncore.error.AbstractServiceException;

public class UserNotFoundException extends AbstractServiceException {

  public UserNotFoundException(Object... args) {
    super(UserErrorCode.NOT_FOUND, args);
  }
}
