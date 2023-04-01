package com.sim.ch11ex1s1.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sim.ch11ex1s1.domain.user.entity.User;
import com.sim.ch11ex1s1.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;

	@PostMapping("/user/add")
	public void addUser(@RequestBody User user){
		userService.addUser(user);
	}

	@PostMapping("/user/auth")
	public void auth(@RequestBody User user){
		userService.auth(user);
	}
}
