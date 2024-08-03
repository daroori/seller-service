package com.application.seller.controller;

import com.application.seller.model.SellerDetails;
import com.application.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @PostMapping("/register")
    public ResponseEntity<SellerDetails> register(@RequestBody SellerDetails cred){
        SellerDetails user = service.register(cred);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SellerDetails> login(@RequestBody SellerDetails cred){
        SellerDetails user = service.login(cred);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
