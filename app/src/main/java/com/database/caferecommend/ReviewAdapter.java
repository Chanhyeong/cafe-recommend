package com.database.caferecommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-12-04.
 */

public class ReviewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ReviewData> reviewData;
    private LayoutInflater inflater;

    public ReviewAdapter(Context c, ArrayList<ReviewData> reviewData) {
        this.context = c;
        this.reviewData = reviewData;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return reviewData.size();
    }

    public Object getItem(int position) {
        return reviewData.get(position).getReviewText();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_reviewlist, parent, false);
        }


        TextView reviewText = (TextView)convertView.findViewById(R.id.reviewText);
        reviewText.setText(reviewData.get(position).getReviewText());

        RatingBar reviewRating= (RatingBar) convertView.findViewById(R.id.revRating);
        reviewRating.setRating(reviewData.get(position).getScore().floatValue());

        return convertView;
    }

}
