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
	 * @param userForm contains the registration values from the form.
	 * @param bindingResult It validates the form.
	 * @return message string
	 */
    @PostMapping("/m/signUp")
    public String registration(@RequestBody User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);
        
       
        if (bindingResult.hasErrors()){
        	
	        List<FieldError> user =bindingResult.getFieldErrors("username");	        
	        for (FieldError fieldError : user) {
	        	String rejectedValue = fieldError.getRejectedValue().toString();
	        	if(rejectedValue.equals(userForm.getUsername())){
	        		return "\"username_exists\"";
	        	}	         	        	
			}
	        List<FieldError> email =bindingResult.getFieldErrors("email");	        
	        for (FieldError fieldError : email) {
	        	String rejectedValue = fieldError.getRejectedValue().toString();
	        	if(rejectedValue.equals(userForm.getEmail())){
	        		return "\"email_exists\"";
	        	}	         	        	
			}
	              	
        	return "\"hasErrors\"";
        }
                   
        userService.save(userForm);
        

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());       
    	
        return "\"ok\"";
    }	
    
    

}
