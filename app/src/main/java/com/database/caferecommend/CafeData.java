package com.database.caferecommend;

/**
 * Created by Administrator on 2016-11-25.
 */

public class CafeData {

    private int image;
    private String name;
    private String tel;
    private int avg;

    public CafeData(int image, String name, String tel, int avg){
        this.image = image;
        this.name = name;
        this.tel = tel;
        this.avg=avg;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public int getAvg(){
        return avg;
    }

}

