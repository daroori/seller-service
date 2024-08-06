package com.application.seller.controller;
import com.application.seller.exception.UserAlreadyExisting;
import com.application.seller.model.Seller;
import com.application.seller.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Seller seller){
        Optional<Seller> existingSeller = Optional.ofNullable(sellerRepo.findByUsername(seller.getUsername()));
        if(existingSeller.isPresent()){
            throw new UserAlreadyExisting("Username is already taken");
        }

        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        sellerRepo.save(seller);
        return new ResponseEntity<>("Seller Registered Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("Seller logged in successfully",HttpStatus.OK);
    }
}

