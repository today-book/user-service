package org.todaybook.userservice.share.presentation.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.todaybook.userservice.share.domain.SharedBook;

public record SharedBookResponse(
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
    String reason) {
  public static SharedBookResponse from(SharedBook sharedBook) {
    return new SharedBookResponse(
        sharedBook.bookId(),
        sharedBook.isbn(),
        sharedBook.title(),
        sharedBook.author(),
        sharedBook.description(),
        sharedBook.categories(),
        sharedBook.publisher(),
        sharedBook.publishedAt(),
        sharedBook.thumbnail(),
        sharedBook.score(),
        sharedBook.reason());
  }
}
