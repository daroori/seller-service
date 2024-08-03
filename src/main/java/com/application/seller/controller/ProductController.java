package com.application.seller.controller;

import com.application.seller.dto.ProductRequest;
import com.application.seller.model.Product;
import com.application.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping ("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestPart ProductRequest productReq,
                                               @RequestPart MultipartFile image) throws IOException {

        System.out.println(productReq);
        System.out.println(image);
        Product prod = service.saveProduct(productReq,image);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id){
        Product prod = service.getProductById(id);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> prod = service.getAllProducts();
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestPart Product product,
                                                 @RequestPart MultipartFile file) throws IOException {
        Product prod = service.updateProduct(id,product, file);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id)  {
        Product prod = service.deleteProduct(id);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }
}
