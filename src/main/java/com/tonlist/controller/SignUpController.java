package com.tonlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.tonlist.persistence.entities.User;
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
	
    /**
     * Get called when Get mapping url ("/signUp") is accessed with GET.
     * It open registration form for the user to fill out.
     * @param model Create new User for the form.
     * @return signUp html page.
     */
	@GetMapping("/signUp")
    String signUp(Model model){	
		model.addAttribute("userForm", new User());
        
		return "signUp";
    }
	
	/**
	 * Get called when Post mapping url ("/signUp") is accessed with POST.
	 * @param userForm Get the registration value from the form.
	 * @param bindingResult  It validate the form.
	 * @param model
	 * @return redirect:/ html page.
	 */
    @PostMapping("/signUp")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        
        
        if (bindingResult.hasErrors()) 
            return "signUp";
        

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        
        return "redirect:/";
    }	

}
