package com.database.caferecommend;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016-11-25.
 */
public class DBManager extends SQLiteOpenHelper {
    private final String[][] cafeData = {
            {"할리스커피", "031-211-2884", "8", "23", "경기", "경기 수원시 영통구 중부대로 258", "일반"},
            {"커피볶는수", "010-6460-0602", "9", "22", "경기", "경기 수원시 팔달구 우만동 62-1", "일반"},
            {"탐앤탐스", "031-241-2341", "10", "23", "경기", "경기도 수원시 영통구 원천동 18-1 아록빌딩 1층", "일반"},
            {"스타벅스", "031-215-4516", "10", "22", "경기", "경기도 수원시 팔달구 우만동 58-32 오스타스토리빌딩 1층", "일반"},
            {"봄봄", "031-123-3214", "9", "22", "경기", "경기 수원시 팔달구 우만동 30-1", "일반"},
            {"커피만", "031-456-7890", "11", "23", "경기", "경기 수원시 팔달구 우만동 36-6", "일반"},
            {"나인어클락", "031-123-2345", "12", "24", "경기", "경기 수원시 팔달구 우만동 571-3 2층", "일반"},
            {"커피나무", "031-211-2342", "11", "24", "경기", "경기도 수원시 팔달구 우만동 577-1", "일반"},
            {"스타벅스", "02-542-1268", "8", "24", "서울", "서울특별시 강남구 신사동 580-2 관영빌딩 1층", "일반"},
            {"스타벅스", "033-231-5235", "8", "24", "강원", "강원도 춘천시 효자동 632-3", "일반"},
            {"스타벅스", "032-789-1235", "8", "24", "인천", "인천광역시 부평구 부평동 159-25", "일반"},
            {"탐앤탐스", "061-877-2310", "9", "23", "전남", "전라남도 목포시 대안동 12-1", "일반"},
            {"할리스커피", "062-665-0938", "11", "24", "광주", "광주광역시 동구 지산동 518-18", "일반"},
            {"카페베네", "064-555-0931", "7", "23", "제주", "제주특별자치도 제주시 한림읍 협재리 2447-46", "일반"},
            {"카페베네", "055-674-2314", "7", "23", "경남", "경상남도 사천시 벌리동 253-8", "일반"},
            {"카페베네", "063-443-7351", "7", "23", "전북", "전라북도 군산시 중앙로2가 12-4", "일반"},
            {"엔젤리너스", "042-423-5246", "8", "22", "대전", "전북도 군산시 수송로 213", "일반"},
            {"엔젤리너스", "041-342-4325", "9", "22", "충남", "충청남도 아산시 배방읍 장재리 13-1", "일반"},
            {"이디야커피", "053-734-4415", "9", "24", "대구", "대구광역시 중구 중앙대로 400-1", "일반"}
    };

    private final String[] brandData = {
        "탐앤탐스", "카페베네", "이디야커피", "스타벅스", "할리스", "엔젤리너스", "봄봄", "빽다방",  "쥬씨",
                "커피나무", "나인어클락", "커피만", "봄봄", "커피볶는수"
    };

    private final String[][] menuData = {
            {"아메리카노", "9900", "스타벅스"}, {"카푸치노", "9900", "스타벅스"},
            {"카페모카", "9900", "스타벅스"},  {"프라푸치노", "9900", "스타벅스"},
            {"블루베리베이글", "9900", "스타벅스"},
            {"아메리카노", "6000", "빽다방"}, {"카푸치노", "6000", "빽다방"},
            {"카페모카", "6000", "빽다방"}, {"프라푸치노", "6000", "빽다방"},
            {"치즈베이글", "6000", "빽다방"},
            {"아메리카노", "5500", "이디야커피"}, {"카푸치노", "5500", "이디야커피"},
            {"플레인베이글", "5500", "이디야커피"}, {"프라푸치노", "5500", "이디야커피"},
            {"카페모카", "5500", "이디야커피"},
            {"아메리카노", "5000", "탐앤탐스"},  {"카페모카", "5000", "탐앤탐스"},
            {"프라푸치노", "5000", "탐앤탐스"}, {"블루베리베이글", "5000", "탐앤탐스"},
            {"카푸치노", "5000", "탐앤탐스"},
            {"아메리카노", "5000", "엔젤리너스"}, {"카푸치노", "5000", "엔젤리너스"},
            {"카페모카", "5000", "엔젤리너스"}, {"프라푸치노", "5000", "엔젤리너스"},
            {"치즈베이글", "5000", "엔젤리너스"},
            {"아메리카노", "5000", "봄봄"},  {"카페모카", "5000", "봄봄"},
            {"프라푸치노", "5000", "봄봄"},  {"플레인베이글", "5000", "봄봄"},
            {"카푸치노", "5000", "봄봄"},
            {"아메리카노", "6000", "카페베네"}, {"카페모카", "6000", "카페베네"},
            {"프라푸치노", "6000", "카페베네"}, {"카푸치노", "6000", "카페베네"},
            {"크림베이글", "6000", "카페베네"},
            {"아메리카노", "3200", "나인어클락"}, {"코코아", "3500", "나인어클락"},
            {"카페모카", "3500", "나인어클락"}, {"프라푸치노", "4000", "나인어클락"},
            {"카푸치노", "4000", "나인어클락"}, {"크림베이글", "1200", "나인어클락"},
            {"아메리카노", "5000", "할리스커피"}, {"카페모카", "5500", "할리스커피"},
            {"프라푸치노", "5700", "할리스커피"}, {"블루베리베이글", "3500", "할리스커피"},
            {"카푸치노", "5500", "할리스커피"},
            {"아메리카노", "2500", "커피나무"}, {"카푸치노", "2800", "커피나무"},
            {"플레인베이글", "1900", "커피나무"}, {"프라푸치노", "3000", "커피나무"},
            {"카페모카", "2900", "커피나무"},
            {"아메리카노", "3000", "커피볶는수"}, {"카푸치노", "3500", "커피볶는수"},
            {"카페모카", "3700", "커피볶는수"}, {"프라푸치노", "4000", "커피볶는수"},
            {"블루베리베이글", "2800", "커피볶는수"},
            {"아메리카노", "900", "커피만"}, {"카푸치노", "1500", "커피만"},
            {"카페모카", "1600", "커피만"}, {"프라푸치노", "1800", "커피만"}
    };

    public DBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public String convertString(String[] input){
        String output = "values (";

        for(String s: input){
            try{
                Double.parseDouble(s);
                output += ", " + s;
            }catch (Exception e) {
                output += ", '" + s + "'";
            }
            if(!s.equals(input[input.length - 1]))
                output += ", ";
        }
        output += ")";

        System.out.println(output);

        return output;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CAFE(CAFE_ID integer primary key autoincrement, NAME text not null, PHONE text, OPEN_TIME integer, END_TIME integer, LOCATE text not null, DETAIL_LOCATE text not null, CATEGORY text not null)");
        db.execSQL("CREATE TABLE IF NOT EXISTS FRANCHISE(CAFE_NAME text not null, BRAND_IMAGE blob)");
        db.execSQL("CREATE TABLE IF NOT EXISTS MENU(MENU_ID integer primary key autoincrement, MENU_NAME text not null, PRICE integer, IMAGE blob, CAFE_NAME text not null," +
                "foreign key (CAFE_NAME) references FRANCHISE(CAFE_NAME) on delete SET NULL on update CASCADE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS PICTURE(IMAGE_ID integer primary key autoincrement, IMAGE_ADDR text not null, CAFE_ID integer not null, " +
                "foreign key (CAFE_ID) references CAFE(CAFE_ID) on delete SET NULL on update CASCADE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS EVENT(EVENT_ID integer primary key autoincrement, CAFE_NAME text not null, EVENT_DETAIL text not null, " +
                "foreign key (CAFE_NAME) references FRANCHISE(CAFE_NAME) on delete SET NULL on update CASCADE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS REVIEW(REVIEW_ID integer primary key autoincrement, SCORE integer, CAFE_ID integer, REVIEW_TEXT text not null," +
                "foreign key (CAFE_ID) references CAFE(CAFE_ID) on delete SET NULL on update CASCADE)");

        for(String[] s: cafeData){
            String query = "CAFE (NAME, PHONE, OPEN_TIME, END_TIME, LOCATE, DETAIL_LOCATE, CATEGORY) " + convertString(s);
            insert(query);
        }
        for(String s: brandData){
            String query = "FRANCHISE (CAFE_NAME) " + "value (" + s + ")";
            insert(query);
        }
        for(String[] s: menuData){
            String query = "CAFE () " + convertString(s);
            insert(query);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        //insert into 테이블명 values(속성, 속성)
        db.execSQL("insert into " + _query);

        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public String PrintData(String input) {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        //select * from 테이블명;
        //select 속성,속성...from 테이블명;
        Cursor cursor = db.rawQuery("select * from "+ input, null);
        while (cursor.moveToNext()) {
            str="출력";
        }
        return str;
    }
}