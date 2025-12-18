package org.todaybook.userservice.userbook.presentation;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PostMapping
  public void registerAll(
      @AuthenticationPrincipal AuthenticatedUser authentication,
      @RequestBody @Valid List<UserBookRequest> request) {
    userBookService.registerAll(authentication.userId(), request);
  }

  @DeleteMapping("/{id}")
  public void delete(
      @AuthenticationPrincipal AuthenticatedUser authentication, @PathVariable Long id) {
    userBookService.delete(authentication.userId(), id);
  }

  @GetMapping("/{id}")
  public UserBookResponse getUserBookById(
      @AuthenticationPrincipal AuthenticatedUser authentication, @PathVariable Long id) {
    return userBookService.getUserBookByUserId(authentication.userId(), id);
  }

  @GetMapping
  public List<UserBookResponse> getUserBooksByUserId(
      @AuthenticationPrincipal AuthenticatedUser authentication) {
    return userBookService.getUserBooksByUserId(authentication.userId());
  }

  @GetMapping("/saved/{bookId}")
  public boolean isSavedBook(
      @AuthenticationPrincipal AuthenticatedUser authentication, @PathVariable UUID bookId) {
    return userBookService.isSavedBook(authentication.userId(), bookId);
  }

  @GetMapping("/saved")
  public Map<UUID, Boolean> getSavedBooksByBookId(
      @AuthenticationPrincipal AuthenticatedUser authentication, @RequestParam List<UUID> bookIds) {
    return userBookService.getSavedBooksByBookId(authentication.userId(), bookIds);
  }
}
