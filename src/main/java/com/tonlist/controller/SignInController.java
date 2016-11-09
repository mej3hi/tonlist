package com.tonlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignInController {
		
	/**
	 * Get called when Get mapping url ("/signIn") is accessed with GET.
	 * It open login page for the user.
	 * @return signIn html page.
	 */
	@GetMapping("/signIn")
    String signIn(){			
        return "signIn";
    }
 
}
