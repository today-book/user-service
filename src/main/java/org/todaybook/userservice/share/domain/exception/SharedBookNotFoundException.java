package org.todaybook.userservice.share.domain.exception;

import java.util.UUID;
import org.todaybook.commoncore.error.AbstractServiceException;
import org.todaybook.userservice.global.exception.ErrorIdentifier;

public class SharedBookNotFoundException extends AbstractServiceException {

  public SharedBookNotFoundException(UUID token) {
    super(ShareErrorCode.BOOK_NOT_FOUND, ErrorIdentifier.of("token", token));
  }
}
