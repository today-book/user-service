package org.todaybook.userservice.userbook.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.todaybook.commoncore.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum UserBookErrorCode implements ErrorCode {
  NOT_FOUND("USERBOOK.NOT_FOUND", HttpStatus.NOT_FOUND.value()),
  ALREADY_EXISTS("USERBOOK.ALREADY_EXISTS", HttpStatus.BAD_REQUEST.value()),
  ;

  private final String code;
  private final int status;
}
