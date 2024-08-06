package com.application.seller.service;

import com.application.seller.model.Seller;
import com.application.seller.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SellerDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SellerRepo sellerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Seller seller = sellerRepo.findByUsername(username);
            return User.withUsername(seller.getUsername())
                    .password(seller.getPassword())
                    .authorities("SELLER")
                    .build();
        }catch (UsernameNotFoundException ex){
            throw new UsernameNotFoundException("User not found with Username " + username);
        }
    }
}
