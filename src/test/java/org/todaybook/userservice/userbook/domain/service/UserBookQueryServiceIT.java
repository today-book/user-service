package org.todaybook.userservice.userbook.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.todaybook.userservice.BookFixture;
import org.todaybook.userservice.config.PostgresContainerConfig;
import org.todaybook.userservice.user.UserId;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.dto.Book;
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
    Book book = BookFixture.book(bookId.toUUID());

    UserBook saved = repository.save(UserBook.create(userId, book));

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

    Book book1 = BookFixture.book(UUID.randomUUID());
    Book book2 = BookFixture.book(UUID.randomUUID());

    repository.save(UserBook.create(userId, book1));
    repository.save(UserBook.create(userId, book2));

    List<UserBook> result = userBookQueryService.getUserBooks(userId);

    assertEquals(2, result.size());
  }

  @Test
  @DisplayName("내 책장 등록 여부 확인 - 저장된 경우")
  void test4() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());
    Book book = BookFixture.book(bookId.toUUID());

    repository.save(UserBook.create(userId, book));

    boolean result = userBookQueryService.isSavedBook(userId, bookId);

    assertTrue(result);
  }

  @Test
  @DisplayName("내 책장 등록 여부 확인 - 저장되지 않은 경우")
  void test5() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());

    boolean result = userBookQueryService.isSavedBook(userId, bookId);

    assertFalse(result);
  }

  @Test
  @DisplayName("여러 BookId 중 저장된 책만 반환")
  void test6() {
    UserId userId = UserId.generateId();

    BookId bookId1 = BookId.of(UUID.randomUUID());
    BookId bookId2 = BookId.of(UUID.randomUUID());
    BookId bookId3 = BookId.of(UUID.randomUUID());

    repository.save(UserBook.create(userId, BookFixture.book(bookId1.toUUID())));
    repository.save(UserBook.create(userId, BookFixture.book(bookId2.toUUID())));

    List<BookId> input = Arrays.asList(bookId1, bookId2, bookId3);

    Set<BookId> result = userBookQueryService.getSavedBooksByBookIds(userId, input);

    assertEquals(2, result.size());
    assertTrue(result.contains(bookId1));
    assertTrue(result.contains(bookId2));
    assertFalse(result.contains(bookId3));
  }

  @Test
  @DisplayName("여러 BookId 모두 저장되지 않은 경우 빈 Set 반환")
  void test7() {
    UserId userId = UserId.generateId();

    List<BookId> input = Arrays.asList(BookId.of(UUID.randomUUID()), BookId.of(UUID.randomUUID()));

    Set<BookId> result = userBookQueryService.getSavedBooksByBookIds(userId, input);

    assertTrue(result.isEmpty());
  }
}
