package com.example.groceryapp.models;

public class TestModelcat1 {
    String title;
    int Imageres;
    String price;

    public TestModelcat1(String title, int imageres, String price) {
        this.title = title;
        Imageres = imageres;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getImageres() {
        return Imageres;
    }

    public String getPrice() {
        return price;
    }
}
