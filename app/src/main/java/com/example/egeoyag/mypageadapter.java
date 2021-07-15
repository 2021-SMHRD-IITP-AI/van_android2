package com.example.egeoyag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ContentView;

import java.util.ArrayList;

public class mypageadapter extends BaseAdapter {


    private ArrayList<Bitmap_mypageDTO> mypagedtolist = new ArrayList<>();
    private Context context;

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

        context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_page_list, parent, false);
        }

        ImageView pl_img2 = convertView.findViewById(R.id.pl_img2);
        TextView tv_name2 = convertView.findViewById(R.id.tv_name2);
        TextView tv_2 = convertView.findViewById(R.id.tv_2);
        TextView tv_ymd2 = convertView.findViewById(R.id.tv_ymd2);

        Bitmap_mypageDTO item = mypagedtolist.get(position);

        pl_img2.setImageBitmap(item.getBitmap());
        tv_name2.setText(item.getDto().getName2());
        tv_2.setText(item.getDto().getSymptom2());
        tv_ymd2.setText(item.getDto().getYmd2());

        return convertView;
    }

    public void addItem (Bitmap_mypageDTO gg){
        Bitmap_mypageDTO pdto = gg;
        mypagedtolist.add(pdto);

    }




}