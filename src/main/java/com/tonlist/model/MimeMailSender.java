package com.tonlist.model;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MimeMailSender {
	
	@Autowired
	private JavaMailSender mailSender;	
	
	/**
	 * It send email to given email address.
	 * @param to The email address.
	 * @param subject The subject about email.
	 * @param body The text to send.
	 * @throws MessagingException
	 */
	public void sendMail(String to, String subject, String body) throws MessagingException {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true); 
		helper.setTo(to);											   
		helper.setSubject(subject);
		helper.setText(body, true);  
		
		mailSender.send(message);
		
		
	}

}
