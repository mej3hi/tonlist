package com.tonlist.controller.Mobile;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String registration(@RequestBody User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        
        if (userService.findByUsername(userForm.getUsername()) != null){
        	return "\"username_exists\"";
        }
       
        if (bindingResult.hasErrors()){
        	return "\"hasErrors\"";
        }
                   
        
        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());       
    	
        return "\"ok\"";
    }	
    
    

}
