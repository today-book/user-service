package org.todaybook.userservice.share.presentation;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.todaybook.userservice.share.application.service.ShareService;
import org.todaybook.userservice.share.presentation.dto.SharedBookRequest;
import org.todaybook.userservice.share.presentation.dto.SharedBookResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shares")
public class ShareController {

  private final ShareService shareService;

  @PostMapping("/book")
  public UUID shareBook(@RequestBody SharedBookRequest request) {
    return shareService.shareBook(request);
  }

  @GetMapping("/book/{token}")
  public SharedBookResponse getSharedBook(@PathVariable UUID token) {
    return shareService.getSharedBook(token);
  }
}
