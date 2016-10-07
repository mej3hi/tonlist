package com.tonlist.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tonlist.model.MimeMailSender;
import com.tonlist.model.User;
import com.tonlist.service.UserService;
import com.tonlist.validator.UserValidator;

@Controller
public class ForgetpasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private MimeMailSender mimeMailSender;

	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String resetPasswordView(final Model model) {
		model.addAttribute("user", new User());
		return "/forget-password";
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public String forgetPassword(@ModelAttribute("user") User user, final Model model) throws MessagingException, IOException {
		//model.addAttribute("user", user);

		// logger.info("UserTransmittedEmail# :" + user.getEmail());
		User foundUser = userService.findByEmail(user.getEmail());
		if (foundUser != null) {
			String secureToken = UUID.randomUUID().toString();
			//foundUser.setResetPasswordToken(secureToken);
			/*
			 * Give token one hour expiration delay
			 */
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			Date expirationDate = calendar.getTime();
			// logger.info("Expriration date :" + expirationDate);
			//foundUser.setResetPasswordExpires(expirationDate);
			/*
			 * Update user into database
			 */
			userService.save(foundUser);
			String text = "You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n"
					+ "Please click on the following link, or paste into your browser to complete the reset password process :\n\n"
					+ ServletUriComponentsBuilder.fromCurrentContextPath().path("/resetPassword")
							.queryParam("_key", secureToken).build().toUriString()
					+ "\n\n If you did not request this, please ignore this email and your password will remain unchanged.";
			mimeMailSender.sendMail(foundUser.getEmail(), "Password reset request", text);

			String responseMessage = "A mail has been sent to your mail box";
			model.addAttribute("responseMessage", responseMessage);

			return "forgetPassword";
		}

		String responseMessage = "Invalid address mail.This account doesn't exist";
		model.addAttribute("invalidMailAddress", responseMessage);

		return "forgetPassword";
	};

}
