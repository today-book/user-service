package org.todaybook.userservice.share.domain.exception;

import org.todaybook.commoncore.error.AbstractServiceException;

public class SharedBookNotFoundException extends AbstractServiceException {

  public SharedBookNotFoundException(Object... args) {
    super(ShareErrorCode.BOOK_NOT_FOUND, args);
  }
}
