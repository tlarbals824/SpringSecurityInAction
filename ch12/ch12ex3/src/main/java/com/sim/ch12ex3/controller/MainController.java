package com.sim.ch12ex3.controller;

import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	private Logger logger = Logger.getLogger(MainController.class.getName());

	@GetMapping("/")
	public String main(Authentication authentication) {
		logger.info(String.valueOf(authentication.getPrincipal()));
		return "main.html";
	}
}
