package com.boot.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CookieExpiryRefresher extends HandlerInterceptorAdapter {


    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler) throws Exception {


        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies){
            if (cookie.getName().contentEquals("token")){
                if (cookie.getValue().contentEquals(request.getSession().getId())){
                    cookie.setMaxAge(60*60*5);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }

    }

}