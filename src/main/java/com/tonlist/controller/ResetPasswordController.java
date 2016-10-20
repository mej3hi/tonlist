package com.tonlist.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tonlist.persistence.entities.User;
import com.tonlist.service.UserService;
import com.tonlist.validator.ResetPasswValidation;

@Controller
public class ResetPasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResetPasswValidation resetPasswValidation;

	@GetMapping("/resetPassword")
	public String resetpasswordView(@RequestParam(value = "_key") String resetPasswordToken, final Model model) {
		User user = userService.findByResetPasswordToken(resetPasswordToken);
		Date expirationDate;
		if (user != null) {
			expirationDate = user.getResetpasswordexpires();

			if (expirationDate.after(new Date())) {
				model.addAttribute("resetPasswordToken", resetPasswordToken);

				return "resetPassword";
			}

			String expiresMsg = "The request has already been expires. Please make a request for new password";
			model.addAttribute("passwordHasexpires", expiresMsg);
			return "resetPassword";
		}

		String erroMsg = "Something went wrong when conforming the request for new password."
				+ "You could try the link again and see if it will work."
				+ "If not you could try again request for new password."; 
				

		model.addAttribute("erroMessage", erroMsg);
		return "resetPassword";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam(value = "_key") String resetPasswordToken,
			@RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm,
			final Model model) {
		
		String PasswordValidation = resetPasswValidation.validate(password, passwordConfirm);
		
		if (PasswordValidation != "") {
			model.addAttribute("passwordErro", PasswordValidation );
			model.addAttribute("resetPasswordToken", resetPasswordToken); 
			return "resetPassword";
		}

		User userToUpdate = userService.findByResetPasswordToken(resetPasswordToken);
		userToUpdate.setPassword(password);
		userToUpdate.setResetpasswordtoken(null);
		userToUpdate.setResetpasswordexpires(null);
		userService.save(userToUpdate);

		String responseMessage = "Your password was successfully updated";
		model.addAttribute("responseMessage", responseMessage);
		return "resetPassword";

	}


}

