package com.Romael.ShoppingCart.api;

import com.Romael.ShoppingCart.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    //Display All Products
    @GetMapping("/products")
    public ModelAndView getAllProducts(){
        ModelAndView modelAndView = new ModelAndView("/products");
        modelAndView.addObject("products",productsService.getAllProducts());

        return modelAndView;
    }

}
