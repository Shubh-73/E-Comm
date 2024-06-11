package com.example.fakestore.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    //private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private Category category;


}
