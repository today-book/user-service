package org.todaybook.userservice.userbook.domain.service;

import java.util.List;
import java.util.Set;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;

public interface UserBookQueryService {

  UserBook getUserBookById(Long id);

  UserBook getUserBookByBookId(BookId bookId);

  List<UserBook> getUserBooksByUserId(UserId userId);

  boolean isSavedBook(UserId userId, BookId bookId);

  Set<BookId> getSavedBooksByBookIds(UserId userId, List<BookId> bookIds);
}
