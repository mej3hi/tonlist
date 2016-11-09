package com.tonlist.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tonlist.persistence.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find user with a specific username.
	 * @param username Is the specific username.
	 * @return User.
	 */
    User findByUsername(String username);
    
    /**
     * Find email with a specific email.
     * @param email Is the spacific email.
     * @return user.
     */
	User findByEmail(String email);

	/**
	 * Find reset Password token with a specific token.
	 * @param token Is the spacific token.
	 * @return User.
	 */
	User findByresetpasswordtoken(String token);
    

}
