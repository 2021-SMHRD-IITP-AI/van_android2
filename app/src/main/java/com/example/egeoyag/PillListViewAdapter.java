package com.example.egeoyag;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PillListViewAdapter extends BaseAdapter {
    private ArrayList<Bitmap_ListPillDTO> p_list = new ArrayList<>();

    ImageView l_p_img2;


    @Override
    public int getCount() {
        return p_list.size();
    }

    @Override
    public Object getItem(int position) {
        return p_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Context context = parent.getContext();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.p_list, parent, false);
        }

            ImageView l_p_img = convertView.findViewById(R.id.l_p_img);
            TextView l_p_name = convertView.findViewById(R.id.l_p_name);
            TextView l_p_company = convertView.findViewById(R.id.l_p_company);
            TextView l_p_eff1 = convertView.findViewById(R.id.l_p_eff1);
            TextView l_p_num = convertView.findViewById(R.id.l_p_num);



             Bitmap_ListPillDTO item = p_list.get(position);

             l_p_img.setImageBitmap(item.getBitmap());
             l_p_name.setText(item.getDto().getL_p_name());
             l_p_company.setText(item.getDto().getL_p_company());
             l_p_eff1.setText(item.getDto().getL_p_eff1());
             l_p_num.setText(item.getDto().getL_p_num());


            return convertView;

        }

        public void addItem(Bitmap_ListPillDTO gg){
            Bitmap_ListPillDTO pdto= gg;
            p_list.add(pdto);

    }


}


