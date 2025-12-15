package org.todaybook.userservice.share.application.service;

import java.util.UUID;
import org.todaybook.userservice.share.domain.SharedBook;
import org.todaybook.userservice.share.presentation.dto.SharedBookRequest;

public interface ShareService {
  UUID shareBook(SharedBookRequest request);

  SharedBook getSharedBook(UUID token);

  void deleteSharedBook(UUID token);
}
