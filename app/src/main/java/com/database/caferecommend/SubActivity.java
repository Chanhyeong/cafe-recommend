package com.database.caferecommend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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

        call = (TextView) findViewById(R.id.call);
        name = (TextView) findViewById(R.id.cafeName);
        address = (TextView) findViewById(R.id.address);
        open = (TextView) findViewById(R.id.open);
        close = (TextView) findViewById(R.id.close);
        image = (ImageView) findViewById(R.id.cafeImage);
        cafeRatingBar = (RatingBar) findViewById(R.id.ratingBar1);

        //카페 넘버 받아오기
        Intent intent = getIntent();
        int cafe_num = (int) intent.getIntExtra("value", 1);
        ArrayList<CafeData> arr = (ArrayList<CafeData>) intent.getSerializableExtra("CafeData");
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getCafeNum() == cafe_num) {
                call.setText(arr.get(i).getTel());

                cafeName = arr.get(i).getName();
                image.setImageResource(arr.get(i).getImage());

                name.setText(cafeName);
                address.setText(arr.get(i).getAddress());
                open.setText(Integer.toString(arr.get(i).getOpen()));
                close.setText(Integer.toString(arr.get(i).getClose()));
                cafeRatingBar.setRating(2);
                // arr.get(i).getAvg();
                //arr.get(i).getImage();
                break;
            }
        }

        call=(TextView)findViewById(R.id.call);
        name=(TextView)findViewById(R.id.cafeName);
        address=(TextView)findViewById(R.id.address);
        open=(TextView)findViewById(R.id.open);
        close=(TextView)findViewById(R.id.close);
        image=(ImageView)findViewById(R.id.cafeImage);
        cafeRatingBar=(RatingBar)findViewById(R.id.cafeRatingBar);

        //카페 넘버 받아오기
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
        ListView menu = (ListView) findViewById(R.id.menuList);
        MenuAdapter menuAdapter = new MenuAdapter(SubActivity.this, menuList);
        menu.setAdapter(menuAdapter);

        findViewById(R.id.revBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Review.class);
                intent.putExtra("cafeNum", cafeData.getCafeNum());
                startActivity(intent);
            }
        });



        findViewById(R.id.appendMenu).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.append_menu, null);

                //여기에 dialog에 들어갈 애들 추가
                final RadioButton select1 = (RadioButton) findViewById(R.id.select1);
                final RadioButton select2 = (RadioButton) findViewById(R.id.select2);
                final RadioButton select3 = (RadioButton) findViewById(R.id.select3);
                final EditText addMenu = (EditText) view.findViewById(R.id.addMenu);
                final EditText addPrice = (EditText) view.findViewById(R.id.addPrice);

                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                builder.setView(view);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String str_menu = addMenu.getText().toString();
                        String str_price = addPrice.getText().toString();


                        if (select1.isSelected()) {
                        } else if (select2.isSelected()) {
                        } else if (select3.isSelected()) {
                        }
                        // 추가하는 문장
                        if (str_menu != null && str_price != null) // 카페메뉴를 입력하지 않으면, 추가되지 않도록
                        {
                            String[] values = {str_menu, str_price};
                            //String query = "MENU (NAME,PRICE)" + db.convertString(values);
                            //db.update(query);
                            Log.d("mks...", str_menu + str_price);
                            //다시 업로드 하도록 하는 코드 필요!!!!
                            //setData();
                            //listMake();
                        }
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });



        findViewById(R.id.modificationCafe).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.modification_cafe, null);

                //여기에 dialog에 들어갈 애들 추가
                //customTitle.setTextColor(Color.BLACK);
                final EditText mName = (EditText)view.findViewById(R.id.mName);
                final EditText mPhone = (EditText)view.findViewById(R.id.mPhone);
                final EditText mOpen = (EditText)view.findViewById(R.id.mOpen);
                final EditText mClose = (EditText)view.findViewById(R.id.mClose);
                final EditText mlocation = (EditText)view.findViewById(R.id.mLocation);
                final EditText mAddress = (EditText)view.findViewById(R.id.mAddress);
                final EditText mChar = (EditText)view.findViewById(R.id.mChar);

                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                builder.setView(view);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String str_name = mName.getText().toString();
                        String str_phone = mPhone.getText().toString();
                        int str_open = Integer.parseInt(mOpen.getText().toString());
                        int str_close = Integer.parseInt(mClose.getText().toString());
                        String str_loc = mlocation.getText().toString();
                        String str_addr = mAddress.getText().toString();
                        String str_char = mChar.getText().toString();

                        // 추가하는 문장
                        // 옵션 - && str_number != null && str_loc != null && str_addr != null && str_char != null
                        if(str_name != null) // 카페이름을 입력하지 않으면, 추가되지 않도록
                        {
                            String[] values = {str_name, str_phone, Integer.toString(str_open), Integer.toString(str_close), str_loc, str_addr, str_char};
                            //String query = "CAFE (NAME,PHONE,OPEN_TIME,END_TIME,LOCATE,DETAIL_LOCATE,CATEGORY)" + db.convertString(values);

                            //db.update(query);
                            Log.d("mks...", str_name + str_phone);
                            //다시 업로드 하도록 하는 코드 필요!!!!
                            //setData();
                            //listMake();
                        }
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });



        findViewById(R.id.deleteCafe).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.delete_cafe, null);

                //여기에 dialog에 들어갈 애들 추가
                //customTitle.setTextColor(Color.BLACK);
                final EditText password = (EditText)view.findViewById(R.id.password);

                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                builder.setView(view);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String str_password = password.getText().toString();

                        // 추가하는 문장
                        // 옵션 - && str_number != null && str_loc != null && str_addr != null && str_char != null
                        if(str_password.equals("admin")) // 카페이름을 입력하지 않으면, 추가되지 않도록
                        {
                            //String query = "CAFE (NAME,PHONE,OPEN_TIME,END_TIME,LOCATE,DETAIL_LOCATE,CATEGORY)" + db.convertString(values);
                            //db.delete(query);
                            //다시 업로드 하도록 하는 코드 필요!!!!
                            //setData();
                            //listMake();
                        }
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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
