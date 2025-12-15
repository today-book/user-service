package org.todaybook.userservice.userbook.application;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.todaybook.userservice.user.domain.UserId;
import org.todaybook.userservice.userbook.application.dto.UserBookMapper;
import org.todaybook.userservice.userbook.domain.BookId;
import org.todaybook.userservice.userbook.domain.UserBook;
import org.todaybook.userservice.userbook.domain.dto.Book;
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
  public void delete(Long id) {
    userBookManageService.deleteById(id);
  }

  @Override
  public UserBookResponse getUserBookById(Long id) {
    UserBook userBook = userBookQueryService.getUserBookById(id);
    return UserBookResponse.from(userBook);
  }

  @Override
  public List<UserBookResponse> getUserBooks(UUID userId) {
    List<UserBook> userBooks = userBookQueryService.getUserBooks(UserId.of(userId));
    return userBooks.stream().map(UserBookResponse::from).toList();
  }

  @Override
  public boolean isSavedBook(UUID userId, UUID bookId) {
    return userBookQueryService.isSavedBook(UserId.of(userId), BookId.of(bookId));
  }

  public Set<UUID> getSavedBooksByBookId(UUID userId, List<UUID> bookIds) {
    Set<BookId> result =
        userBookQueryService.getSavedBooksByBookIds(
            UserId.of(userId), bookIds.stream().map(BookId::of).toList());
    return result.stream().map(BookId::toUUID).collect(Collectors.toSet());
  }
}
