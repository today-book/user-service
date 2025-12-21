package org.todaybook.userservice.share.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.todaybook.userservice.config.PostgresContainerConfig;
import org.todaybook.userservice.config.RedisContainerConfig;
import org.todaybook.userservice.share.SharedBookFixture;
import org.todaybook.userservice.share.presentation.dto.SharedBookRequest;
import org.todaybook.userservice.share.presentation.dto.SharedBookResponse;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@Import({PostgresContainerConfig.class, RedisContainerConfig.class})
class ShareControllerTests {

  @Autowired MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @Test
  @DisplayName("도서 공유 토큰 발급 성공")
  void test1() throws Exception {
    UUID token = UUID.randomUUID();
    SharedBookRequest request = SharedBookFixture.sharedBookRequest();

    mockMvc
        .perform(
            put("/public/v1/users/share/book/{token}", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("토큰으로 SharedBook 조회 성공")
  void test2() throws Exception {
    UUID token = UUID.randomUUID();
    SharedBookRequest request = SharedBookFixture.sharedBookRequest();

    mockMvc
        .perform(
            put("/public/v1/users/share/book/{token}", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk());

    String response =
        mockMvc
            .perform(get("/public/v1/users/share/book/{token}", token))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    SharedBookResponse sharedBook = objectMapper.readValue(response, SharedBookResponse.class);

    System.out.println("response: " + sharedBook);
  }
}
