package com.database.caferecommend;

/**
 * Created by Administrator on 2016-12-04.
 */

public class MenuData {
    private int image;  //메뉴 이미지
    private String name; //메뉴이름
    private String price; //메뉴가격

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
