package org.todaybook.userservice.userbook.application.dto;

import org.todaybook.userservice.userbook.domain.Book;
import org.todaybook.userservice.userbook.presentation.dto.UserBookRequest;

public class UserBookMapper {

  public static Book toBook(UserBookRequest request) {
    return new Book(
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
