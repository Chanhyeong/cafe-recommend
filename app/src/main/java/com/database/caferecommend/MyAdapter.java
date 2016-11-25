package com.database.caferecommend;

import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2016-11-25.
 */



import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<cafeData> arrData;
    private LayoutInflater inflater;

    public MyAdapter(Context c, ArrayList<cafeData> arr) {
        this.context = c;
        this.arrData = arr;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrData.size();
    }

    public Object getItem(int position) {
        return arrData.get(position).getName();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_layout, parent, false);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.image);
        image.setImageResource(arrData.get(position).getImage());

        TextView name = (TextView)convertView.findViewById(R.id.name);
        name.setText(arrData.get(position).getName());

        TextView tel = (TextView)convertView.findViewById(R.id.tel);
        tel.setText(arrData.get(position).getTel());

        ImageView avg = (ImageView) convertView.findViewById(R.id.avg);
        avg.setImageResource(arrData.get(position).getAvg());

        return convertView;
    }
}



