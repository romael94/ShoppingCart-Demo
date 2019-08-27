package com.Romael.ShoppingCart.service;

import com.Romael.ShoppingCart.model.Products;
import com.Romael.ShoppingCart.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.OrderBy;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService{

    private ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    //Get all Products in DB
    public List<Products> getAllProducts() {

        List<Products> products = new ArrayList<>();

        productsRepository.findAll().forEach(products::add);

        return products;
    }

    //Find product by {productid}
    public Optional<Products> findById(Integer productid) {
        return productsRepository.findById(productid);
    }

}
