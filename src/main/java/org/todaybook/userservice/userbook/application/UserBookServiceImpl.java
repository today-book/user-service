package org.todaybook.userservice.userbook.application;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.application.dto.UserBookMapper;
import org.todaybook.userservice.userbook.domain.Book;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.exception.UserBookAccessDeniedException;
import org.todaybook.userservice.userbook.domain.service.UserBookManageService;
import org.todaybook.userservice.userbook.domain.service.UserBookQueryService;
import org.todaybook.userservice.userbook.presentation.dto.UserBookRequest;
import org.todaybook.userservice.userbook.presentation.dto.UserBookResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class UserBookServiceImpl implements UserBookService {

  private final UserBookQueryService userBookQueryService;
  private final UserBookManageService userBookManageService;

  @Override
  public void register(UUID userId, UserBookRequest request) {
    Book book = UserBookMapper.toBook(request);
    userBookManageService.save(UserId.of(userId), book);
  }

  @Override
  public void registerAll(UUID userId, List<UserBookRequest> request) {
    List<Book> books = request.stream().map(UserBookMapper::toBook).toList();
    userBookManageService.saveAll(UserId.of(userId), books);
  }

  @Override
  public void deleteById(UUID userId, Long id) {
    UserBook userBook = userBookQueryService.getUserBookById(id);

    if (!userBook.getUserId().toUUID().equals(userId)) {
      throw new UserBookAccessDeniedException(id);
    }

    userBookManageService.deleteById(id);
  }

  @Override
  public void deleteByBookId(UUID userId, UUID bookId) {
    UserBook userBook = userBookQueryService.getUserBookByBookId(BookId.of(bookId));

    if (!userBook.getUserId().toUUID().equals(userId)) {
      throw new UserBookAccessDeniedException(UserId.of(userId), BookId.of(bookId));
    }

    userBookManageService.deleteByBookId(BookId.of(bookId));
  }

  @Override
  public UserBookResponse getUserBookById(Long id) {
    UserBook userBook = userBookQueryService.getUserBookById(id);
    return UserBookResponse.from(userBook);
  }

  @Override
  public UserBookResponse getOwnedUserBook(UUID userId, Long id) {
    UserBook userBook = userBookQueryService.getUserBookById(id);

    if (!userBook.getUserId().toUUID().equals(userId)) {
      throw new UserBookAccessDeniedException(id);
    }

    return UserBookResponse.from(userBook);
  }

  @Override
  public List<UserBookResponse> getOwnedUserBooks(UUID userId) {
    List<UserBook> userBooks = userBookQueryService.getUserBooksByUserId(UserId.of(userId));
    return userBooks.stream().map(UserBookResponse::from).toList();
  }

  @Override
  public boolean isSavedBook(UUID userId, UUID bookId) {
    return userBookQueryService.isSavedBook(UserId.of(userId), BookId.of(bookId));
  }

  @Override
  public Map<UUID, Boolean> getSavedBooksByBookId(UUID userId, List<UUID> bookIds) {
    List<BookId> bookIdList = bookIds.stream().map(BookId::of).toList();
    Set<BookId> savedIds =
        userBookQueryService.getSavedBooksByBookIds(UserId.of(userId), bookIdList);
    return bookIdList.stream().collect(Collectors.toMap(BookId::toUUID, savedIds::contains));
  }
}
