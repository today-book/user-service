package org.todaybook.userservice.userbook.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookId {

  @Column(name = "book_id", nullable = false)
  private UUID id;

  protected BookId(UUID id) {
    this.id = id;
  }

  public static BookId of(UUID id) {
    return new BookId(id);
  }

  public UUID toUUID() {
    return id;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
