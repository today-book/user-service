package org.todaybook.userservice.share;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.todaybook.userservice.share.domain.SharedBook;
import org.todaybook.userservice.share.presentation.dto.SharedBookRequest;

public class SharedBookFixture {
  public static SharedBookRequest sharedBookRequest() {
    return new SharedBookRequest(
        UUID.randomUUID(),
        "9800000000001",
        "도서 제목",
        "도서 저자",
        "도서 소개",
        List.of(),
        "출판사",
        LocalDate.now(),
        null,
        0.7,
        "추천 이유");
  }

  public static SharedBook sharedBook() {
    return new SharedBook(
        UUID.randomUUID(),
        "9800000000001",
        "도서 제목",
        "도서 저자",
        "도서 소개",
        List.of(),
        "출판사",
        LocalDate.now(),
        null,
        0.7,
        "추천 이유");
  }
}
