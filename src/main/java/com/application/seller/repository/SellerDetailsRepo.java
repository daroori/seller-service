package com.application.seller.repository;

import com.application.seller.model.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDetailsRepo extends JpaRepository<SellerDetails, Long> {
    SellerDetails findByUsername(String username);
}
