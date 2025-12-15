package org.todaybook.userservice.userbook.presentation;

import jakarta.validation.Valid;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todaybook.userservice.userbook.application.UserBookService;
import org.todaybook.userservice.userbook.presentation.dto.UserBookSavedRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/v1/users/bookshelf")
public class UserBookInternalController {

  private final UserBookService userBookService;

  @PostMapping("/saved")
  public Map<UUID, Boolean> getUserBookById(@RequestBody @Valid UserBookSavedRequest request) {
    return userBookService.getSavedBooksByBookId(request.userId(), request.bookIds());
  }
}
