package org.todaybook.userservice.userbook.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.todaybook.userservice.BookFixture;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.user.UserId;
import org.todaybook.userservice.userbook.domain.dto.Book;
import org.todaybook.userservice.userbook.domain.exception.UserBookAlreadyExistsException;
import org.todaybook.userservice.userbook.domain.exception.UserBookNotFoundException;
import org.todaybook.userservice.userbook.domain.repository.UserBookRepository;

@ExtendWith(MockitoExtension.class)
class UserBookManageServiceTests {

  @Mock private UserBookRepository repository;

  @InjectMocks private UserBookManageServiceImpl userBookManageService;

  @Test
  @DisplayName("내 책장 저장 성공")
  void test1() {
    UserId userId = UserId.generateId();

    BookId bookId = BookId.of(UUID.randomUUID());
    Book book = BookFixture.book(bookId.toUUID());

    when(repository.findByUserIdAndBookId(userId, bookId)).thenReturn(Optional.empty());
    when(repository.save(any(UserBook.class))).thenAnswer(inv -> inv.getArgument(0));

    UserBook result = userBookManageService.save(userId, book);

    assertNotNull(result);
    verify(repository).save(any(UserBook.class));
  }

  @Test
  @DisplayName("내 책장 저장 실패 - 이미 존재하는 경우")
  void test2() {
    UserId userId = UserId.generateId();

    BookId bookId = BookId.of(UUID.randomUUID());
    Book book = BookFixture.book(bookId.toUUID());

    UserBook existing = UserBook.create(userId, book);

    when(repository.findByUserIdAndBookId(userId, bookId)).thenReturn(Optional.of(existing));

    assertThrows(
        UserBookAlreadyExistsException.class, () -> userBookManageService.save(userId, book));
    verify(repository, never()).save(any());
  }

  @Test
  @DisplayName("내 책장 삭제 성공")
  void test3() {
    Long id = 1L;

    UserBook existing = mock(UserBook.class);

    when(repository.findById(id)).thenReturn(Optional.of(existing));

    userBookManageService.deleteById(id);

    verify(repository).deleteById(id);
  }

  @Test
  @DisplayName("내 책장 삭제 실패 - 존재하지 않는 경우")
  void test4() {
    Long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(UserBookNotFoundException.class, () -> userBookManageService.deleteById(id));

    verify(repository, never()).deleteById(any());
  }
}
