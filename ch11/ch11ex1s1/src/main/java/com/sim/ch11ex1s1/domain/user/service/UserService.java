package com.sim.ch11ex1s1.domain.user.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sim.ch11ex1s1.domain.otp.service.OtpService;
import com.sim.ch11ex1s1.domain.user.entity.User;
import com.sim.ch11ex1s1.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final OtpService otpService;

	@Transactional
	public void addUser(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void auth(User user){
		User findUser = userRepository.findByUsername(user.getUsername())
			.orElseThrow(() -> new BadCredentialsException("Bad credentials"));

		if(passwordEncoder.matches(user.getPassword(),findUser.getPassword())){
			renewOtp(findUser);
		}else{
			throw new BadCredentialsException("Bad credentials");
		}
	}

	private void renewOtp(User user){
		otpService.renewOtp(user.getUsername());
	}
}
