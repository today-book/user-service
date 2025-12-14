package org.todaybook.userservice.userbook.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;

public class UserBookAlreadyExistsException extends AbstractServiceException {

  public UserBookAlreadyExistsException(Object... args) {
    super(UserBookErrorCode.ALREADY_EXISTS, args);
  }
}
