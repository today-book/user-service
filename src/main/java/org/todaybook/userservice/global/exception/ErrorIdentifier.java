package org.todaybook.userservice.global.exception;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class ErrorIdentifier implements Serializable {

  private Map<String, Object> identifiers = new LinkedHashMap<>();

  private ErrorIdentifier(Map<String, Object> identifiers) {
    this.identifiers = identifiers;
  }

  public static ErrorIdentifier of(String key, Object value) {
    Map<String, Object> map = new LinkedHashMap<>();
    map.put(key, value);
    return new ErrorIdentifier(map);
  }

  public static ErrorIdentifier of(Map<String, Object> identifiers) {
    return new ErrorIdentifier(new LinkedHashMap<>(identifiers));
  }

  public ErrorIdentifier add(String key, Object value) {
    identifiers.put(key, value);
    return this;
  }

  @Override
  public String toString() {
    StringJoiner joiner = new StringJoiner(", ");
    identifiers.forEach((k, v) -> joiner.add(k + "=" + v));
    return joiner.toString();
  }
}
