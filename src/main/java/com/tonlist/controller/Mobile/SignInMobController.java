package com.tonlist.controller.Mobile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInMobController {
	
	@GetMapping("/m/signIn")
	public String name() {
		
		return "[]";
	}

}
