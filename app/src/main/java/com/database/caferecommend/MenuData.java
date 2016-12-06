package com.database.caferecommend;

/**
 * Created by Administrator on 2016-12-04.
 */

//각 카페별 메뉴의  이미지, 이름 , 가격 정보를 위해 사용한다.
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
