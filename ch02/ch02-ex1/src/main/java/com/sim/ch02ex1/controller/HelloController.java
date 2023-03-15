package com.sim.ch02ex1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	// curl -u user:passowrd http://localhost:8080/hello
	// password에 경우 spring 시작시에 생성해주는 UUID를 사용한다.
	@GetMapping("/hello")
	public String hello(){
		return "Hello";
	}
}
