package org.todaybook.userservice.userbook.presentation.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import org.todaybook.userservice.userbook.domain.Book;
import org.todaybook.userservice.userbook.domain.UserBook;

public record UserBookResponse(
    Long id,
    UUID userId,
    UUID bookId,
    Book book,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {
  public static UserBookResponse from(UserBook userBook) {
    return new UserBookResponse(
        userBook.getId(),
        userBook.getUserId().toUUID(),
        userBook.getBookId().toUUID(),
        userBook.getBook(),
        userBook.getCreatedAt(),
        userBook.getUpdatedAt());
  }
}
