package com.sim.ch05ex3.contoller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest{

	@Autowired
	MockMvc mvc;

	@Test
	@DisplayName("/hello 요청에 대해 인증이 되지 않은 경우 401 에러가 발생한다.")
	void unauthorized() throws Exception {
		mvc.perform(get("/hello"))
			.andExpect(header().string("message", "Luke, I am your father"))
			.andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("/hello 요청에 대해 인증이 완료되면 Hello를 출력한다.")
	@WithMockUser(username = "test")
	void authorized() throws Exception {
		mvc.perform(get("/hello"))
			.andExpect(content().string("Hello"))
			.andExpect(status().isOk());
	}

}