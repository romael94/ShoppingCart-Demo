package com.Romael.ShoppingCart.exceptions;

import com.Romael.ShoppingCart.model.Products;


public class ProductStockException extends Exception {
    public ProductStockException(){
        super("Product is out of stock");
    }
    public ProductStockException(Products product){
        super(product.getName() + " is Out of Stock");
    }
}
