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

        final int cafeNum = (int)intent.getSerializableExtra("cafeNum");

        findViewById(R.id.revWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                intent.putExtra("cafeNum", cafeNum);
                startActivity(intent);
            }
        });
    }
}
