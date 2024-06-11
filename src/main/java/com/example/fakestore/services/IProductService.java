package com.example.fakestore.services;

import com.example.fakestore.models.Product;
import com.example.fakestore.productDTOs.ProductRequestDto;
import com.example.fakestore.productDTOs.ProductResponseDto;

import java.util.List;

public interface IProductService {

    public Product getSingleProduct(Long id);

    public List<Product> getAllProducts();

    public Product replaceProduct(Long id, Product product);

    public Product addProduct(Product product);

    public Product updateProduct(Product product);
}
