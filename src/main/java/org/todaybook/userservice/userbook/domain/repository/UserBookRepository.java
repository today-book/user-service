package org.todaybook.userservice.userbook.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.user.UserId;

public interface UserBookRepository extends Repository<UserBook, Long> {
  Optional<UserBook> findById(Long id);

  Optional<UserBook> findByUserIdAndBookId(UserId userId, BookId bookId);

  List<UserBook> findByUserId(UserId userId);

  List<UserBook> findByUserIdAndBookIdIn(UserId userId, List<BookId> bookIds);

  UserBook save(UserBook userBook);

  void deleteById(Long id);
}
