package com.application.seller.controller;

import com.application.seller.model.Seller;
import com.application.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @GetMapping( "/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id){
        Seller existingSeller = service.findById(id);
        return new ResponseEntity<>(existingSeller,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers(){
        return new ResponseEntity<>(service.getAllSellers(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return new ResponseEntity<>("Seller with Id " + id + " Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> updatedSeller(@PathVariable Long id,
                                                @RequestBody Seller seller){
       Seller updatedSeller =  service.updateSeller(id,seller);
        return new ResponseEntity<>(updatedSeller,HttpStatus.OK);
    }
}
