package com.example.groceryapp.models;

public class ModelCart {
    int imageRes;
    String productName;

    public ModelCart(int imageRes, String productName) {
        this.imageRes = imageRes;
        this.productName = productName;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getProductName() {
        return productName;
    }
}
