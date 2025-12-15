package org.todaybook.userservice.userbook.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.BookFixture;

@ActiveProfiles("test")
class UserBookTests {

  @Test
  @DisplayName("UserBook 생성 성공")
  void test1() {
    UserId userId = UserId.generateId();

    BookId bookId = BookId.of(UUID.randomUUID());
    Book book = BookFixture.book(bookId.toUUID());

    UserBook userBook = UserBook.create(userId, book);

    assertEquals(userId, userBook.getUserId());
    assertEquals(bookId, userBook.getBookId());
    assertEquals(book, userBook.getBook());
  }

  @Test
  @DisplayName("UserBook 생성 실패 - userId null")
  void test2() {
    BookId bookId = BookId.of(UUID.randomUUID());
    Book book = BookFixture.book(bookId.toUUID());

    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> UserBook.create(null, book));

    System.out.println("message: " + exception.getMessage());
  }

  @Test
  @DisplayName("UserBook 생성 실패 - book 필수 값 누락")
  void test3() {
    Book book = BookFixture.invalidBook(null);

    Exception exception =
        assertThrows(
            IllegalArgumentException.class, () -> UserBook.create(UserId.generateId(), book));

    System.out.println("message: " + exception.getMessage());
  }

  @Test
  @DisplayName("updateBook 성공 - 동일한 bookId일 경우")
  void test4() {
    UUID bookId = UUID.randomUUID();

    Book book1 = BookFixture.book(bookId);
    UserBook userBook = UserBook.create(UserId.generateId(), book1);

    Book book2 = BookFixture.book(bookId);

    UserBook updated = userBook.updateBook(book2);

    assertEquals(updated.getBook(), book2);
  }

  @Test
  @DisplayName("updateBook 실패 - 도서 아이디(bookId)가 다를 경우")
  void test5() {
    Book book1 = BookFixture.book(UUID.randomUUID());
    UserBook userBook = UserBook.create(UserId.generateId(), book1);

    Book book2 = BookFixture.book(UUID.randomUUID());

    Exception exception =
        assertThrows(IllegalStateException.class, () -> userBook.updateBook(book2));

    System.out.println("message: " + exception.getMessage());
  }

  @Test
  @DisplayName("updateBook 실패 - book 필수 정보 누락")
  void test6() {
    UUID bookId = UUID.randomUUID();

    Book book1 = BookFixture.book(bookId);
    UserBook userBook = UserBook.create(UserId.generateId(), book1);

    Book book2 = BookFixture.invalidBook(bookId);

    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> userBook.updateBook(book2));

    System.out.println("message: " + exception.getMessage());
  }
}
