package com.database.caferecommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2016-11-30.
 */

public class Review extends AppCompatActivity {
    private Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity);

        findViewById(R.id.revWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cafeNum = (Integer)intent.getIntExtra("cafeNum", 1);
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                intent.putExtra("cafeNum", cafeNum);
                startActivity(intent);
            }
        });
    }
}
