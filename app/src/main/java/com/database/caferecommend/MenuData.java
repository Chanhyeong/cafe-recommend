package com.database.caferecommend;

/**
 * Created by Administrator on 2016-12-04.
 */

public class MenuData {
    private int image;
    private String name;
    private String price;

    public MenuData(int image, String name, String price){
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

}
