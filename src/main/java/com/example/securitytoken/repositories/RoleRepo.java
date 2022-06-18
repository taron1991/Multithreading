package com.example.securitytoken.repositories;

import com.example.securitytoken.models.Role;
import com.example.securitytoken.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
