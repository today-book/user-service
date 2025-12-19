package org.todaybook.userservice.userbook.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;
import org.todaybook.userservice.global.exception.ErrorIdentifier;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.BookId;

public class UserBookAlreadyExistsException extends AbstractServiceException {

  public UserBookAlreadyExistsException(UserId userId, BookId bookId) {
    super(
        UserBookErrorCode.ALREADY_EXISTS,
        ErrorIdentifier.of("userId", userId).add("bookId", bookId));
  }
}
