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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {
    ArrayList<MenuData> menuList; //menu 정보 받음
    TextView call;
    TextView name;
    TextView address;
    TextView open;
    TextView close;
    ImageView image;
    int number;
    RatingBar cafeRatingBar;
    String cafeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //Main Activity로부터 카페정보 받아옴
        //Get cafe information from MainActivity
        Intent intent = getIntent();

        // TextView 추가
        // Add TextView
        call=(TextView)findViewById(R.id.call);
        name=(TextView)findViewById(R.id.cafeName);
        address=(TextView)findViewById(R.id.address);
        open=(TextView)findViewById(R.id.open);
        close=(TextView)findViewById(R.id.close);
        image=(ImageView)findViewById(R.id.cafeImage);
        cafeRatingBar=(RatingBar)findViewById(R.id.cafeRatingBar);

        final CafeData cafeData = (CafeData)intent.getSerializableExtra("CafeData");
        number = cafeData.getCafeNum();
        call.setText(cafeData.getTel());

        image.setImageResource(cafeData.getImage());

        cafeName = cafeData.getName();

        // TextView의 값 설정
        // Set value to each TextView
        name.setText(cafeData.getName());
        address.setText(cafeData.getAddress());
        open.setText(Integer.toString(cafeData.getOpenTime()));
        close.setText(Integer.toString(cafeData.getCloseTime()));
        System.out.println(cafeData.getAvg());
        cafeRatingBar.setRating(cafeData.getAvg());
        setMenuData();// 메뉴 정보를 setting!
        ListMenu();

        // Review로 넘어가는 버튼
        // Button that performs switching to Review
        findViewById(R.id.revBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Review.class);
                // 후기를 등록할 때 사용할 카페 번호를 넘겨 줌
                // Put cafeNum which is used when users write review about cafe
                intent.putExtra("cafeNum", cafeData.getCafeNum());
                startActivity(intent);
            }
        });

        // 카페 별 Menu를 추가하는 버튼
        // Button that performs appending menu of cafe
        findViewById(R.id.appendMenu).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.append_menu, null);

                final String str_img[] = {"stra_a","angel_ame","hollys_cino"};

                // EditText 추가
                // Add Edit Text
                final EditText addMenu = (EditText) view.findViewById(R.id.addMenu);
                final EditText addPrice = (EditText) view.findViewById(R.id.addPrice);
                final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                builder.setView(view);

                // 사용자가 정보 입력 후 '네'버튼을 누를 경우
                // If users touch 'yes' after they entered information
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String str_menu = addMenu.getText().toString();
                        String str_price = addPrice.getText().toString();

                        int radioButtonID = radioGroup.getCheckedRadioButtonId();
                        View radioButton = radioGroup.findViewById(radioButtonID);
                        int idx = radioGroup.indexOfChild(radioButton);

                        // 입력한 정보를 디비에 추가
                        // Insert new information into database
                        if (str_menu != null && str_price != null) // 카페메뉴를 입력하지 않으면, 추가되지 않도록
                        {
                            String[] values = {str_menu, str_price,name.getText().toString(),str_img[idx]};
                            String query = "MENU (MENU_NAME,PRICE,CAFE_NAME,IMAGE)" + CommonFunction.dbManager.convertString(values);
                            CommonFunction.dbManager.insert(query);

                            // 추가된 정보를 새로 띄워줌
                            // Refresh information that are added.
                            setMenuData();
                            ListMenu();
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

                // EditText 추가
                // Add EditText
                final EditText mName = (EditText)view.findViewById(R.id.mName);
                final EditText mPhone = (EditText)view.findViewById(R.id.mPhone);
                final EditText mOpen = (EditText)view.findViewById(R.id.mOpen);
                final EditText mClose = (EditText)view.findViewById(R.id.mClose);
                final EditText mLocation = (EditText)view.findViewById(R.id.mLocation);
                final EditText mAddress = (EditText)view.findViewById(R.id.mAddress);
                final EditText mChar = (EditText)view.findViewById(R.id.mChar);

                mName.setText(cafeData.getName());
                mPhone.setText(cafeData.getTel());
                mOpen.setText(Integer.toString(cafeData.getOpenTime()));
                mClose.setText(Integer.toString(cafeData.getCloseTime()));
                mLocation.setText(cafeData.getLocation());
                mAddress.setText(cafeData.getAddress());
                mChar.setText(cafeData.getCategory());

                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                builder.setView(view);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str_name = mName.getText().toString();
                        String str_phone = mPhone.getText().toString();
                        int open_ = Integer.parseInt(mOpen.getText().toString());
                        int close_ = Integer.parseInt(mClose.getText().toString());
                        String str_loc = mLocation.getText().toString();
                        String str_addr = mAddress.getText().toString();
                        String str_cartegory = mChar.getText().toString();

                        cafeData.changeData(str_name, str_phone, str_addr, str_loc, open_, close_, str_cartegory);

                        // 카페이름을 입력하지 않으면, 추가되지 않도록 함
                        // If there is not a cafe name, it will not be added.
                        if(str_name != null)
                        {
                            String[] values = {str_name, str_phone, Integer.toString(open_), Integer.toString(close_), str_loc, str_addr, str_cartegory};
                            String query = "UPDATE CAFE SET" + " NAME = '" + str_name + "', PHONE = '" + str_phone + "', DETAIL_LOCATE = '" + str_addr + "', LOCATE = '" + str_loc +
                                    "', CATEGORY = '" + str_cartegory + "', OPEN_TIME = " + Integer.toString(open_) + ", END_TIME = " + Integer.toString(close_) + " WHERE CAFE_ID = " + cafeData.getCafeNum() + ";";


                            CommonFunction.dbManager.update(query);
                            Log.d("mks...", str_name + str_phone);

                            name.setText(str_name);
                            call.setText(str_phone);
                            address.setText(str_addr);
                            open.setText(Integer.toString(open_));
                            close.setText(Integer.toString(close_));

                            // 변경한 내용이 바로 적용되도록 함
                            // Adjust changes instantly
                            Intent edit = new Intent();
                            edit.putExtra("delete", "ok");
                            SubActivity.this.setResult(RESULT_OK, edit);
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

                        // 관리자 비밀번호를 입력하지 않으면 삭제되지 않도록 함
                        // If the user didn't enter the admin password, he cannot delete the cafe information.
                        if(str_password.equals("admin"))
                        {
                            String query = "delete from CAFE where CAFE_ID="+number;
                            CommonFunction.dbManager.delete(query);
                            Intent deleteOut = new Intent();
                            deleteOut.putExtra("delete", "ok");
                            SubActivity.this.setResult(RESULT_OK, deleteOut);
                            finish();
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

        findViewById(R.id.cafePicture).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.activity_picture, null);
                String get = CommonFunction.dbManager.getByQuery("IMAGE_NAME", "PICTURE", "CAFE_ID=" + Integer.toString(cafeData.getCafeNum()));
                System.out.println("get"+get);
                String imageName=null;
                try{
                    JSONArray jarray = new JSONArray(get);
                    for(int i=0; i < jarray.length(); i++)
                    {
                        JSONObject jObject = jarray.getJSONObject(i);
                        imageName = jObject.getString("imageName");
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                ImageView picture = (ImageView)view.findViewById(R.id.pictureImg);

                // 이미지 파일 이름이 데이터베이스에 없으면 기본 이미지 출력
                // If there is not a file name of image, default images will be printed
                if(imageName==null) {
                    picture.setImageResource(R.drawable.sample_in);
                }
                else{
                    System.out.println(CommonFunction.imageNumber.get(imageName));
                    picture.setImageResource(CommonFunction.imageNumber.get(imageName));
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void ListMenu()
    {
        ListView menu = (ListView) findViewById(R.id.menuList);
        MenuAdapter menuAdapter = new MenuAdapter(SubActivity.this, menuList);
        menu.setAdapter(menuAdapter);
    }

    private void setMenuData(){
        String get = CommonFunction.dbManager.PrintData("menu");
        //System.out.println(get);    // for log.

        menuList=new ArrayList<MenuData>();

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
