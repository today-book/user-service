package org.todaybook.userservice.userbook.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.todaybook.userservice.userbook.domain.exception.UserBookAlreadyExistsException;
import org.todaybook.userservice.userbook.domain.exception.UserBookNotFoundException;
import org.todaybook.userservice.userbook.domain.repository.UserBookRepository;

@SpringBootTest
@ActiveProfiles("test")
@Import({PostgresContainerConfig.class})
class UserBookManageServiceIT {

  @Autowired private UserBookRepository repository;

  @Autowired private UserBookManageServiceImpl userBookManageService;

  @Test
  @DisplayName("내 책장 저장 성공")
  void test1() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());
    BookSnapshot snapshot = BookFixture.book(bookId.toUUID());

    UserBook saved = userBookManageService.save(userId, snapshot);

    assertNotNull(saved);
    assertEquals(userId, saved.getUserId());
    assertEquals(bookId, saved.getBookId());

    assertTrue(repository.findByUserIdAndBookId(userId, bookId).isPresent());
  }

  @Test
  @DisplayName("내 책장 저장 실패 - 중복 저장")
  void test2() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());
    BookSnapshot snapshot = BookFixture.book(bookId.toUUID());

    userBookManageService.save(userId, snapshot);

    assertThrows(
        UserBookAlreadyExistsException.class, () -> userBookManageService.save(userId, snapshot));
  }

  @Test
  @DisplayName("내 책장 삭제 성공")
  void test3() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());
    BookSnapshot snapshot = BookFixture.book(bookId.toUUID());

    UserBook saved = userBookManageService.save(userId, snapshot);
    Long id = saved.getId();

    userBookManageService.deleteById(id);

    assertFalse(repository.findById(id).isPresent());
  }

  @Test
  @DisplayName("내 책장 삭제 실패 - 존재하지 않는 경우")
  void test4() {
    assertThrows(
        UserBookNotFoundException.class, () -> userBookManageService.deleteById(Long.MAX_VALUE));
  }
}

