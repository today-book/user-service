package org.todaybook.userservice.user.repository;

import java.util.Optional;
import org.springframework.data.repository.Repository;
import org.todaybook.userservice.user.KakaoId;
import org.todaybook.userservice.user.User;
import org.todaybook.userservice.user.UserId;

public interface UserRepository extends Repository<User, UserId> {
  Optional<User> findById(UserId userId);

  Optional<User> findByKakaoId(KakaoId kakaoId);

  User save(User user);

  User deleteById(UserId userId);
}
