package com.example.securitytoken.services.impl;

import com.example.securitytoken.models.Role;
import com.example.securitytoken.models.Status;
import com.example.securitytoken.models.User;
import com.example.securitytoken.repositories.RoleRepo;
import com.example.securitytoken.repositories.UserRepo;
import com.example.securitytoken.services.Userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements Userservice {

    private RoleRepo roleRepo;
    private UserRepo userRepo;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepo roleRepo, UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    //при регистрации новым пользователям присваиваем роль
    @Override
    public User register(User user) {
        Role role_user = roleRepo.findByName("role user");
        List<Role> list = new ArrayList<>();
        list.add(role_user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(list);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepo.save(user);
        log.info("successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepo.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepo.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepo.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
