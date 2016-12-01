package com.database.caferecommend;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView call=(TextView)findViewById(R.id.call);
        TextView address=(TextView)findViewById(R.id.address);
        DBManager db=new DBManager(getApplicationContext(),"cafe",null,1);
        //call.setText();
        //address.setText();


        findViewById(R.id.revBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(getApplicationContext(),Review.class);
               // startActivity(intent);
            }
        });
    }

}
