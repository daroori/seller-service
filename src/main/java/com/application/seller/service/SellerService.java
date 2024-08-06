package com.application.seller.service;
import com.application.seller.exception.UserAlreadyExisting;
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
            Seller sellerToUpdate = existingSeller.get();

            Optional<Seller> checkSeller = Optional.ofNullable(sellerRepo.findByUsername(seller.getUsername()));
            if(checkSeller.isPresent() && (checkSeller.get().getId() != sellerToUpdate.getId())){
                throw new UserAlreadyExisting("Username is already taken");
            }

            sellerToUpdate.setUsername(seller.getUsername());
            sellerToUpdate.setPassword(seller.getPassword());
            sellerToUpdate.setName(seller.getName());
            sellerToUpdate.setEmail(seller.getEmail());

            return sellerRepo.save(sellerToUpdate);

        }else {
            throw new UserNotFound("User not found with ID " + id);
        }
    }
}
