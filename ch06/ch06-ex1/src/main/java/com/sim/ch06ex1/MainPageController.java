package com.sim.ch06ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sim.ch06ex1.domain.product.service.ProductService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainPageController {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ProductService productService;

	@GetMapping("/main")
	public String main(Authentication authentication, Model model){
		model.addAttribute("username", authentication.getName());
		model.addAttribute("products", productService.findAll());
		return "main";
	}

	@PostConstruct
	public void test(){
		log.info("bCryptPasswordEncoder: {}", bCryptPasswordEncoder.encode("12345"));
	}
}