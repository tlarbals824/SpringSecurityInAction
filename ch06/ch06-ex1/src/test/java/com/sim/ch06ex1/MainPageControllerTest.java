package com.sim.ch06ex1;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.sim.ch06ex1.domain.authority.entity.Authority;
import com.sim.ch06ex1.domain.product.repository.ProductRepository;
import com.sim.ch06ex1.domain.user.entity.EncryptionAlgorithm;
import com.sim.ch06ex1.domain.user.entity.User;
import com.sim.ch06ex1.domain.user.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration(
	exclude = {DataSourceAutoConfiguration.class,
	DataSourceTransactionManagerAutoConfiguration.class,
	HibernateJpaAutoConfiguration.class}
)
public class MainPageControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private ProductRepository productRepository;

	@Test
	@DisplayName("잘못된 사용자 이름")
	public void loggingInWithWrongUser() throws Exception {
		mvc.perform(formLogin())
			.andExpect(unauthenticated());
	}

	@Test
	@DisplayName("인증후 메인페이지 테스트")
	@WithMockUser(username = "test", password = "test")
	public void skipAuthenticationTest() throws Exception {
		mvc.perform(get("/main"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hello, test!")));
	}


	@Test
	@DisplayName("유효한 사용자 인증")
	public void testAuthenticationWithValidUser() throws Exception {
		User mockUser = new User();
		mockUser.setUsername("test");
		mockUser.setPassword("{bcrypt}"+bCryptPasswordEncoder.encode("test"));
		mockUser.setAlgorithm(EncryptionAlgorithm.BCRYPT);

		Authority authority = new Authority();
		authority.setName("read");
		authority.setUser(mockUser);
		mockUser.setAuthorities(List.of(authority));

		when(userRepository.findByUsername("test")).thenReturn(Optional.of(mockUser));

		mvc.perform(formLogin().user("test").password("test"))
			.andExpect(authenticated());

	}

	@Test
	@DisplayName("유효하지 않은 사용자 인증")
	public void testAuthenticationWithInvalidUser() throws Exception {
		when(userRepository.findByUsername("test")).thenReturn(Optional.empty());

		mvc.perform(formLogin().user("test").password("test"))
			.andExpect(unauthenticated());
	}

	@Test
	@DisplayName("유효하지 않은 사용자 비밀번호")
	public void testAuthenticationWithInvalidPassword() throws Exception {
		User mockUser = new User();
		mockUser.setUsername("test");
		mockUser.setPassword("{bcrypt}"+bCryptPasswordEncoder.encode("test"));
		mockUser.setAlgorithm(EncryptionAlgorithm.BCRYPT);

		Authority authority = new Authority();
		authority.setName("read");
		authority.setUser(mockUser);
		mockUser.setAuthorities(List.of(authority));

		when(userRepository.findByUsername("test")).thenReturn(Optional.of(mockUser));

		mvc.perform(formLogin().user("test").password("invalid"))
			.andExpect(unauthenticated());
	}

}
