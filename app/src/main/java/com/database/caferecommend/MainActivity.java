package com.database.caferecommend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/*
Select
 - select * from 테이블명;
    테이블의 모든 속성과 값을 가져온다.

 - select 속성, 속성... from 테이블명;
    테이블에 선택한 속성의 값만 가져온다.

Insert
 - insert into 테이블명 values(속성, 속성...);
   테이블에 입력한 속성값으로 추가 한다. (속성의 순서가 일치해야 함)

Delete
 - delete from 테이블명 where 조건, 조건...;
   테이블에 조건이 일치하는 값을 삭제한다.

Update
 - update 테이블명 set 값, 값... where 조건, 조건...;
   테이블에 조건에 일치하는 속성의 값을 입력한 값으로 변경한다.

쿼리문에 조건은 콤마(,) 사용 시 여러 조건을 가질 수 있습니다.


 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager dbManager1=new DBManager(getApplicationContext(),"테이블 이름",null,1);
        DBManager dbManager2=new DBManager(getApplicationContext(),"테이블 이름",null,1);
        DBManager dbManager3=new DBManager(getApplicationContext(),"테이블 이름",null,1);
        DBManager dbManager4=new DBManager(getApplicationContext(),"테이블 이름",null,1);
        DBManager dbManager5=new DBManager(getApplicationContext(),"테이블 이름",null,1);
        DBManager dbManager6=new DBManager(getApplicationContext(),"테이블 이름",null,1);

    }
}
