package com.database.caferecommend;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    ArrayList<MenuData> menuList;
    DBManager db=new DBManager(getApplicationContext(),"cafe",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //카페 넘버 받아오기
        Intent intent = getIntent();
        int cafe_num = intent.getIntExtra("value", 1);

        DBManager db=new DBManager(getApplicationContext(),"cafe",null,1);
        //call.setText();
        //address.setText();

        setData();
        ListView menu= (ListView)findViewById(R.id.menuList);
        MenuAdapter menuAdapter=new MenuAdapter(this,menuList);
        menu.setAdapter(menuAdapter);



        findViewById(R.id.revBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(getApplicationContext(),Review.class);
               // startActivity(intent);
            }
        });
    }

    private void setData(){
        TextView call=(TextView)findViewById(R.id.call);
        TextView address=(TextView)findViewById(R.id.address);

        String get = db.PrintData("menu");
        //System.out.println(get);    // for log.

        menuList=new ArrayList<MenuData>();
        // 코드 확인용 예제문
        // arrData.add(new CafeData(R.mipmap.ic_launcher,"엔젤리너스","010-1111-2222",0));

        Log.d("mk", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        try{
            JSONArray jarray = new JSONArray(get);
            for(int i=0; i < jarray.length(); i++)
            {
                JSONObject jObject = jarray.getJSONObject(i);
                String name = jObject.getString("name");
                String price = jObject.getString("price");

                Log.d("mk",i + ": " + name + price);

                menuList.add(new MenuData(R.mipmap.ic_launcher,name,price));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
