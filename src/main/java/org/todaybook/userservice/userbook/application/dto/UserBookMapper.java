package org.todaybook.userservice.userbook.application.dto;

import org.todaybook.userservice.userbook.domain.BookSnapshot;
import org.todaybook.userservice.userbook.presentation.dto.UserBookRequest;

public class UserBookMapper {

  public static BookSnapshot toBook(UserBookRequest request) {
    return new BookSnapshot(
        request.bookId(),
        request.isbn(),
        request.author(),
        request.title(),
        request.description(),
        request.categories(),
        request.publisher(),
        request.publishedAt(),
        request.thumbnail(),
        request.score(),
        request.reason());
  }
}
