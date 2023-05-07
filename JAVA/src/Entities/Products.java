package Entities;

import Utils.Constants;

import java.time.LocalDate;

public class Products implements Comparable<Products> {
    
    private int id;
    private String title;
    private String image;
    private float price;
    
    public Products(int id, String title, String image, float price) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public Products(String title, String image, float price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public Products(String title, float price) {
        this.title = title;
        
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    

    @Override
    public int compareTo(Products products) {
        switch (Constants.compareVar) {
            case "Title":
                 return products.getTitle().compareTo(this.getTitle());
            case "Image":
                 return products.getImage().compareTo(this.getImage());
            case "Price":
                return Float.compare(products.getPrice(), this.getPrice());
            
            default:
                return 0;
        }
    }
    
}