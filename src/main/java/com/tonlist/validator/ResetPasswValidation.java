package com.tonlist.validator;

import org.springframework.stereotype.Component;

/**
 * 
 * Validate the Reset Password form.
 */
@Component
public class ResetPasswValidation {

	public String validate(String password, String passwordConfirm) {
		String passwErro = "";
		
		if (password.length() < 8 || password.length() > 32) {
			passwErro += "Try one with at least 8 characters.";
		}
		
		if (!passwordConfirm.equals(password)) {
			passwErro += "These passwords don't match.";
        }
		return passwErro;
				 
	 }


}
