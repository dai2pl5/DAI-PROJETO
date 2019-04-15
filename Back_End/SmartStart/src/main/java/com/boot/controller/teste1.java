package com.boot.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
@RestController
public class teste1 {

	@GetMapping("/")
	public void testarCookie(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("está a funcionar");
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
