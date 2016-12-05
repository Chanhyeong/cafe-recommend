package com.database.caferecommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static com.database.caferecommend.R.drawable.angel_a;

public class SubActivity extends AppCompatActivity {
    ArrayList<MenuData> menuList; //menu 정보 받음
    DBManager db=new DBManager(SubActivity.this,"menu",null,1);
    TextView call;
    TextView name;
    TextView address;
    TextView open;
    TextView close;
    ImageView image;
    RatingBar cafeRatingBar;
    String cafeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        call=(TextView)findViewById(R.id.call);
        name=(TextView)findViewById(R.id.cafeName);
        address=(TextView)findViewById(R.id.address);
        open=(TextView)findViewById(R.id.open);
        close=(TextView)findViewById(R.id.close);
        image=(ImageView)findViewById(R.id.cafeImage);
        cafeRatingBar=(RatingBar)findViewById(R.id.cafeRatingBar);

        //카페 넘버 받아오기
        Intent intent = getIntent();
        final CafeData cafeData = (CafeData)intent.getSerializableExtra("CafeData");

        call.setText(cafeData.getTel());

        cafeName = cafeData.getName();
        image.setImageResource(cafeData.getImage());

        name.setText(cafeName);
        address.setText(cafeData.getAddress());
        open.setText(Integer.toString(cafeData.getOpen()));
        close.setText(Integer.toString(cafeData.getClose()));
        cafeRatingBar.setRating(2);
        // cafeData.getAvg();
        //cafeData.getImage();

        setMenuData();// 메뉴 정보를 setting!
        ListView menu= (ListView)findViewById(R.id.menuList);
        MenuAdapter menuAdapter=new MenuAdapter(SubActivity.this,menuList);
        menu.setAdapter(menuAdapter);

        findViewById(R.id.revBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Review.class);
                intent.putExtra("cafeNum", cafeData.getCafeNum());
                startActivity(intent);
            }
        });
    }

    private void setMenuData(){
        String get = db.PrintData("menu");
        //System.out.println(get);    // for log.

        menuList=new ArrayList<MenuData>();
        // 코드 확인용 예제문
        // arrData.add(new CafeData(R.mipmap.ic_launcher,"엔젤리너스","010-1111-2222",0));

        try{
            JSONArray jarray = new JSONArray(get);
            for(int i=0; i < jarray.length(); i++)
            {
                JSONObject jObject = jarray.getJSONObject(i);
                String name = jObject.getString("name");
                String price = jObject.getString("price");
                String image = jObject.getString("image");
                String instantCafeName = jObject.getString("cafename");

                Log.d("mk",i + ": " + name + price);

                if(cafeName.equals(instantCafeName)) {
                    if (CommonFunction.imageNumber.get(image) != null)
                        menuList.add(new MenuData(CommonFunction.imageNumber.get(image), name, price));
                    else
                        menuList.add(new MenuData(R.mipmap.ic_launcher, name, price));
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
