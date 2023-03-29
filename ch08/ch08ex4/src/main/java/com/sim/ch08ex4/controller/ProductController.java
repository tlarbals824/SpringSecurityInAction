package com.sim.ch08ex4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@GetMapping("/product/{code}")
	public String productCode(@PathVariable("code") String code){
		return code;
	}

}
