package org.todaybook.userservice.userbook.domain;

import jakarta.persistence.AttributeOverride;
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
import org.todaybook.userservice.user.domain.UserId;

@Entity
@Getter
@ToString
@Table(schema = "member", name = "p_user_books")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBook {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  @AttributeOverride(name = "id", column = @Column(name = "user_id"))
  private UserId userId;

  @Embedded private BookId bookId;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(columnDefinition = "jsonb", nullable = false)
  private BookSnapshot snapshot;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime updatedAt;

  public static UserBook create(UserId userId, BookSnapshot snapshot) {
    validateUserId(userId);
    validateBook(snapshot);

    UserBook userBook = new UserBook();

    userBook.userId = userId;
    userBook.bookId = BookId.of(snapshot.bookId());
    userBook.snapshot = snapshot;

    return userBook;
  }

  public UserBook updateSnapshot(BookSnapshot snapshot) {
    validateBook(snapshot);
    verifyBookId(BookId.of(snapshot.bookId()));

    this.snapshot = snapshot;

    return this;
  }

  public static void validateUserId(UserId userId) {
    if (userId == null || userId.getId() == null) {
      throw new IllegalArgumentException("회원 아이디(userId)는 비어있을 수 없습니다.");
    }
  }

  public static void validateBook(BookSnapshot snapshot) {
    if (snapshot.bookId() == null) {
      throw new IllegalArgumentException("도서 아이디(bookId)는 비어있을 수 없습니다.");
    }

    if (snapshot.isbn() == null || snapshot.isbn().isBlank()) {
      throw new IllegalArgumentException("도서 고유번호(isbn)는 비어있을 수 없습니다.");
    }

    if (snapshot.title() == null || snapshot.title().isBlank()) {
      throw new IllegalArgumentException("도서 제목(title)은 비어있을 수 없습니다.");
    }

    if (snapshot.author() == null || snapshot.author().isBlank()) {
      throw new IllegalArgumentException("도서 저자(author)는 비어있을 수 없습니다.");
    }

    if (snapshot.description() == null || snapshot.description().isBlank()) {
      throw new IllegalArgumentException("도서 소개(description)는 비어있을 수 없습니다.");
    }
  }

  public void verifyBookId(BookId bookId) {
    if (!bookId.equals(this.bookId)) {
      throw new IllegalStateException("도서 아이디(bookId)는 변경할 수 없습니다.");
    }
  }
}
