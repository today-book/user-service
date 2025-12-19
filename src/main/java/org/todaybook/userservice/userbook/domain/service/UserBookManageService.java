package org.todaybook.userservice.userbook.domain.service;

import java.util.List;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.BookSnapshot;
import org.todaybook.userservice.userbook.domain.UserBook;

public interface UserBookManageService {
  UserBook save(UserId userId, BookSnapshot snapshot);

  List<UserBook> saveAll(UserId userId, List<BookSnapshot> snapshots);

  void deleteById(Long id);

  void deleteByBookId(BookId bookId);
}
