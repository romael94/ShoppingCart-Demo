package com.Romael.ShoppingCart.repository;

import com.Romael.ShoppingCart.exceptions.ProductStockException;
import com.Romael.ShoppingCart.model.Products;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;

@Repository
public interface CartRepository {

    void addProduct(Products product) throws ProductStockException;

    void removeProduct(Products product);

    Map<Products,Integer> getProductsInCart();

    void checkOut() throws ProductStockException;

    Double getValue();
}
