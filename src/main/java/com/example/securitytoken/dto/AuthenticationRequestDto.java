package com.example.securitytoken.dto;

import lombok.Data;

//класс для аутентификации логина и запроса
@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
