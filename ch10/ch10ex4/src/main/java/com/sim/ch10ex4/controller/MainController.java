package com.sim.ch10ex4.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	private Logger logger = Logger.getLogger(MainController.class.getName());

	@GetMapping("/")
	public String main(){
		return "main.html";
	}

	@PostMapping("/test")
	@ResponseBody
	// @CrossOrigin("http://localhost:8080")
	public String test(){
		logger.info("Test method called");
		return "HELLO";
	}
}
