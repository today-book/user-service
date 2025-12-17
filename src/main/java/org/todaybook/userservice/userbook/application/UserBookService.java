package org.todaybook.userservice.userbook.application;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.todaybook.userservice.userbook.presentation.dto.UserBookRequest;
import org.todaybook.userservice.userbook.presentation.dto.UserBookResponse;

public interface UserBookService {
  void register(UUID userId, UserBookRequest request);

  void delete(UUID userId, Long id);

  UserBookResponse getUserBookById(Long id);

  UserBookResponse getUserBookByUserId(UUID userId, Long id);

  List<UserBookResponse> getUserBooksByUserId(UUID userId);

  boolean isSavedBook(UUID userId, UUID bookId);

  Map<UUID, Boolean> getSavedBooksByBookId(UUID userId, List<UUID> bookIds);
}
