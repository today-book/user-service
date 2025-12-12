package org.todaybook.userservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.todaybook.userservice.domain.dto.Book;

@Entity
@Getter
@ToString
@Table(schema = "user", name = "p_user_books")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBook {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded private UserId userId;

  @Embedded private BookId bookId;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(columnDefinition = "jsonb", nullable = false)
  private Book book;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime updatedAt;

  public static UserBook create(UserId userId, Book book) {
    validateUserId(userId);
    validateBook(book);

    UserBook userBook = new UserBook();

    userBook.userId = userId;
    userBook.bookId = BookId.of(book.id());
    userBook.book = book;

    return userBook;
  }

  public UserBook updateBook(Book book) {
    validateBookId(this.bookId, book);
    validateBook(book);

    this.book = book;

    return this;
  }

  public static void validateUserId(UserId userId) {
    if (userId == null || userId.getId() == null) {
      throw new IllegalArgumentException("회원 아이디(userId)는 비어있을 수 없습니다.");
    }
  }

  public static void validateBook(Book book) {
    if (book.id() == null) {
      throw new IllegalArgumentException("도서 아이디(bookId)는 비어있을 수 없습니다.");
    }

    if (book.isbn() == null || book.isbn().isBlank()) {
      throw new IllegalArgumentException("도서 고유번호(isbn)는 비어있을 수 없습니다.");
    }

    if (book.title() == null || book.title().isBlank()) {
      throw new IllegalArgumentException("도서 제목(title)은 비어있을 수 없습니다.");
    }

    if (book.author() == null || book.author().isBlank()) {
      throw new IllegalArgumentException("도서 저자(author)는 비어있을 수 없습니다.");
    }

    if (book.description() == null || book.description().isBlank()) {
      throw new IllegalArgumentException("도서 소개(description)는 비어있을 수 없습니다.");
    }
  }

  public void validateBookId(BookId bookId, Book book) {
    if (!bookId.equals(BookId.of(book.id()))) {
      throw new IllegalStateException("도서 아이디(bookId)는 수정할 수 없습니다.");
    }
  }
}
