package org.todaybook.userservice.userbook.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.todaybook.userservice.config.PostgresContainerConfig;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.BookFixture;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.BookSnapshot;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.exception.UserBookNotFoundException;
import org.todaybook.userservice.userbook.domain.repository.UserBookRepository;

@SpringBootTest
@ActiveProfiles("test")
@Import({PostgresContainerConfig.class})
class UserBookQueryServiceIT {

  @Autowired private UserBookRepository repository;

  @Autowired private UserBookQueryService userBookQueryService;

  @Test
  @DisplayName("ID로 책장 조회 성공")
  void test1() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());
    BookSnapshot snapshot = BookFixture.book(bookId.toUUID());

    UserBook saved = repository.save(UserBook.create(userId, snapshot));

    UserBook result = userBookQueryService.getUserBookById(saved.getId());

    assertNotNull(result);
    assertEquals(saved.getId(), result.getId());
  }

  @Test
  @DisplayName("ID로 책장 조회 실패")
  void test2() {
    assertThrows(
        UserBookNotFoundException.class,
        () -> userBookQueryService.getUserBookById(Long.MAX_VALUE));
  }

  @Test
  @DisplayName("내 책장 전체 조회 성공")
  void test3() {
    UserId userId = UserId.generateId();

    BookSnapshot snapshot1 = BookFixture.book(UUID.randomUUID());
    BookSnapshot snapshot2 = BookFixture.book(UUID.randomUUID());

    repository.save(UserBook.create(userId, snapshot1));
    repository.save(UserBook.create(userId, snapshot2));

    List<UserBook> result = userBookQueryService.getUserBooksByUserId(userId);

    assertEquals(2, result.size());
  }
}
