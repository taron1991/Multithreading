package com.example.securitytoken.services;



import com.example.securitytoken.models.User;

import java.util.List;

public interface Userservice {

    User register(User user);
    List<User> getAll();
    User findByUsername(String name);
    User findById(Long id);
    void delete(long id);
}
