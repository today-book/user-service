package org.todaybook.userservice.userbook.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;
import org.todaybook.userservice.global.exception.ErrorIdentifier;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.BookId;

public class UserBookAccessDeniedException extends AbstractServiceException {

  public UserBookAccessDeniedException(Long id) {
    super(UserBookErrorCode.ACCESS_DENIED, ErrorIdentifier.of("id", id));
  }

  public UserBookAccessDeniedException(BookId bookId) {
    super(UserBookErrorCode.ACCESS_DENIED, ErrorIdentifier.of("bookId", bookId));
  }

  public UserBookAccessDeniedException(UserId userId, BookId bookId) {
    super(
        UserBookErrorCode.ACCESS_DENIED,
        ErrorIdentifier.of("userId", userId).add("bookId", bookId));
  }
}
