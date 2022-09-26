package com.example.groceryapp.models;

public class ModelOrders {
    String id;
    int ImageRes;
    String count;

    public ModelOrders(String id, int imageRes, String count) {
        this.id = id;
        ImageRes = imageRes;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public int getImageRes() {
        return ImageRes;
    }

    public String getCount() {
        return count;
    }
}
