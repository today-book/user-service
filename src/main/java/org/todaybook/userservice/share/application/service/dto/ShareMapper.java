package org.todaybook.userservice.share.application.service.dto;

import org.todaybook.userservice.share.domain.SharedBook;
import org.todaybook.userservice.share.presentation.dto.SharedBookRequest;

public class ShareMapper {

  public static SharedBook toSharedBook(SharedBookRequest request) {
    return new SharedBook(
        request.bookId(),
        request.isbn(),
        request.title(),
        request.author(),
        request.description(),
        request.categories(),
        request.publisher(),
        request.publishedAt(),
        request.thumbnail(),
        request.score(),
        request.reason());
  }
}
