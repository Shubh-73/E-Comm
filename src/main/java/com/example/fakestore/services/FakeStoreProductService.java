package com.example.fakestore.services;

import com.example.fakestore.models.Category;
import com.example.fakestore.models.Product;
import com.example.fakestore.productDTOs.ProductRequestDto;
import com.example.fakestore.productDTOs.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements IProductService{


    RestTemplate restTemplate;


    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductFromResponseDto(ProductResponseDto productResponseDto) {
        Product product = new Product();
        product.setTitle(productResponseDto.getTitle());
        product.setPrice(productResponseDto.getPrice());
        product.setDescription(productResponseDto.getDescription());
        Category category = new Category();
        category.setName(productResponseDto.getCategory());
        product.setCategory(category);
        product.setImage(productResponseDto.getImage());

        return product;
    }

    public ProductRequestDto getRequestDtoFromProduct(Product product) {
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle(product.getTitle());
        productRequestDto.setPrice(product.getPrice());
        productRequestDto.setDescription(product.getDescription());
        productRequestDto.setImage(product.getImage());
        productRequestDto.setCategory(product.getCategory().getName());
        return productRequestDto;
    }


    @Override
    public Product getSingleProduct(Long id){

        ProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, ProductResponseDto.class
        );


        return getProductFromResponseDto(responseDto);
    }

    @Override
    public List<Product> getAllProducts() {

        ProductResponseDto[] responseDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products", ProductResponseDto[].class
        );


        List<Product> products = new ArrayList<>();
        for (ProductResponseDto productResponseDto : responseDtos) {
            products.add(getProductFromResponseDto(productResponseDto));

        }

        return products;
    }

    @Override
    public Product replaceProduct(Long id , Product product){

        ProductRequestDto requestDto = getRequestDtoFromProduct(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDto, ProductResponseDto.class);

        HttpMessageConverterExtractor<ProductResponseDto> responseExractor

                = new HttpMessageConverterExtractor(
                        ProductResponseDto.class,
                restTemplate.getMessageConverters()
        );

        ProductResponseDto productResponseDto = restTemplate.execute(
                "https://fakestoreapi.com/products" + id,
                HttpMethod.PUT,
                requestCallback,
                responseExractor
        );


        return getProductFromResponseDto(productResponseDto);
    }

    @Override
    public Product addProduct(Product product){

        ProductRequestDto requestDto = getRequestDtoFromProduct(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDto, ProductResponseDto.class);

        HttpMessageConverterExtractor<ProductResponseDto> responseExractor

                = new HttpMessageConverterExtractor(
                ProductResponseDto.class,
                restTemplate.getMessageConverters()
        );

        ProductResponseDto productResponseDto = restTemplate.execute(
                "https://fakestoreapi.com/products" ,
                HttpMethod.PUT,
                requestCallback,
                responseExractor
        );


        return getProductFromResponseDto(productResponseDto);

       // return null;
    }


    @Override
    public Product updateProduct(Product product){


        ProductRequestDto requestDto = getRequestDtoFromProduct(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDto, ProductResponseDto.class);

        HttpMessageConverterExtractor<ProductResponseDto> responseExractor

                = new HttpMessageConverterExtractor(
                ProductResponseDto.class,
                restTemplate.getMessageConverters()
        );

        ProductResponseDto productResponseDto = restTemplate.execute(
                "https://fakestoreapi.com/products" ,
                HttpMethod.PATCH,
                requestCallback,
                responseExractor
        );


        return getProductFromResponseDto(productResponseDto);
        //return null;
    }

}
