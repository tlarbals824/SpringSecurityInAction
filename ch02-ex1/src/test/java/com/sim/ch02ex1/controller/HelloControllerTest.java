package com.sim.ch02ex1.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

	@Autowired
	MockMvc mvc;

	@Test
	@DisplayName("/hello 엔드 포인트에 대해서 GET 요청을 보내면 401 unauthorized 에러가 발생한다.")
	void unauthorized() throws Exception {
		mvc.perform(get("/hello"))
			.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser
	@DisplayName("/hello 엔드 포인트에 대해서 GET 요청을 보내면 200 OK 발생한다.")
	void authorized() throws Exception {
		mvc.perform(get("/hello"))
			.andExpect(content().string("Hello"))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("/hello 엔드 포인트에 대해서 실제 사용자에 대해 GET 요청을 보내면 200 OK 발생한다.")
	void authorizedWithUser() throws Exception {
		mvc.perform(get("/hello")
				.with(user("test")))
			.andExpect(content().string("Hello"))
			.andExpect(status().isOk());
	}
}