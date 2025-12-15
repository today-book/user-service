package org.todaybook.userservice.share.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record SharedBook(
    UUID bookId,
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
