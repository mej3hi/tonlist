package com.tonlist.service;


import com.tonlist.persistence.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    User findByEmail(String email);
    
    User findByResetPasswordToken(String token);
}
