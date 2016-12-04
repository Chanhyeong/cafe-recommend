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
    RatingBar ratingBar1;
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
        ratingBar1=(RatingBar)findViewById(R.id.ratingBar1);

        //카페 넘버 받아오기
        Intent intent = getIntent();
        int cafe_num = (int)intent.getIntExtra("value", 1);
        ArrayList<CafeData> arr=(ArrayList<CafeData>) intent.getSerializableExtra("CafeData");
        for(int i=0; i<arr.size(); i++) {
            if (arr.get(i).getCafe_num() == cafe_num) {
                call.setText(arr.get(i).getTel());

                cafeName = arr.get(i).getName();
                image.setImageResource(arr.get(i).getImage());

                name.setText(cafeName);
                address.setText(arr.get(i).getAddress());
                open.setText(Integer.toString(arr.get(i).getOpen()));
                close.setText(Integer.toString(arr.get(i).getClose()));
                ratingBar1.setRating(2);
                // arr.get(i).getAvg();
                //arr.get(i).getImage();
                break;
            }
        }

        setMenuData();// 메뉴 정보를 setting!
        ListView menu= (ListView)findViewById(R.id.menuList);
        MenuAdapter menuAdapter=new MenuAdapter(SubActivity.this,menuList);
        menu.setAdapter(menuAdapter);

        findViewById(R.id.revBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Review.class);
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
                    if (MainActivity.imageNumber.get(image) != null)
                        menuList.add(new MenuData(MainActivity.imageNumber.get(image), name, price));
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
