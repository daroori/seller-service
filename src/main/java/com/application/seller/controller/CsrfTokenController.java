package com.application.seller.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfTokenController {
    @GetMapping("/csrf")
    @ResponseBody
    public CsrfToken csrf(HttpServletRequest http){
        return (CsrfToken) http.getAttribute(CsrfToken.class.getName());
    }
}
