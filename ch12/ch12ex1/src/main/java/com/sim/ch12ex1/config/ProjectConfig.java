package com.sim.ch12ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ProjectConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.oauth2Login();

		http.authorizeRequests(
			request -> request
				.anyRequest().authenticated()
		);

		return http.build();
	}

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(){
		return new InMemoryClientRegistrationRepository(clientRegistration());
	}

	private  ClientRegistration clientRegistration(){
		ClientRegistration clientRegistration = CommonOAuth2Provider.GITHUB
			.getBuilder("github")
			.clientId("")
			.clientSecret("")
			.build();

		return clientRegistration;
	}


	// private ClientRegistration clientRegistration(){
	// 	ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("github")
	// 		.clientId("")
	// 		.clientSecret("")
	// 		.scope(new String[] {"read:user"})
	// 		.authorizationUri("https://github.com/login/oauth/authorize")
	// 		.tokenUri("https://github.com/login/oauth/access_token")
	// 		.userInfoUri("https://api.github.com/user")
	// 		.userNameAttributeName("id")
	// 		.clientName("GitHub")
	// 		.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	// 		.redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}")
	// 		.build();
	//
	// 	return clientRegistration;
	// }
}
