package com.example.securitytoken.repositories;


import com.example.securitytoken.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "select * from users where username=:name", nativeQuery = true)
    User findByUsername(@Param("name") String name);
}
