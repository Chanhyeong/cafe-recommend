package com.database.caferecommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-12-04.
 */

/*
activity_sub안에 들어갈 메뉴리스트를 나타내기 위한 adapter이다.
view의 구성은   메뉴 이미지   ,   메뉴의 이름,    메뉴의 가격이다.
 */
public class MenuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MenuData> menu;
    private LayoutInflater inflater;

    public MenuAdapter(Context c, ArrayList<MenuData> menu) {
        this.context = c;
        this.menu = menu;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return menu.size();
    }

    public Object getItem(int position) {
        return menu.get(position).getName();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.activity_menulist, parent, false);
        }
        //각 뷰의 id와 matching하여 view를 setting한다.
        ImageView image = (ImageView)convertView.findViewById(R.id.menuImage);
        image.setImageResource(menu.get(position).getImage());

        TextView name = (TextView)convertView.findViewById(R.id.menuName);
        name.setText(menu.get(position).getName());

        TextView price= (TextView)convertView.findViewById(R.id.menuPrice);
        price.setText(menu.get(position).getPrice());

        return convertView;
    }

}
