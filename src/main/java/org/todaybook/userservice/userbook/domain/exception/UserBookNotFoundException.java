package org.todaybook.userservice.userbook.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;
import org.todaybook.userservice.global.exception.ErrorIdentifier;
import org.todaybook.userservice.userbook.domain.BookId;

public class UserBookNotFoundException extends AbstractServiceException {

  public UserBookNotFoundException(Long id) {
    super(UserBookErrorCode.NOT_FOUND, ErrorIdentifier.of("id", id));
  }

  public UserBookNotFoundException(BookId bookId) {
    super(UserBookErrorCode.NOT_FOUND, ErrorIdentifier.of("bookId", bookId));
  }
}
