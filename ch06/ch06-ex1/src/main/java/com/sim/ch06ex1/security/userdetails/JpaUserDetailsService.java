package com.sim.ch06ex1.security.userdetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sim.ch06ex1.domain.user.entity.User;
import com.sim.ch06ex1.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	/**
	 * @throws UsernameNotFoundException : spring security가 제공하는 AuthenticationException 하위 예외
	 */
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Problem during authentication"));

		return new CustomUserDetails(user);
	}
}
