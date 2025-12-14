package org.todaybook.userservice.userbook.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.UserId;
import org.todaybook.userservice.userbook.domain.exception.UserBookNotFoundException;
import org.todaybook.userservice.userbook.domain.repository.UserBookRepository;

@ExtendWith(MockitoExtension.class)
class UserBookQueryServiceTests {

  @Mock private UserBookRepository repository;

  @InjectMocks private UserBookQueryServiceImpl service;

  @Test
  @DisplayName("ID로 책장 조회 성공")
  void test1() {
    Long id = 1L;
    UserBook userBook = mock(UserBook.class);

    when(repository.findById(id)).thenReturn(Optional.of(userBook));

    UserBook result = service.getUserBookById(id);

    assertNotNull(result);
    assertEquals(userBook, result);
  }

  @Test
  @DisplayName("ID로 책장 조회 실패")
  void test2() {
    Long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(UserBookNotFoundException.class, () -> service.getUserBookById(id));
  }

  @Test
  @DisplayName("사용자의 책장 전체 조회 성공")
  void test3() {
    UserId userId = UserId.generateId();
    List<UserBook> expected = Arrays.asList(mock(UserBook.class), mock(UserBook.class));

    when(repository.findByUserId(userId)).thenReturn(expected);

    List<UserBook> result = service.getUserBooks(userId);

    assertEquals(2, result.size());
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("내 책장 등록 여부 확인 - 저장된 경우")
  void test4() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());

    when(repository.findByUserIdAndBookId(userId, bookId))
        .thenReturn(Optional.of(mock(UserBook.class)));

    boolean result = service.isSavedBook(userId, bookId);

    assertTrue(result);
  }

  @Test
  @DisplayName("내 책장 등록 여부 확인 - 저장되지 않은 경우")
  void test5() {
    UserId userId = UserId.generateId();
    BookId bookId = BookId.of(UUID.randomUUID());

    when(repository.findByUserIdAndBookId(userId, bookId)).thenReturn(Optional.empty());

    boolean result = service.isSavedBook(userId, bookId);

    assertFalse(result);
  }

  @Test
  @DisplayName("여러 BookId 중 저장된 책만 반환")
  void test6() {
    UserId userId = UserId.generateId();

    BookId bookId1 = BookId.of(UUID.randomUUID());
    BookId bookId2 = BookId.of(UUID.randomUUID());
    BookId bookId3 = BookId.of(UUID.randomUUID());

    UserBook userBook1 = mock(UserBook.class);
    UserBook userBook2 = mock(UserBook.class);

    when(userBook1.getBookId()).thenReturn(bookId1);
    when(userBook2.getBookId()).thenReturn(bookId2);

    List<BookId> input = Arrays.asList(bookId1, bookId2, bookId3);
    List<UserBook> found = Arrays.asList(userBook1, userBook2);

    when(repository.findByUserIdAndBookIdIn(userId, input)).thenReturn(found);

    Set<BookId> result = service.getSavedBooksByBookIds(userId, input);

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

    when(repository.findByUserIdAndBookIdIn(userId, input)).thenReturn(Collections.emptyList());

    Set<BookId> result = service.getSavedBooksByBookIds(userId, input);

    assertTrue(result.isEmpty());
  }
}
