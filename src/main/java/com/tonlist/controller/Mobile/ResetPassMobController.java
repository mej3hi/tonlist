package com.tonlist.controller.Mobile;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tonlist.persistence.entities.User;
import com.tonlist.service.UserService;
import com.tonlist.validator.ResetPasswValidation;

@RestController
public class ResetPassMobController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResetPasswValidation resetPasswValidation;
	
	
	/**
	 * Get called when Post mapping url ("/resetPassword") is accessed with POST.
	 * It store the new password that user has create.
	 * @param resetPasswordToken Is the token for that user.
	 * @param password Is the password that user create.
	 * @param passwordConfirm Is the same as password.
	 * @param model Send over validation and success msg.
	 * @return resetPassword html page.
	 */
	@PostMapping("/m/resetPassword")
	public String postResetPassword(@RequestParam("token") String resetPasswordToken,
			@RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm) {
		
		String PasswordValidation = resetPasswValidation.validate(password, passwordConfirm);
		
		if (PasswordValidation != "") { 
			return "\"hasErrors\"";
		}
					
		User user = userService.findByResetPasswordToken(resetPasswordToken);
		Date expirationDate;
		if (user != null) {
			expirationDate = user.getResetpasswordexpires();

			if (expirationDate.after(new Date())) {				
				user.setPassword(password);
				user.setResetpasswordtoken(null);
				user.setResetpasswordexpires(null);
				userService.save(user);
				return "\"ok\"";
			}
			
			return "\"token_expires\"";
		}

		
		return "\"hasErrors\"";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
 
}
