package com.example.fakestore.productDTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
