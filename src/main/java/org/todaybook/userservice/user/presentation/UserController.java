package org.todaybook.userservice.user.presentation;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todaybook.userservice.user.application.service.UserService;
import org.todaybook.userservice.user.presentation.dto.UserRegisterRequest;
import org.todaybook.userservice.user.presentation.dto.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/v1/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public UserResponse register(@RequestBody UserRegisterRequest request) {
    return userService.register(request.kakaoId(), request.nickname());
  }

  @GetMapping("/{id}")
  public UserResponse getUserById(@PathVariable UUID id) {
    return userService.getUserById(id);
  }

  @GetMapping("/kakao/{kakaoId}")
  public UserResponse getUserByKakaoId(@PathVariable Long kakaoId) {
    return userService.getUserByKakaoId(kakaoId);
  }

  @DeleteMapping("/{id}")
  public void deleteUserById(@PathVariable UUID id) {
    userService.deleteById(id);
  }
}
