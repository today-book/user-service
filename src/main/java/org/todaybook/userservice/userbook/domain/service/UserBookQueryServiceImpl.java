package org.todaybook.userservice.userbook.domain.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.exception.UserBookNotFoundException;
import org.todaybook.userservice.userbook.domain.repository.UserBookRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserBookQueryServiceImpl implements UserBookQueryService {

  private final UserBookRepository repository;

  @Override
  public UserBook getUserBookById(Long id) {
    return repository.findById(id).orElseThrow(() -> new UserBookNotFoundException(id));
  }

  @Override
  public UserBook getUserBookByBookId(BookId bookId) {
    return repository.findByBookId(bookId).orElseThrow(() -> new UserBookNotFoundException(bookId));
  }

  @Override
  public List<UserBook> getUserBooksByUserId(UserId userId) {
    return repository.findByUserId(userId);
  }

  @Override
  public boolean isSavedBook(UserId userId, BookId bookId) {
    return repository.findByUserIdAndBookId(userId, bookId).isPresent();
  }

  @Override
  public Set<BookId> getSavedBooksByBookIds(UserId userId, List<BookId> bookIds) {
    return repository.findByUserIdAndBookIds(userId, bookIds).stream()
        .map(UserBook::getBookId)
        .collect(Collectors.toSet());
  }
}
