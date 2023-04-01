package com.sim.ch11ex1s1.domain.otp.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sim.ch11ex1s1.common.util.GenerateCodeUtil;
import com.sim.ch11ex1s1.domain.otp.entity.Otp;
import com.sim.ch11ex1s1.domain.otp.repository.OtpRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OtpService {

	private final OtpRepository otpRepository;

	public void renewOtp(String username){
		final String code = GenerateCodeUtil.generateCode();

		Optional<Otp> userOtp = otpRepository.findByUsername(username);

		if(userOtp.isPresent()){
			Otp otp = userOtp.get();
			otp.setCode(code);
		} else{
			Otp otp = new Otp(username, code);
			otpRepository.save(otp);
		}
	}

	public boolean checkOtp(Otp requestOtp){
		Optional<Otp> userOtp = otpRepository.findByUsername(requestOtp.getUsername());

		return userOtp.filter(otp -> Objects.equals(requestOtp.getCode(), otp.getCode())).isPresent();
	}

}
