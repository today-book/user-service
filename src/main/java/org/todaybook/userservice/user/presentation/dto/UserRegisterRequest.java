package org.todaybook.userservice.user.presentation.dto;

import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(@NotNull Long kakaoId, String nickname) {}
