package org.todaybook.userservice.share.application.service;

import java.util.UUID;
import org.todaybook.userservice.share.presentation.dto.SharedBookRequest;
import org.todaybook.userservice.share.presentation.dto.SharedBookResponse;

public interface ShareService {
  UUID shareBook(SharedBookRequest request);

  SharedBookResponse getSharedBook(UUID token);

  void deleteSharedBook(UUID token);
}
