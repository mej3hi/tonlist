package com.tonlist.service;


import com.tonlist.persistence.entities.User;
import com.tonlist.persistence.repository.RoleRepository;
import com.tonlist.persistence.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Save user in database and bcrypt the user password.
	 * @param user Is the user object.
	 */
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

	/**
	 * Find user with a specific username.
	 * @param username Is the specific username.
	 * @return User.
	 */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find email with a specific email.
     * @param email Is the spacific email.
     * @return user.
     */ 
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);		
	}

	/**
	 * Find reset Password token with a specific token.
	 * @param token Is the spacific token.
	 * @return User.
	 */ 
	@Override
	public User findByResetPasswordToken(String token) {
		return userRepository.findByresetpasswordtoken(token);
		
	}
}
