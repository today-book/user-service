package org.todaybook.userservice.userbook.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todaybook.userservice.userbook.application.UserBookService;
import org.todaybook.userservice.userbook.presentation.dto.UserBookResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users/bookshelf")
public class UserBookAdminController {

  private final UserBookService userBookService;

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/{id}")
  public UserBookResponse getUserBookById(@PathVariable Long id) {
    return userBookService.getUserBookById(id);
  }
}
