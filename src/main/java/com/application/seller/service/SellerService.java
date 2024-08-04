package com.application.seller.service;

import com.application.seller.exception.UserNotFound;
import com.application.seller.model.Seller;
import com.application.seller.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellerService implements UserDetailsService {

    @Autowired
    private SellerRepo sellerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerRepo.findByUsername(username);
        if(seller == null){
            throw new UserNotFound("Username with " + username + " Not found");
        }
        return seller;
    }

    public Seller login(Seller user){
        Seller credentials = sellerRepo.findByUsername(user.getUsername());
        if(credentials == null){
            throw new UserNotFound("Username with " + user.getUsername() + " Not found");
        }
        if(credentials.getPassword().equals(user.getPassword()))
        {
            return credentials;
        }
        throw new UserNotFound("Invalid username / password");
    }

    public Seller register(Seller credentials){
        return sellerRepo.save(credentials);
    }

}
