package com.example.fakestore.controllers;


import com.example.fakestore.models.Category;
import com.example.fakestore.models.Product;
import com.example.fakestore.productDTOs.ProductResponseDto;
import com.example.fakestore.services.IProductService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService iProductService;
    //AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController( IProductService iProductService) {

        this.iProductService = iProductService;
    }


    @GetMapping()
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> productList = iProductService.getAllProducts();

        ResponseEntity responseEntity = new ResponseEntity(productList, HttpStatus.OK);
        return responseEntity;

    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long id) {
        ResponseEntity<Product> responseEntity;
        Product product = iProductService.getSingleProduct(id);
        responseEntity = new ResponseEntity(product, HttpStatus.OK);
        return responseEntity;
    }


//    @GetMapping("/products/{productId}")
//    public String getProductDetails(@PathVariable("productId") String productId) {
//        return "productId: " + productId;
//    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        return categories;
    }


    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable("categoryName") String categoryName){
        List<Product> products = new ArrayList<>();
        return products;

    }

    @PostMapping()
    public Product addProduct(@RequestBody ProductResponseDto productDTO) {
       return null;
    }

    @PutMapping()
    public Product replaceProduct(@RequestBody ProductResponseDto productDTO) {
        return new Product();
    }
}
