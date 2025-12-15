package org.todaybook.userservice.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(@NotNull Long kakaoId, @NotBlank String nickname) {}
