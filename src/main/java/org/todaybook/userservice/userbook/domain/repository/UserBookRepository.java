package org.todaybook.userservice.userbook.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {

  Optional<UserBook> findByBookId(BookId bookId);

  Optional<UserBook> findByUserIdAndBookId(UserId userId, BookId bookId);

  List<UserBook> findByUserId(UserId userId);

  @Query("SELECT ub FROM UserBook ub WHERE ub.userId = :userId and ub.bookId in :bookIds")
  List<UserBook> findByUserIdAndBookIds(
      @Param("userId") UserId userId, @Param("bookIds") List<BookId> bookIds);

  void deleteByBookId(BookId bookId);
}
