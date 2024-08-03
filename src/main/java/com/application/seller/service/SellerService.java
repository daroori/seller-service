package com.application.seller.service;

import com.application.seller.exception.UserNotFound;
import com.application.seller.model.SellerDetails;
import com.application.seller.repository.SellerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private SellerDetailsRepo sellerRepo;

    public SellerDetails login(SellerDetails user){
        SellerDetails credentials = sellerRepo.findByUsername(user.getUsername());
        if(credentials == null){
            throw new UserNotFound("Username with " + user.getUsername() + " Not found");
        }
        if(credentials.getPassword().equals(user.getPassword()))
        {
            return credentials;
        }
        throw new UserNotFound("Invalid username / password");
    }

    public SellerDetails register(SellerDetails credentials){
        return sellerRepo.save(credentials);
    }
}
