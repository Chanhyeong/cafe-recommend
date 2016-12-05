package com.database.caferecommend;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by chanhyeong on 2016-12-05.
 */

public class CommonFunction {
    public static HashMap<String, Integer> imageNumber = new HashMap<String, Integer>();
    public static DBManager dbManager;

    private Class c = R.drawable.class;
    private Field[] f = c.getFields();

    public CommonFunction(DBManager dbManager) {
        /*-- Drawable 폴더의 이미지를 Filename과 int의 HashMap형태로 저장하여 reference를 쉽게함 --*/
        for (Field d : f) {
            try {
                if(d.get("R.drawable." + d.getName()) != null) {
                    imageNumber.put(d.getName(), Integer.valueOf(d.get("R.drawable." + d.getName()).toString()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.dbManager = dbManager;
    }
}
