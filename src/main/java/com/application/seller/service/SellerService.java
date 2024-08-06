package com.application.seller.service;
import com.application.seller.exception.UserNotFound;
import com.application.seller.model.Seller;
import com.application.seller.repository.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepo sellerRepo;

    public Seller findById(Long id) {
        return sellerRepo.findById(id)
                .orElseThrow(() -> new UserNotFound("Seller not found"));
    }

    public List<Seller> getAllSellers() {
        return sellerRepo.findAll();
    }

    public void deleteById(Long id) {
        Optional<Seller> existingSeller = sellerRepo.findById(id);
        if(existingSeller.isPresent()){
            sellerRepo.deleteById(id);
        }else {
            throw new UserNotFound("User not found with ID " + id);
        }
    }

    public Seller updateSeller(Long id, Seller seller) {
        Optional<Seller> existingSeller = sellerRepo.findById(id);

        if(existingSeller.isPresent()){
            Seller updatedSeller = new Seller();

            updatedSeller.setUsername(seller.getUsername());
            updatedSeller.setPassword(seller.getPassword());
            updatedSeller.setId(seller.getId());
            updatedSeller.setName(seller.getName());
            updatedSeller.setEmail(seller.getEmail());
            return updatedSeller;

        }else {
            throw new UserNotFound("User not found with ID " + id);
        }
    }
}
