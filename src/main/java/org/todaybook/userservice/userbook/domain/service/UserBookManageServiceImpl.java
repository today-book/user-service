package org.todaybook.userservice.userbook.domain.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.domain.Book;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
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
  public List<UserBook> saveAll(UserId userId, List<Book> books) {
    List<BookId> bookIds = books.stream().map(book -> BookId.of(book.id())).toList();

    Map<BookId, UserBook> existing =
        repository.findByUserIdAndBookIdIn(userId, bookIds).stream()
            .collect(Collectors.toMap(UserBook::getBookId, Function.identity()));

    List<UserBook> result =
        books.stream()
            .map(
                book -> {
                  BookId bookId = BookId.of(book.id());
                  UserBook userBook = existing.get(bookId);

                  if (userBook != null) {
                    return userBook.updateBook(book);
                  }

                  return UserBook.create(userId, book);
                })
            .toList();

    return repository.saveAll(result);
  }

  @Override
  public void deleteById(Long id) {
    repository.findById(id).orElseThrow(() -> new UserBookNotFoundException(id));

    repository.deleteById(id);
  }
}
