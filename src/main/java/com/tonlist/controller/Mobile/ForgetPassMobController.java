package com.tonlist.controller.Mobile;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tonlist.extraUtilities.MimeMailSender;
import com.tonlist.persistence.entities.User;
import com.tonlist.service.UserService;

@RestController
public class ForgetPassMobController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private MimeMailSender mimeMailSender;
	
	/**
	 * Get called when Post mapping url ("/forgetPassword") is accessed with POST.
	 * It send mail to the user with with link to restore his password.
	 * @param email Is the email from the form.
	 * @param model Send over msg for the email.
	 * @return forgetPassword html page.
	 * @throws MessagingException
	 * @throws IOException
	 */
	@PostMapping("/m/forgetPassword")
	public String postForgetPassword(@RequestParam("email") String email) throws MessagingException, IOException {
				
		User foundUser = userService.findByEmail(email);
	
		if (foundUser != null) {
			
	        SecureRandom random = new SecureRandom();
	        String secureToken = new BigInteger(40, random).toString(32);
			
			foundUser.setResetpasswordtoken(secureToken);
		
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			Date expirationDate = calendar.getTime();			
			foundUser.setResetpasswordexpires(expirationDate);			
		
			userService.save(foundUser);
			String text = "You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n"
					+ "\n\n"
					+ "Here is token to copy :  "+secureToken+"  "
					+ "\n\n If you did not request this, please ignore this email and your password will remain unchanged.";
			
			mimeMailSender.sendMail(foundUser.getEmail(), "Password reset request", text);
			
			return "\"ok\"";
		} 

		return "\"invalid_email\"";
	};

}
