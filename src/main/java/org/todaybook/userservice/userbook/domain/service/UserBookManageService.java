package org.todaybook.userservice.userbook.domain.service;

import java.util.List;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.Book;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;

public interface UserBookManageService {
  UserBook save(UserId userId, Book book);

  List<UserBook> saveAll(UserId userId, List<Book> books);

  void deleteById(Long id);

  void deleteByBookId(BookId bookId);
}
