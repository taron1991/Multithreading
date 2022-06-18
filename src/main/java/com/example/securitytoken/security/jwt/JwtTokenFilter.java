package com.example.securitytoken.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//класс который будет фильтровать запросы на наличие токена тоесть нет токена нет части
//с помощью провайдера валидирует токен т.е каждый запрос проходит через фильтр и
// если токен валидный то даем аутентификацию данному запросу и он будет обработан
public class JwtTokenFilter  extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //каждый запрос нужно валидировать если есть токен то будет часть,если не будет то и аутентификации не будет
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        //получаю токен из request
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            //если токен валидный то я передаю аутентификацию
            Authentication auth = jwtTokenProvider.getAuthentication(token);

            if (auth != null) {
                //деалем аутентификацию
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }
}
