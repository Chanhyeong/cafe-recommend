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

        ImageView image = (ImageView)convertView.findViewById(R.id.menuImage);
        image.setImageResource(menu.get(position).getImage());

        TextView name = (TextView)convertView.findViewById(R.id.menuName);
        name.setText(menu.get(position).getName());

        TextView price= (TextView)convertView.findViewById(R.id.menuPrice);
        price.setText(menu.get(position).getPrice());

        return convertView;
    }

}
