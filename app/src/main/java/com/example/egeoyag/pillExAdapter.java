package com.example.egeoyag;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class pillExAdapter extends BaseAdapter {
    private ArrayList<Bitmap_pillExDTO> spe_l = new ArrayList<>();
    private Context context;

    @Override
    public int getCount() {
        return spe_l.size();
    }

    @Override
    public Object getItem(int position) {
        return spe_l.get(position);
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
            convertView = inflater.inflate(R.layout.spe_l, parent, false);
        }

            TextView spe_p_1 = convertView.findViewById(R.id.spe_p_1);
            TextView spe_p_2 = convertView.findViewById(R.id.spe_p_2);
            TextView spe_p_3 = convertView.findViewById(R.id.spe_p_3);
            TextView spe_p_4 = convertView.findViewById(R.id.spe_p_4);
            TextView spe_p_5 = convertView.findViewById(R.id.spe_p_5);
            TextView spe_p_6 = convertView.findViewById(R.id.spe_p_6);
            TextView spe_p_7 = convertView.findViewById(R.id.spe_p_7);
            ImageView spe_p_img = convertView.findViewById(R.id.spe_p_img);
            ImageButton spe_btn_record = convertView.findViewById(R.id.spe_btn_record);
            ImageButton spe_btn_danger = convertView.findViewById(R.id.spe_btn_danger);


            Bitmap_pillExDTO item = spe_l.get(position);

            spe_p_1.setText(item.getDto().getSpe_p_1());
            spe_p_2.setText(item.getDto().getSpe_p_2());
            spe_p_3.setText(item.getDto().getSpe_p_3());
            spe_p_4.setText(item.getDto().getSpe_p_4());
            spe_p_5.setText(item.getDto().getSpe_p_5());
            spe_p_6.setText(item.getDto().getSpe_p_6());
            spe_p_7.setText(item.getDto().getSpe_p_7());
            spe_p_img.setImageBitmap(item.getBitmap());



        spe_btn_danger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, pill_danger.class);

                    //value는 어떻게 넣으면 되는지 할 수 있죠? 네네네네네네 감사합니당
                    //리스트뷰에 넣었으면 어댑터에서 이벤트를 걸어줘야 먹혀용 ㄱ마사합니다ㅜㅜㅜㅜ

                    intent.putExtra("intent_p_name", spe_p_1.getText().toString());
                    Log.d("버튼 넘어가기", spe_p_1.getText().toString());

                    context.startActivity(intent);
                }
            });

        spe_btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, user_record.class);
                intent.putExtra("intent_p_name", spe_p_1.getText().toString());

                context.startActivity(intent);
                }
             });

            return convertView;


        }
        public void addItem (Bitmap_pillExDTO gg){
            Bitmap_pillExDTO pdto = gg;
            spe_l.add(pdto);

        }


    }


