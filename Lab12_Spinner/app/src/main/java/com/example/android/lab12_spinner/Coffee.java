package com.example.android.lab12_spinner;

import java.io.Serializable;

public class Coffee implements Serializable{
    private String title;
    private int price;
    private int imgId;

    public Coffee(String title, int price, int img_resource_id) {
        this.title = title;
        this.price = price;
        this.imgId = img_resource_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int img_resource_id) {
        this.imgId = img_resource_id;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", imgId=" + imgId +
                '}';
    }
}
