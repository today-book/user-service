package org.todaybook.userservice.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;
import org.todaybook.userservice.domain.BookId;
import org.todaybook.userservice.domain.UserBook;
import org.todaybook.userservice.domain.UserId;

public interface UserBookRepository extends Repository<UserBook, Long> {
  Optional<UserBook> findById(Long id);

  Optional<UserBook> findByUserId(UserId userId);

  List<UserBook> findByUserIdAndBookIdIn(UserId userId, List<BookId> bookIds);

  UserBook save(UserBook userBook);

  void delete(Long id);
}
