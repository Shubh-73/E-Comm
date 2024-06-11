package com.example.fakestore.productDTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {


    private String title;
    private double price;
    private String description;
    private String image;
    private String category;


}
