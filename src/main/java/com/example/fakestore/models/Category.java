package com.example.fakestore.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category  extends BaseModel{

    //private Long id;
    private String name;
}
