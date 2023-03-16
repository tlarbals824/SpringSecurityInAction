package com.sim.ch02ex2.contoller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.sim.ch03ex2.Ch03Ex2Application;

@SpringBootTest(classes = Ch03Ex2Application.class)
@AutoConfigureMockMvc
class HelloControllerTest{

	@Autowired
	MockMvc mvc;

	@Test
	@DisplayName("/hello 요청에 대해 인증이 되지 않은 경우 401 에러가 발생한다.")
	void unauthorized() throws Exception {
		mvc.perform(get("/hello"))
			.andExpect(status().isUnauthorized());
	}

	@Test
	@DisplayName("/hello 요청에 대해 인증이 완료되면 hello를 출력한다.")
	void authorized() throws Exception {
		mvc.perform(get("/hello")
				.with(user("test")))
			.andExpect(content().string("Hello"))
			.andExpect(status().isOk());
	}

}