package org.todaybook.userservice.userbook.presentation;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todaybook.commonmvc.security.external.AuthenticatedUser;
import org.todaybook.userservice.userbook.application.UserBookService;
import org.todaybook.userservice.userbook.presentation.dto.UserBookRequest;
import org.todaybook.userservice.userbook.presentation.dto.UserBookResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/bookshelf")
public class UserBookController {

  private final UserBookService userBookService;

  @PostMapping
  public void register(
      @AuthenticationPrincipal AuthenticatedUser authentication,
      @RequestBody @Valid UserBookRequest request) {
    userBookService.register(authentication.userId(), request);
  }

  @DeleteMapping("/{id}")
  public void delete(
      @AuthenticationPrincipal AuthenticatedUser authenticatedUser, @PathVariable Long id) {
    userBookService.delete(authenticatedUser.userId(), id);
  }

  @GetMapping("/{id}")
  public UserBookResponse getUserBookById(
      @AuthenticationPrincipal AuthenticatedUser authenticatedUser, @PathVariable Long id) {
    return userBookService.getUserBookByUserId(authenticatedUser.userId(), id);
  }

  @GetMapping
  public List<UserBookResponse> getUserBooksByUserId(
      @AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
    return userBookService.getUserBooksByUserId(authenticatedUser.userId());
  }
}
