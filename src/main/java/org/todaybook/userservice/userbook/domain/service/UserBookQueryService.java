package org.todaybook.userservice.userbook.domain.service;

import java.util.List;
import java.util.Set;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;

public interface UserBookQueryService {

  UserBook getUserBookById(Long id);

  UserBook getUserBookByUserId(UserId userId, Long id);

  List<UserBook> getUserBooksByUserId(UserId userId);

  Set<BookId> getSavedBooksByBookIds(UserId userId, List<BookId> bookIds);
}
