package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class user_record_modify extends AppCompatActivity {
    //  "https://nedrug.mfds.go.kr/pbp/cmn/itemImageDownload/151317976910600064"
    //     1
    //       199303109
    // 현우님제발그만
    //  나의증사아아앙아
    private String img;
    private String user_id;
    private String dnum;
    private String p_g;
    private String mysy;
    private String data1, data2, dataA, dataB, timeData, dateall, p_img_link;
    private ImageView ur_p_img2;
    private String p_num, p_img, p_name, p_company, p_otcetc, p_group, p_taking_date, p_user_time, p_user_height, p_user_weight, p_user_record, p_user_myalartime,p_my_symptom, p_user_myalartday;

    int minday, data3, data4;

    // 서버테스테스트용
    //params.put("drug_num", p_num);
    //params.put("user_taking_date", dateall);
    //params.put("user_id", p_user_id);
    private String testp_num = "199303109";
    private String testp_date = "2021/07/13~2021/0713";
    private String testuser_id ="1";

    Bitmap bitmap;

    Button btn_send; //이거 진짜 꼭 좀 고치세요. btn가 왜 te죠?
    TextView p_n, p_c, jun, nae,  date1, date2, mytime, daybreak, morning, afternoon, evening, midnight, time, day, length, weight, user_record;

    TextView minus_day;
    int cnt1, cnt2, cnt3, cnt4, cnt5;
    private RequestQueue queue; //요청하는 개체
    private StringRequest stringRequest; // queue가 정보를 통해서...
    private ListView recordListview;
   // private user_record_listAdapter adapter;
    private String path = "1"; // 모디피에서 다시 레코드로 넘어갈때.

    Switch alarm_switch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_record);
        day = findViewById(R.id.day);
        //dnum = findViewById(R.id.dnum);
        p_n = findViewById(R.id.p_n);
        p_c = findViewById(R.id.p_c);
        jun = findViewById(R.id.jun);
        nae = findViewById(R.id.nae);
        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
        mytime = findViewById(R.id.my_time);
        daybreak = findViewById(R.id.daybreak);
        morning = findViewById(R.id.morning);
        afternoon = findViewById(R.id.afternoon);
        evening = findViewById(R.id.evening);
        midnight = findViewById(R.id.midnight);
        time = findViewById(R.id.time);
        day = findViewById(R.id.day);
        length = findViewById(R.id.length);
        weight = findViewById(R.id.weight);
        user_record = findViewById(R.id.user_record);
        ur_p_img2 = findViewById(R.id.ur_p_img2);

        minus_day = findViewById(R.id.minus_day);

        btn_send = findViewById(R.id.btn_send);

        alarm_switch = findViewById(R.id.alarm_switch);



        alarm_switch.setChecked(true);


        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        p_num = intent.getStringExtra("p_num");
        p_img = intent.getStringExtra("p_img");
        p_name = intent.getStringExtra("p_name");
        p_company = intent.getStringExtra("p_company");
        p_otcetc = intent.getStringExtra("p_otcetc");
        p_group = intent.getStringExtra("p_group");
        p_my_symptom = intent.getStringExtra("p_my_symptom");
        p_taking_date = intent.getStringExtra("p_taking_date");
        p_user_time = intent.getStringExtra("p_user_time");
        p_user_height = intent.getStringExtra("p_user_height");
        p_user_weight = intent.getStringExtra("p_user_weight");
        p_user_record = intent.getStringExtra("p_user_record");
        p_user_myalartime = intent.getStringExtra("p_user_myalartime");
        p_user_myalartday = intent.getStringExtra("p_user_myalartday");


        p_img_link = p_img;

        user_record_modify.DownloadFilesTask task = new user_record_modify.DownloadFilesTask();
        task.execute();

        //약번호  p_num
        String str = p_taking_date;              // 예시 2018/11/15~2018//11/30 으로올거임
        String time1 = str.substring(8, str.indexOf("~"));
        String time2 = str.substring(str.indexOf("~")+1);//2018/11/30 짤랐고
        String time3 = time2.substring(7+1);

        Log.d("날짜 확인 타임1 ",time1);
        Log.d("날짜 확인 타임2 ",time2);
        Log.d("날짜 확인 타임3 ",time3);

        int time4 = Integer.parseInt(time3)-Integer.parseInt(time1);
        //Integer.parseInt();



        minus_day.setText(String.valueOf(time4));



        p_n.setText(p_name );
        p_c.setText(p_company);
        jun.setText(p_otcetc);
        nae.setText(p_my_symptom);
        weight.setText(p_user_weight);
        length.setText(p_user_height);
        user_record.setText(p_user_record);
        time.setText(p_user_myalartime);
        date1.setText(time1);
        mytime.setText(time2);
        day.setText(time4);
        p_n.setText(p_name);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),user_record.class);
                intent1.putExtra("path",path);
                intent1.putExtra("p_img",p_img);
                intent1.putExtra("p_num",p_num);
                intent1.putExtra("p_name",p_name);
                intent1.putExtra("p_company",p_company);
                intent1.putExtra("p_otc",p_otcetc);
                intent1.putExtra("p_group",p_group);
                intent1.putExtra("p_symp",p_my_symptom);
                intent1.putExtra("p_weight",p_user_weight);
                intent1.putExtra("p_height",p_user_height);
                intent1.putExtra("p_record",p_user_record);
                intent1.putExtra("p_myaltime",p_user_myalartime);
                intent1.putExtra("p_myalday",p_user_myalartday);
                intent1.putExtra("p_uid",user_id);
//                intent1.putExtra("p_utime",p_utime); //수정해야해ㅐ해ㅐ해수정수정
//                intent1.putExtra("p_udate",p_udate);
                startActivity(intent1);
            }
        });

    }




    private class DownloadFilesTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL(p_img_link + "");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(con.getInputStream());

                Message message = myHandler.obtainMessage();
                message.obj = bitmap;
                myHandler.sendMessage(message);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    private Handler myHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Bitmap bit = (Bitmap) msg.obj;
            ur_p_img2.setImageBitmap(bit);
        }
    };
}
