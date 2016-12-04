package com.database.caferecommend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.R.attr.dial;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.N;
import static java.sql.DriverManager.println;

/*
Select
 - select * from 테이블명;
    테이블의 모든 속성과 값을 가져온다.

 - select 속성, 속성... from 테이블명;
    테이블에 선택한 속성의 값만 가져온다.

Insert
 - insert into 테이블명 values(속성, 속성...);
   테이블에 입력한 속성값으로 추가 한다. (속성의 순서가 일치해야 함)

Delete
 - delete from 테이블명 where 조건, 조건...;
   테이블에 조건이 일치하는 값을 삭제한다.

Update
 - update 테이블명 set 값, 값... where 조건, 조건...;
   테이블에 조건에 일치하는 속성의 값을 입력한 값으로 변경한다.

쿼리문에 조건은 콤마(,) 사용 시 여러 조건을 가질 수 있습니다.


 */
public class MainActivity extends AppCompatActivity {
    ArrayList<CafeData>arrData;
    MyAdapter myadapter;
    ListView list;
    DBManager dbManager;
    Button plus;
    EditText texxxt;
    Button search;
    int whatSpin;   //  0 = 이름, 1 = 지역.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager=new DBManager(getApplicationContext(),"cafe",null,1);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                whatSpin = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });

        texxxt = (EditText) findViewById(R.id.texxxt);

        /*
        검색 버튼.
        검색 버튼을 눌렀을 경우, 해당하는 것들을 가져오도록 하는 문.
         */
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //whatSpin 0- 이름. 1-지역
                String string = texxxt.getText().toString();
            }
        });

        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.custom_alert_layout, null);

         //여기에 dialog에 들어갈 애들 추가
                //TextView customTitle = (TextView)view.findViewById(R.id.customtitle);
                //customTitle.setText("종료하시겠습니까?");
                //customTitle.setTextColor(Color.BLACK);
                ImageView customIcon = (ImageView)view.findViewById(R.id.customdialogicon);

                final EditText dialog_name = (EditText)view.findViewById(R.id.dialog_name);
                final EditText dialog_number = (EditText)view.findViewById(R.id.dialog_number);
                final EditText dialog_open = (EditText)view.findViewById(R.id.dialog_open);
                final EditText dialog_close = (EditText)view.findViewById(R.id.dialog_close);
                final EditText dialog_loc = (EditText)view.findViewById(R.id.dialog_location);
                final EditText dialog_addr = (EditText)view.findViewById(R.id.dialog_address);
                final EditText dialog_char = (EditText)view.findViewById(R.id.dialog_char);

                dialog_name.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                dialog_number.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                dialog_open.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                dialog_close.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                dialog_loc.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                dialog_addr.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                dialog_char.setImeOptions(EditorInfo.IME_ACTION_NEXT);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(view);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String str_name = dialog_name.getText().toString();
                        String str_number = dialog_number.getText().toString();
                        int str_open = Integer.parseInt(dialog_open.getText().toString());
                        int str_close = Integer.parseInt(dialog_close.getText().toString());
                        String str_loc = dialog_loc.getText().toString();
                        String str_addr = dialog_addr.getText().toString();
                        String str_char = dialog_char.getText().toString();

                        // 추가하는 문장
                        // 옵션 - && str_number != null && str_loc != null && str_addr != null && str_char != null
                        if(str_name != null) // 카페이름을 입력하지 않으면, 추가되지 않도록
                        {
                            dbManager.InsertData(str_name, str_number, str_open, str_close, str_loc, str_addr, str_char);
                            Log.d("mks...", str_name + str_number);
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

        setData();
        list=(ListView)findViewById(R.id.list);
        myadapter=new MyAdapter(this,arrData);
        list.setAdapter(myadapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int push = arrData.get(position).getCafe_num();
                Intent intent=new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("CafeData",arrData);
                intent.putExtra("value", push);
                startActivity(intent);

            }
        });
    }

    private void setData(){
        String get = dbManager.PrintData("cafe");
        //System.out.println(get);    // for log.

        arrData=new ArrayList<CafeData>();
     // 코드 확인용 예제문
     // arrData.add(new CafeData(R.mipmap.ic_launcher,"엔젤리너스","010-1111-2222",0));

        Log.d("mk", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

        try{
            JSONArray jarray = new JSONArray(get);
            for(int i=0; i < jarray.length(); i++)
            {
                JSONObject jObject = jarray.getJSONObject(i);
                int cafe_num = jObject.getInt("number");
                String name = jObject.getString("name");
                String phone = jObject.getString("phone");
                int open = jObject.getInt("open");
                int close = jObject.getInt("close");
                String address=jObject.getString("address");

                Log.d("mk",i + ": " + name + phone);
                //이미지  이름     전화번호     주소      오픈시간    마감시간    평균    카페번호
                arrData.add(new CafeData(R.mipmap.ic_launcher,name,phone,address,open,close,0,cafe_num));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
