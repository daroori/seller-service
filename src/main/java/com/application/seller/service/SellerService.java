package com.application.seller.service;

import com.application.seller.exception.UserNotFound;
import com.application.seller.model.Seller;
import com.application.seller.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SellerService implements UserDetailsService {

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerRepo.findByUsername(username);
        if(seller == null){
            throw new UserNotFound("Username with " + username + " Not found");
        }
        return new User(seller.getUsername(), seller.getPassword(),new ArrayList<>());
    }

    public Seller login(Seller user){
        Seller credentials = sellerRepo.findByUsername(user.getUsername());
        if(credentials == null){
            throw new UserNotFound("Username with " + user.getUsername() + " Not found");
        }
        if(passwordEncoder.matches(user.getPassword(),credentials.getPassword()))
        {
            return credentials;
        }
        throw new UserNotFound("Invalid username / password");
    }

    public Seller register(Seller credentials){
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        return sellerRepo.save(credentials);
    }

}
