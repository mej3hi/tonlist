package com.tonlist.service;


import com.tonlist.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
