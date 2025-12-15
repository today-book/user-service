package org.todaybook.userservice.userbook.presentation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record UserBookSavedRequest(@NotNull UUID userId, @NotNull List<UUID> bookIds) {}
