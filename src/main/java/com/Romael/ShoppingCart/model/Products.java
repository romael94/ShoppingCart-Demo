package com.Romael.ShoppingCart.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.Integer;

//Product class that simulates the products table in the DB
@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "productid")
    private Integer productid;

    @NotNull
    @Column(name = "name")
    private String Name;

    @NotNull
    @Column(name = "price")
    private Double Price;

    @NotNull
    @Column(name = "stock")
    private Integer Stock;

    @NotNull
    @Column(name = "image")
    private String Image;


    //Default Constructor
    public Products(){

    }

    //Constructor
    public Products(@NotNull Integer productid,
                    @NotNull String name,
                    @NotNull Double price,
                    @NotNull Integer stock,
                    @NotNull String image) {
        super();
        this.productid = productid;
        Name = name;
        Price = price;
        Stock = stock;
        Image = image;
    }

    //Getters
    public Integer getProductid() {
        return productid;
    }

    public String getName() {
        return Name;
    }

    public Double getPrice() {
        return Price;
    }

    public Integer getStock() {
        return Stock;
    }

    public String getImage() {
        return Image;
    }

    //Setters
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }

    public void setStock(Integer stock) {
        this.Stock = stock;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        return productid.equals(products.productid);
    }

    @Override
    public int hashCode(){
        return productid.hashCode();
    }
}
