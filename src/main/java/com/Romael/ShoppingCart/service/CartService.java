package com.Romael.ShoppingCart.service;

import com.Romael.ShoppingCart.exceptions.ProductStockException;
import com.Romael.ShoppingCart.model.Products;
import com.Romael.ShoppingCart.repository.CartRepository;
import com.Romael.ShoppingCart.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Service
@Transactional
public class CartService implements CartRepository {

    private ProductsRepository productsRepository;
    ProductStockException productStockException;

    @Autowired
    public CartService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    //Initilization of cart map
    private Map<Products,Integer> products = new HashMap<>();


    //adds product to cart if product is in stock, throws exception if out of stock
    @Override
    public void addProduct(Products product) throws ProductStockException{
        if(product.getStock() > 0) {
            if (products.containsKey(product)) {
                products.replace(product, products.get(product) + 1);
            } else {
                products.put(product, 1);
            }
        }
        else{
            throw new ProductStockException(product);
        }


    }

    //remove product and all its quantities in cart
    @Override
    public void removeProduct(Products product) {
        if(products.containsKey(product)){
            products.remove(product);
        }
    }

    //updates product stock and clears the cart, throws exception for out of stock item
    @Override
    public void checkOut() throws ProductStockException{

        Products product;
        for(Map.Entry<Products, Integer> entry : products.entrySet()){

            product = productsRepository.getOne(entry.getKey().getProductid());

            if(product.getStock() < entry.getValue()){
                throw new ProductStockException(product);
            }
            else
                entry.getKey().setStock(product.getStock() - entry.getValue());

        }
        productsRepository.saveAll(products.keySet());
        productsRepository.flush();
        products.clear();
    }

    //gets all products in cart
    @Override
    public Map<Products, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    //gets total value of products in cart
    @Override
    public Double getValue() {
        Double itemTotal;
        Double cartTotal = 0.00;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        for(Map.Entry<Products, Integer> entry : products.entrySet()){
            itemTotal = entry.getKey().getPrice() * entry.getValue();
            cartTotal += itemTotal;
        }
        decimalFormat.format(cartTotal);
        return cartTotal;
    }
}
