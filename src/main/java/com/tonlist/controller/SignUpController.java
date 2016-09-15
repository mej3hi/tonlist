package com.tonlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SignUpController {
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
    String signIn(){			
        return "signUp";
    }

}
