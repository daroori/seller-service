package com.application.seller.repository;

import com.application.seller.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Long> {
    Seller findByUsername(String username);
}
