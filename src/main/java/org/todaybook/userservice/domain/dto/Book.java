package org.todaybook.userservice.domain.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record Book(
    UUID id,
    String isbn,
    String title,
    String author,
    String description,
    List<String> categories,
    String publisher,
    LocalDate publishedAt,
    String thumbnail,
    Double score,
    String reason) {}
