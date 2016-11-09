package com.tonlist.service;

public interface SecurityService {
	
	/**
	 * Find login user.
	 * @return String username.
	 */
    String findLoggedInUsername();

    /**
     * It auto login in user. 
     * @param username Specific username
     * @param password Specific password
     */
    void autologin(String username, String password);
}
