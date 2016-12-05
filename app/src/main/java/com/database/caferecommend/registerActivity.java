package com.database.caferecommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by Administrator on 2016-12-01.
 */

public class RegisterActivity extends AppCompatActivity {
    private RatingBar reviewRatingBar;
    private Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_review);
        final int cafeNum = (int)intent.getSerializableExtra("cafeNum");

        reviewRatingBar = (RatingBar)findViewById(R.id.reviewRatingBar);
        findViewById(R.id.regRev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText=(EditText) findViewById(R.id.revEdit);
                double reviewRating = (double)reviewRatingBar.getRating();
                String review = editText.getText().toString();

                String[] values = { Double.toString(reviewRating), review, Integer.toString(cafeNum)};
                String query = "REVIEW (SCORE, REVIEW_TEXT, CAFE_ID) " + CommonFunction.dbManager.convertString(values);
                CommonFunction.dbManager.insert(query);
            }
        });
    }
}