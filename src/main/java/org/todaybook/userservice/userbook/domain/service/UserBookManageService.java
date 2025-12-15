package org.todaybook.userservice.userbook.domain.service;

import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.Book;

public interface UserBookManageService {
  UserBook save(UserId userId, Book book);

  void deleteById(Long id);
}
