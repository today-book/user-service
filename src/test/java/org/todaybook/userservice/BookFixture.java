package org.todaybook.userservice;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.UserId;
import org.todaybook.userservice.userbook.domain.dto.Book;

public class BookFixture {

  public static UserBook userBook(Long id, BookId bookId) {
    return UserBook.create(UserId.of(id), book(bookId.toUUID()));
  }

  public static Book book(UUID id) {
    return Book.builder()
        .id(id)
        .isbn("9800000000001")
        .title("도서 제목")
        .author("도서 저자")
        .description("도서 소개")
        .categories(List.of())
        .publisher("출판사")
        .publishedAt(LocalDate.now())
        .score(null)
        .reason(null)
        .build();
  }

  public static Book invalidBook(UUID id) {
    return Book.builder().id(id).build();
  }
}
