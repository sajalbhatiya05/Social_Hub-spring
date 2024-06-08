package com.bhatiya.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String homeComtrollerHandler() {
		 return "This is home controller";
	}
	
	@GetMapping("/home")
	public String homeComtrollerHandler2() {
		 return "This is home controller2";
	}
	
	
}
