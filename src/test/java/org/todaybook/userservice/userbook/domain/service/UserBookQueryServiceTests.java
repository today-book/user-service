package org.todaybook.userservice.userbook.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.UserBook;
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

    List<UserBook> result = service.getUserBooksByUserId(userId);

    assertEquals(2, result.size());
    assertEquals(expected, result);
  }
}
