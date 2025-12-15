package org.todaybook.userservice.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.postgresql.PostgreSQLContainer;

@SpringBootTest
@ActiveProfiles("test")
@Import({PostgresContainerConfig.class, RedisContainerConfig.class})
public class PostgresContainerTests {

  @Autowired private PostgreSQLContainer postgres;

  @Autowired private DataSource dataSource;

  @Test
  @DisplayName("PostgresContainer 실행 테스트")
  void test1() {
    assertTrue(postgres.isRunning());
  }

  @Test
  @DisplayName("PostgresContainer 연결 테스트")
  void test2() throws Exception {
    assertNotNull(dataSource);

    String url = dataSource.getConnection().getMetaData().getURL();
    assertTrue(url.contains("test-postgres"));
  }
}
