package org.todaybook.userservice.userbook.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.UserId;
import org.todaybook.userservice.userbook.domain.dto.Book;
import org.todaybook.userservice.userbook.domain.exception.UserBookAlreadyExistsException;
import org.todaybook.userservice.userbook.domain.exception.UserBookNotFoundException;
import org.todaybook.userservice.userbook.domain.repository.UserBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserBookManageServiceImpl implements UserBookManageService {

  private final UserBookRepository repository;

  @Override
  public UserBook save(UserId userId, Book book) {
    repository
        .findByUserIdAndBookId(userId, BookId.of(book.id()))
        .ifPresent(
            userBook -> {
              throw new UserBookAlreadyExistsException(userId, book.id());
            });

    UserBook userBook = UserBook.create(userId, book);

    return repository.save(userBook);
  }

  @Override
  public void deleteById(Long id) {
    repository.findById(id).orElseThrow(() -> new UserBookNotFoundException(id));

    repository.delete(id);
  }
}
