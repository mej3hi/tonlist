package com.tonlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tonlist.model.User;
import com.tonlist.service.SecurityService;
import com.tonlist.service.UserService;
import com.tonlist.validator.UserValidator;


@Controller
public class SignUpController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
    String signIn(Model model){	
		model.addAttribute("userForm", new User());
        
		return "signUp";
    }
	
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        
        
        if (bindingResult.hasErrors()) {
        	
        	model.addAttribute("name",userForm.getUsername());
        	model.addAttribute("email",userForm.getEmail());
        	model.addAttribute("username",userForm.getUsername());
        	
            return "signUp";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        
        return "redirect:/";
    }	

}
