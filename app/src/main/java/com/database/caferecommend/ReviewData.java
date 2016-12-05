package com.database.caferecommend;

/**
 * Created by Administrator on 2016-12-05.
 */

public class ReviewData {
    private double score;  //평점
    private String review_text ; //

    public ReviewData(double score,String review_text){
        this.score= score;
        this.review_text = review_text;
    }
    public Double getScore() {
        return score;
    }
    public String getReviewText() {
        return review_text;
    }
}
