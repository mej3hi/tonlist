package com.tonlist.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tonlist.persistence.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
