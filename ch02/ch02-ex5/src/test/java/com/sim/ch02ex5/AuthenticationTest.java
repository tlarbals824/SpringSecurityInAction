package com.sim.ch02ex5;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

	@Autowired
	MockMvc mvc;

	@Test
	@DisplayName("/hello 요청에 대해 유효한 인증 정보가 넘어오면 hello를 출력한다.")
	public void authenticationWIthValidUser() throws Exception {
		mvc.perform(get("/hello")
			.with(httpBasic("test","test")))
			.andExpect(content().string("Hello"))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("/hello 요청에 대해 유효하지 않은 인증 정보가 넘어오면 401 에러가 발생한다.")
	public void authenticationWIthInvalidUser() throws Exception {
		mvc.perform(get("/hello")
			.with(httpBasic("test","invalid")))
			.andExpect(status().isUnauthorized());
	}
}
