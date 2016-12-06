package com.database.caferecommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-11-30.
 */

// 사용자에게 다른 카페이용자가  카페에  남긴 후기 정보를 보여준다.

public class Review extends AppCompatActivity {
    ArrayList<ReviewData> revList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_activity);

        Intent intent = getIntent();
        final int cafeNum = (int) intent.getExtras().getInt("cafeNum");
        //기본키인 카페번호를 통해 각 카페의 리뷰데이터를 세팅한다.
        setReview(cafeNum);
        ListView listview=(ListView)findViewById(R.id.revList);
        ReviewAdapter reviewAdapter=new ReviewAdapter(this,revList);
        listview.setAdapter(reviewAdapter);

        findViewById(R.id.revWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //리뷰를 등록하고 싶다면 이 버튼을 눌러 리뷰를 남길 수 있다.
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                intent.putExtra("cafeNum", cafeNum);
                startActivity(intent);
            }
        });
    }
    private void setReview(int cafeNum){
        String get = CommonFunction.dbManager.getByQuery("SCORE, REVIEW_TEXT", "REVIEW", "CAFE_ID="+Integer.toString(cafeNum));
        System.out.println("ㄴㄴ"+get + cafeNum);    // for log.
        revList=new ArrayList<ReviewData>();

        try{
            JSONArray jarray = new JSONArray(get);
            for(int i=0; i < jarray.length(); i++)
            {
                JSONObject jObject = jarray.getJSONObject(i);
                Double score = jObject.getDouble("score");
                String review_text= jObject.getString("review_text");

                ReviewData reviewData=new ReviewData(score,review_text);
                revList.add(i,reviewData);//revList에 데이터를 세팅
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}

