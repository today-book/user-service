package org.todaybook.userservice.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.todaybook.commoncore.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
  NOT_FOUND("USER.NOT_FOUND", HttpStatus.NOT_FOUND.value()),
  ALREADY_EXISTS("USER.ALREADY_EXISTS", HttpStatus.CONFLICT.value()),
  INVALID_ROLE("USER.INVALID_ROLE", HttpStatus.BAD_REQUEST.value()),
  ;

  private final String code;
  private final int status;
}
