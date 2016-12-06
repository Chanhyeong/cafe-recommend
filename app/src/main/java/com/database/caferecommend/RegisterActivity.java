package com.database.caferecommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by Administrator on 2016-12-01.
 */

/*
 *  후기 등록 Activity
 */
public class RegisterActivity extends AppCompatActivity {
    private RatingBar reviewRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_review);

        // Database에 각 카페마다 후기를 등록해야하므로 이전 Activity들에서 카페 번호인 cafeNum을 받아옴
        Intent intent = getIntent();
        final int cafeNum = (int) intent.getExtras().getInt("cafeNum");

        // 별점을 등록할 RatingBar
        reviewRatingBar = (RatingBar)findViewById(R.id.reviewRatingBar);

        // 후기 등록 버튼을 누를 시
        findViewById(R.id.regRev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 후기 내용을 적을 EditText
                EditText editText=(EditText) findViewById(R.id.revEdit);
                double reviewRating = (double)reviewRatingBar.getRating();
                String review = editText.getText().toString();

                // Database에 별점과 내용을 후기(REVIEW) 테이블에 저장하는 코드
                String[] values = { Double.toString(reviewRating), review, Integer.toString(cafeNum)};
                String query = "REVIEW (SCORE, REVIEW_TEXT, CAFE_ID) " + CommonFunction.dbManager.convertString(values);
                CommonFunction.dbManager.insert(query);

                // 이전 Activity로 돌아가는 코드
                Intent intent=new Intent(getApplicationContext(),Review.class);
                intent.putExtra("cafeNum", cafeNum);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }
}