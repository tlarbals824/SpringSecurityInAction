package com.sim.ch05ex4.contoller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest{

	@Autowired
	MockMvc mvc;

	@Test
	@DisplayName("잘못된 사용자 이름")
	public void loggingInWithWrongUser() throws Exception {
		mvc.perform(formLogin()
			.user("notest").password("test"))
			.andExpect(header().exists("failed"))
			.andExpect(unauthenticated());
	}

	@Test
	@DisplayName("유효한 사용자이지만 유효하지 않은 권한")
	public void loggingInWithWrongAuthority() throws Exception {
		mvc.perform(formLogin()
				.user("write").password("test"))
			.andExpect(redirectedUrl("/error"))
			.andExpect(status().isFound())
			.andExpect(authenticated());
	}

	@Test
	@DisplayName("유효한 사용자이고 유효한 권한")
	public void loggingIn() throws Exception {
		mvc.perform(formLogin()
				.user("read").password("test"))
			.andExpect(redirectedUrl("/home"))
			.andExpect(status().isFound())
			.andExpect(authenticated());
	}

}