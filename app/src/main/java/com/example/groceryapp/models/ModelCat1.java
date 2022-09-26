package com.example.groceryapp.models;

public class ModelCat1 {
    String title,des,price;
    int Images;

    public ModelCat1(String title, String des, String price, int images) {
        this.title = title;
        this.des = des;
        this.price = price;
        Images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImages() {
        return Images;
    }

    public void setImages(int images) {
        Images = images;
    }
}
