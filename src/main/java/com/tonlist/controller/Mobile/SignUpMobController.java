package com.tonlist.controller.Mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tonlist.persistence.entities.User;
import com.tonlist.service.SecurityService;
import com.tonlist.service.UserService;
import com.tonlist.validator.UserValidator;

@RestController
public class SignUpMobController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
	/**
	 * Get called when Post mapping url ("/signUp") is accessed with POST.
	 * @param userForm Get the registration value from the form.
	 * @param bindingResult  It validate the form.
	 * @param model
	 * @return redirect:/ html page.
	 */
    @PostMapping("/m/signUp")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        
        
        if (bindingResult.hasErrors()) 
            return "signUp";
        

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        
        return "[]";
    }	
    
    

}
