package com.sim.ch11ex1s1.domain.otp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sim.ch11ex1s1.domain.otp.entity.Otp;
import com.sim.ch11ex1s1.domain.otp.service.OtpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OtpController {

	private final OtpService otpService;

	@PostMapping("/otp/check")
	public void check(@RequestBody Otp otp, HttpServletResponse response){
		if(otpService.checkOtp(otp)){
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

}
