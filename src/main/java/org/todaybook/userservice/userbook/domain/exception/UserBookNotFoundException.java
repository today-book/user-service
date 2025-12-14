package org.todaybook.userservice.userbook.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;

public class UserBookNotFoundException extends AbstractServiceException {

  public UserBookNotFoundException(Object... args) {
    super(UserBookErrorCode.NOT_FOUND, args);
  }
}
