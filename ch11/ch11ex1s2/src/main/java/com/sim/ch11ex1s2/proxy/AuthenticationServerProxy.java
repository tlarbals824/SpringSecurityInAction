package com.sim.ch11ex1s2.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationServerProxy {

	private final RestTemplate restTemplate;

	@Value("${auth.server.base.url}")
	private String baseUrl;

	public void sendAuth(String username, String password){
		final String url = baseUrl + "/user/auth";

		User user = User.builder()
				.username(username)
				.password(password)
				.build();

		HttpEntity<User> request = new HttpEntity<>(user);

		restTemplate.postForEntity(url, request, void.class);
	}

	public boolean sendOTP(String username, String code){
		final String url = baseUrl + "/otp/check";

		User user = User.builder()
			.username(username)
			.code(code)
			.build();

		HttpEntity<User> request = new HttpEntity<>(user);

		ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);

		return response.getStatusCode().equals(HttpStatus.OK);
	}

}
