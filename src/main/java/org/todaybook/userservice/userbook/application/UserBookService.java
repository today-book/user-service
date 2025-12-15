package org.todaybook.userservice.userbook.application;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.todaybook.userservice.userbook.presentation.dto.UserBookRequest;
import org.todaybook.userservice.userbook.presentation.dto.UserBookResponse;

public interface UserBookService {
  void register(UUID userId, UserBookRequest request);

  void delete(Long id);

  UserBookResponse getUserBookById(Long id);

  List<UserBookResponse> getUserBooks(UUID userId);

  boolean isSavedBook(UUID userId, UUID bookId);

  Set<UUID> getSavedBooksByBookId(UUID userId, List<UUID> bookIds);
}
