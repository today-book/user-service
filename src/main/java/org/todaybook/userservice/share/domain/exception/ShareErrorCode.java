package org.todaybook.userservice.share.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.todaybook.commoncore.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum ShareErrorCode implements ErrorCode {
  BOOK_NOT_FOUND("SHARE.BOOK_NOT_FOUND", HttpStatus.NOT_FOUND.value()),
  ;

  private final String code;
  private final int status;
}
