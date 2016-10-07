package com.tonlist.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tonlist.model.User;
import com.tonlist.service.UserService;

@Controller
public class ResetPasswordController {

	@Autowired
	private UserService userService;


	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetpasswordView(@RequestParam(value = "_key") String resetPasswordToken, final Model model) {
		User user = userService.findByResetPasswordToken(resetPasswordToken);
		Date expirationDate;
		if (user != null) {
			/*expirationDate = user.getResetPasswordExpires();
			if (expirationDate.after(new Date())) {
				model.addAttribute("user", user);
				model.addAttribute("resetPasswordToken", resetPasswordToken);
				return "resetPassword";
			}*/
		}
		return "/error";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(@RequestParam(value = "_key") String resetPasswordToken, @ModelAttribute("user") User user,
			final Model model) {
		//logger.info("UserLogin# " + user.getEmail() + "  UserPassword# " + user.getPassword());
		User userToUpdate = userService.findByResetPasswordToken(resetPasswordToken);
		String uptadedPassword = user.getPassword();
		userToUpdate.setPassword(encryptPassword(uptadedPassword));
		//userToUpdate.setResetPasswordToken(null);
		//userToUpdate.setResetPasswordExpires(null);
		userService.save(userToUpdate);

		//boolean passwordChanged = true;
		//String redirectionUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/").build()
				//.toUriString();
		String responseMessage = "Your password was successfully updated";
		//model.addAttribute("passwordChanged", passwordChanged);
		//model.addAttribute("redirectionUrl", redirectionUrl);
		model.addAttribute("responseMessage", responseMessage);
		return "resetPassword";
	}

	private String encryptPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}