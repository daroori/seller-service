package com.application.seller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private long id;
    private String name;
    private String description;
    private double price;
    private long quantity;
    private boolean availability;
}
