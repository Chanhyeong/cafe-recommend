package com.database.caferecommend;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by chanhyeong on 2016-12-05.
 */

/*
 *  모든 객체에서 공통으로 참조하는 데이터들을 가지고 있는 Class
 *  MainActivity에서 단 하나 생셩되고 프로그램 확장 시 추가 생성은 없다고 가정하며 (코드 상에서는 단 하나만 생성), Singleton 개념은 미구현
 */

public class CommonFunction {
    public static HashMap<String, Integer> imageNumber = new HashMap<String, Integer>();
    public static DBManager dbManager;

    private Class c = R.drawable.class;
    private Field[] f = c.getFields();

    public CommonFunction(DBManager dbManager) {
        /*-- drawable 폴더의 이미지를 Filename과 int의 HashMap형태로 저장하여 reference를 쉽게함 --*/
        for (Field d : f) {
            try {
                if(d.get("R.drawable." + d.getName()) != null) {
                    imageNumber.put(d.getName(), Integer.valueOf(d.get("R.drawable." + d.getName()).toString()));
                    System.out.println(Integer.valueOf(d.get("R.drawable." + d.getName()).toString()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // MainActivity에서 생성된 객체를 할당
        this.dbManager = dbManager;
    }
}
