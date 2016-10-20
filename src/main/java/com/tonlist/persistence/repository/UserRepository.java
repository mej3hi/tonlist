package com.tonlist.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tonlist.persistence.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

	User findByEmail(String email);

	User findByresetpasswordtoken(String token);
    

}
