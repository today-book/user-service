package org.todaybook.userservice.userbook.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UserBookRequest(
    @NotNull UUID bookId,
    @NotBlank String isbn,
    @NotBlank String title,
    @NotBlank String author,
    @NotBlank String description,
    List<String> categories,
    String publisher,
    LocalDate publishedAt,
    String thumbnail,
    Double score,
    String reason) {}
