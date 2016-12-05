package com.database.caferecommend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016-11-25.
 */

public class CafeData implements Serializable{

    private int cafeNum;   //카페번호
    private int image;      //이미지
    private int openTime;   //오픈시간
    private int closeTime;  //마감시간
    private String location;  //지역
    private String address;  //주소
    private String name;     //카페이름
    private String tel;      //전화 번호
    private float avg;        //평점
    private String category;

    //이미지  이름     전화번호     주소      지역   오픈시간    마감시간    평점    카페번호
    public CafeData(int image, String name, String tel, String address, String location, int openTime, int closeTime, int cafeNum, String category){
        this.image = image;
        this.name = name;
        this.tel = tel;
        this.address=address;
        this.location=location;
        this.openTime=openTime;
        this.closeTime=closeTime;
        this.cafeNum = cafeNum;
        this.category = category;

        String get = CommonFunction.dbManager.getByQuery("AVG(SCORE)", "REVIEW", "CAFE_ID=" + Integer.toString(cafeNum));
        float score = 0;
        try{
            JSONArray jarray = new JSONArray(get);
            for(int i=0; i < jarray.length(); i++)
            {
                JSONObject jObject = jarray.getJSONObject(i);
                score = (float)jObject.getDouble("avg");
            }

            score = (float)(Math.round(score- 0.5)) + (float)0.5;
            avg = score;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void changeData(String name, String tel, String address, String location, int openTime, int closeTime, String category){
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.location = location;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.category = category;
    }

    public int getCafeNum() {
        return cafeNum;
    }

    public int getImage() {
        return image;
    }

    public int getOpenTime() {
        return openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public float getAvg() {
        return avg;
    }

    public String getCategory() {
        return category;
    }
}

