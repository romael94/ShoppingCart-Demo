package com.Romael.ShoppingCart.api;

import com.Romael.ShoppingCart.exceptions.ProductStockException;
import com.Romael.ShoppingCart.model.Products;
import com.Romael.ShoppingCart.service.CartService;
import com.Romael.ShoppingCart.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/mycart")
public class CartController{

    private final CartService cartService;
    private final ProductsService productsService;

    @Autowired
    public CartController(CartService cartService, ProductsService productsService) {
        this.cartService = cartService;
        this.productsService = productsService;
    }

    //Get all Products in Cart
    @GetMapping
    public ModelAndView mycart(){
        ModelAndView modelAndView = new ModelAndView("/mycart");
        modelAndView.addObject("products", cartService.getProductsInCart());
        modelAndView.addObject("cartTotal",cartService.getValue().toString());

        return modelAndView;
    }

    //Add Product to Cart
    @GetMapping("/addproduct/{productid}")
    public ModelAndView addProductToCart(@PathVariable("productid") Products product){

            if (productsService.findById(product.getProductid()).isPresent()) {
                try {
                    cartService.addProduct(product);
                } catch (ProductStockException e){
                    return mycart().addObject("OutofStockMsg",e.getMessage());
                }

            }
        return mycart();
    }


    //Remove Product from Cart
    @GetMapping("/removeproduct/{productid}")
    public ModelAndView removeProduct(@PathVariable("productid") Integer productid){
        productsService.findById(productid).ifPresent(cartService::removeProduct);
        return mycart();
    }

    //Checkout
    @GetMapping("/checkout")
    public ModelAndView checkout(){
        try {
            cartService.checkOut();
        } catch (ProductStockException e){
            return mycart().addObject("OutofStockMsg", e.getMessage());
        }
        return mycart();
    }

}
