package com.tonlist.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tonlist.extraUtilities.MimeMailSender;
import com.tonlist.persistence.entities.User;
import com.tonlist.service.UserService;


@Controller
public class ForgetpasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private MimeMailSender mimeMailSender;

	/**
	 * Get called when Get mapping url ("/forgetPassword") is accessed with GET.
	 * It open forgot form page for the user. 
	 * @return forgetPassword html page.
	 */
	@GetMapping("/forgetPassword")
	public String forgetPassword() {
		return "forgetPassword";
	}

	/**
	 * Get called when Post mapping url ("/forgetPassword") is accessed with POST.
	 * It send mail to the user with with link to restore his password.
	 * @param email Is the email from the form.
	 * @param model Send over msg for the email.
	 * @return forgetPassword html page.
	 * @throws MessagingException
	 * @throws IOException
	 */
	@PostMapping("/forgetPassword")
	public String  postForgetPassword(@RequestParam("email") String email,Model model) throws MessagingException, IOException {
				
		User foundUser = userService.findByEmail(email);
	
		if (foundUser != null) {
			
			String secureToken = UUID.randomUUID().toString();
			foundUser.setResetpasswordtoken(secureToken);
		
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			Date expirationDate = calendar.getTime();			
			foundUser.setResetpasswordexpires(expirationDate);			
		
			userService.save(foundUser);
			String text = "You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n"
					+ "Please click on the following link, or paste into your browser to complete the reset password process :\n\n"
					+ ServletUriComponentsBuilder.fromCurrentContextPath().path("/resetPassword")
							.queryParam("_key", secureToken).build().toUriString()
					+ "\n\n If you did not request this, please ignore this email and your password will remain unchanged.";
			mimeMailSender.sendMail(foundUser.getEmail(), "Password reset request", text);

			String foundMailMsg = "A mail has been sent to you";
			model.addAttribute("foundMailAddress", foundMailMsg);

			return "forgetPassword";
		} 

		String invalidMailMsg = "Invalid address mail.This account doesn't exist";
		model.addAttribute("invalidMailAddress", invalidMailMsg);
		

		return "forgetPassword";
	};

}
