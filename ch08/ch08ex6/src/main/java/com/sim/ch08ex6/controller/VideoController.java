package com.sim.ch08ex6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

	@GetMapping("/video/{country}/{language}")
	public String video(@PathVariable("country") String country, @PathVariable("language") String language){
		return "Video allowed for " + country + " " + language;
	}
}
