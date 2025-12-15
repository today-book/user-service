package org.todaybook.userservice.share.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.todaybook.userservice.share.application.service.dto.ShareMapper;
import org.todaybook.userservice.share.domain.ShareToken;
import org.todaybook.userservice.share.domain.SharedBook;
import org.todaybook.userservice.share.domain.exception.SharedBookNotFoundException;
import org.todaybook.userservice.share.presentation.dto.SharedBookRequest;
import org.todaybook.userservice.share.presentation.dto.SharedBookResponse;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

  private final ObjectMapper objectMapper;
  private final ShareCacheService shareCacheService;

  @Override
  public UUID shareBook(SharedBookRequest request) {
    ShareToken shareToken = ShareToken.create();
    String key = ShareCacheKeyBuilder.tokenKey(shareToken.token());

    SharedBook book = ShareMapper.toSharedBook(request);

    String json = toJson(book);

    shareCacheService.save(key, json);

    return shareToken.token();
  }

  @Override
  public SharedBookResponse getSharedBook(UUID token) {
    String key = ShareCacheKeyBuilder.tokenKey(token);

    String json =
        shareCacheService.find(key).orElseThrow(() -> new SharedBookNotFoundException(token));

    SharedBook sharedBook = fromJson(json);

    return SharedBookResponse.from(sharedBook);
  }

  @Override
  public void deleteSharedBook(UUID token) {
    String key = ShareCacheKeyBuilder.tokenKey(token);

    shareCacheService.delete(key);
  }

  private SharedBook fromJson(String json) {
    try {
      return objectMapper.readValue(json, SharedBook.class);
    } catch (Exception e) {
      throw new IllegalStateException("JSON 역직렬화에 실패하였습니다.", e);
    }
  }

  private String toJson(Object value) {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (Exception e) {
      throw new IllegalStateException("JSON 직렬화에 실패하였습니다.", e);
    }
  }
}
