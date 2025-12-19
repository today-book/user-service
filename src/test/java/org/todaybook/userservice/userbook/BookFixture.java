package org.todaybook.userservice.userbook;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.todaybook.userservice.userbook.domain.BookSnapshot;

public class BookFixture {

  public static BookSnapshot book(UUID id) {
    return BookSnapshot.builder()
        .bookId(id)
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

  public static BookSnapshot invalidBook(UUID id) {
    return BookSnapshot.builder().bookId(id).build();
  }
}
