package org.todaybook.userservice.user.domain;

import lombok.Getter;
import org.todaybook.userservice.user.domain.exception.UserInvalidRoleException;

@Getter
public enum Role {
  USER,
  ADMIN;

  public static Role fromString(String role) {
    if (role == null || role.isBlank()) {
      throw new UserInvalidRoleException("역할(role)은 빈 값일 수 없습니다.");
    }

    try {
      return Role.valueOf(role.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new UserInvalidRoleException(e, role);
    }
  }
}
