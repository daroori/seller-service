package com.application.seller.service;

import com.application.seller.dto.ProductRequest;
import com.application.seller.exception.ProductNotFound;
import com.application.seller.model.Product;
import com.application.seller.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product saveProduct(ProductRequest productReq, MultipartFile file) throws IOException {
        Product product = convertToEntity(productReq);
        if(file != null) {
           product.setImage(file.getBytes());
           return productRepo.save(product);
        }else {
            throw new RuntimeException("Product Image Required");
        }
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(Long id){
        return productRepo.findById(id).
                orElseThrow(() -> new ProductNotFound("Product with Id :" + id + " not found"));
    }

    public Product updateProduct(Long id, Product product, MultipartFile file) throws IOException {

       return productRepo.findById(id)
               .map(existingProduct -> {
                   existingProduct.setName(product.getName());
                   existingProduct.setDescription(product.getDescription());
                   existingProduct.setPrice(product.getPrice());
                   existingProduct.setQuantity(product.getQuantity());
                   existingProduct.setAvailability(product.isAvailability());

                   try {
                       existingProduct.setImage(file.getBytes());
                   }catch (IOException e){
                       throw new RuntimeException("Failed to upload image",e);
                   }
                   return productRepo.save(existingProduct);
        }
        ).orElseThrow(() -> new ProductNotFound("Product with Id :" + product.getId() + " not found"));
    }

    public Product deleteProduct(Long id){
        Product prod = productRepo.findById(id).
                orElseThrow(() -> new ProductNotFound("\"Product with Id :\" + id + \" not found\""));
        productRepo.deleteById(id);
        return prod;
    }

    //Converts ProductRequest to Product Entity
    private Product convertToEntity(ProductRequest productReq) {
        Product product = new Product();
        product.setId(productReq.getId());
        product.setName(productReq.getName());
        product.setDescription(productReq.getDescription());
        product.setAvailability(productReq.isAvailability());
        product.setPrice(productReq.getPrice());
        product.setQuantity(productReq.getQuantity());

        return product;
    }

}
