package org.todaybook.userservice.userbook.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;

public class UserBookAccessDeniedException extends AbstractServiceException {

  public UserBookAccessDeniedException(Object... args) {
    super(UserBookErrorCode.ACCESS_DENIED, args);
  }
}
