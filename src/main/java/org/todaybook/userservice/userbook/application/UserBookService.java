package org.todaybook.userservice.userbook.application;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.todaybook.userservice.userbook.presentation.dto.UserBookRequest;
import org.todaybook.userservice.userbook.presentation.dto.UserBookResponse;

public interface UserBookService {
  void register(UUID userId, UserBookRequest request);

  void registerAll(UUID userId, List<UserBookRequest> request);

  void deleteById(UUID userId, Long id);

  void deleteByBookId(UUID userId, UUID bookId);

  UserBookResponse getUserBookById(Long id);

  UserBookResponse getOwnedUserBook(UUID userId, Long id);

  List<UserBookResponse> getOwnedUserBooks(UUID userId);

  boolean isSavedBook(UUID userId, UUID bookId);

  Map<UUID, Boolean> getSavedBooksByBookId(UUID userId, List<UUID> bookIds);
}
