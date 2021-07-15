package com.example.egeoyag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class mypageadapter extends BaseAdapter {


    private ArrayList<mypagedto> mypagedtolist = new ArrayList<mypagedto>();

    @Override
    public int getCount() {
        return mypagedtolist.size();
    }

    @Override
    public Object getItem(int position) {
        return mypagedtolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_page_list, parent, false);

        }

        ImageView img = convertView.findViewById(R.id.pl_img2);
        TextView tv_name = convertView.findViewById(R.id.tv_name2);
        TextView tv_1 = convertView.findViewById(R.id.tv_2);
        TextView tv_ymd = convertView.findViewById(R.id.tv_ymd2);




        return convertView;
    }




}