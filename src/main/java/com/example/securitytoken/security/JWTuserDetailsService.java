package com.example.securitytoken.security;

import com.example.securitytoken.models.User;
import com.example.securitytoken.security.jwt.JwtUser;
import com.example.securitytoken.security.jwt.JwtUserFactory;
import com.example.securitytoken.services.Userservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
/**
 * класс своя реализация Userdetailservice
 */
public class JWTuserDetailsService implements UserDetailsService {


    private final Userservice userService;

    @Autowired
    public JWTuserDetailsService(@Lazy Userservice userService) {
        this.userService = userService;
    }

    //метод который на основании найденного usera по username генерит JwtUsera который является имплементацией Userdetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
