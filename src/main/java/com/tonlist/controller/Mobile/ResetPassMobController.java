package com.tonlist.controller.Mobile;

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
	public String postResetPassword(@RequestParam(value = "_key") String resetPasswordToken,
			@RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm,
			final Model model) {
		
		String PasswordValidation = resetPasswValidation.validate(password, passwordConfirm);
		
		if (PasswordValidation != "") {
			//model.addAttribute("passwordErro", PasswordValidation );
			//model.addAttribute("resetPasswordToken", resetPasswordToken); 
			return "[]";
		}

		User userToUpdate = userService.findByResetPasswordToken(resetPasswordToken);
		userToUpdate.setPassword(password);
		userToUpdate.setResetpasswordtoken(null);
		userToUpdate.setResetpasswordexpires(null);
		userService.save(userToUpdate);

		String responseMessage = "Your password was successfully updated";
		//model.addAttribute("responseMessage", responseMessage);
		return "[]";

	}
	
 
}
