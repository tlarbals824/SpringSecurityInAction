package com.sim.ch10ex3.security;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import com.sim.ch10ex3.domain.Token.entity.Token;
import com.sim.ch10ex3.domain.Token.repository.TokenRepository;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

	private final TokenRepository tokenRepository;

	public CustomCsrfTokenRepository(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();
		return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
	}

	@Override
	public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
		String identifier = request.getHeader("X-IDENTIFIER");
		Optional<Token> existingToken = tokenRepository.findByIdentifier(identifier);

		if(existingToken.isPresent()){
			Token token = existingToken.get();
			token.setToken(csrfToken.getToken());
		} else {
			Token token = new Token();
			token.setToken(csrfToken.getToken());
			token.setIdentifier(identifier);
			tokenRepository.save(token);
		}
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		String identifier = request.getHeader("X-IDENTIFIER");

		Optional<Token> existingToken = tokenRepository.findByIdentifier(identifier);

		if(existingToken.isPresent()){
			Token token = existingToken.get();
			return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
		}
		return null;
	}
}
