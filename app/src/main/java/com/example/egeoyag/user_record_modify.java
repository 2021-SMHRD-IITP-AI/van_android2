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

    private String img;
    private String user_id;
    private String dnum;
    private String p_g;
    private String mysy;
    private String data1, data2, dataA, dataB, timeData, dateall, p_img_link;
    private ImageView urm_p_img;


 TextView urm_pname;// urm_pcompany, urm_otcetc, urm_mysymptom, urm_date_start, urm_date_end,urm_takingtime1, urm_takingtime2, urm_takingtime3, urm_takingtime4, urm_takingtime5, urm_alarmtime, urm_alarmday,urm_height, urm_weigth, urm_myrecord;

    private String urm_s_pimg, urm_s_pname, urm_s_pcompany, urm_s_potcetc, urm_s_pgroup, urm_s_mysymptom, urm_s_date,
            urm_s_day, urm_s_height, urm_s_weight, urm_s_myrecord, urm_s_alarmtime, urm_s_alarmday, urm_s_pnum, urm_s_userid;



    int minday, data3, data4;



    Bitmap bitmap;

    Button btn_send; //이거 진짜 꼭 좀 고치세요. btn가 왜 te죠?



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

//
//        initial();
        urm_pname = findViewById(R.id.urm_pname);
//        urm_pcompany = findViewById(R.id.urm_pcompany);
//        urm_otcetc = findViewById(R.id.urm_potcetc);
//        urm_mysymptom = findViewById(R.id.urm_mysymptom);
//        urm_date_start = findViewById(R.id.urm_date_start);
//        urm_takingtime1 = findViewById(R.id.urm_takingtime1);
//        urm_takingtime2 = findViewById(R.id.urm_takingtime2);
//        urm_takingtime3 = findViewById(R.id.urm_takingtime3);
//        urm_takingtime4 = findViewById(R.id.urm_takingtime4);
//        urm_takingtime5 = findViewById(R.id.urm_takingtime5);
//        urm_alarmtime = findViewById(R.id.urm_alarmtime);
//        urm_alarmday = findViewById(R.id.urm_alarmday);
//        urm_p_img = findViewById(R.id.urm_p_img);
//        btn_send = findViewById(R.id.btn_send_modify);


//
//        Intent intent = getIntent();//user_record_list에서 받아온 인텐트
//        String urm_state = intent.getStringExtra("state");
//        Log.d("무슨인텐트", urm_state + "값있어?");
//
//        if(urm_state.equals("팽팽")){//user_record_list에서 넘어왔을 때
//
//            urm_s_pimg = intent.getStringExtra("p_arr_img");
//            urm_s_pnum = intent.getStringExtra("p_arr_num");
//            urm_s_pname = intent.getStringExtra("p_arr_name");
//            urm_s_potcetc = intent.getStringExtra("p_arr_otcetc");
//            urm_s_pgroup = intent.getStringExtra("p_arr_group");
//            urm_s_mysymptom = intent.getStringExtra("p_arr_symptom");
//            urm_s_weight = intent.getStringExtra("p_arr_weight");
//            urm_s_height = intent.getStringExtra("p_arr_height");
//            urm_s_myrecord = intent.getStringExtra("p_arr_record");
//            urm_s_alarmtime = intent.getStringExtra("p_arr_myalatime");
//            urm_s_alarmday = intent.getStringExtra("p_arr_myaladay");
//            urm_s_userid = intent.getStringExtra("p_arr_user_id");
//            urm_s_date = intent.getStringExtra("p_arr_user_time");//시작 ~ 끝
//            Log.d("이게뭐야",urm_s_date+"잉");
//            urm_s_day = intent.getStringExtra("p_arr_user_date");
//
//        }
//        else{
//            Intent intent_from_user_record = getIntent();
//
//            urm_s_pimg = intent_from_user_record.getStringExtra("p_ur_img");
//
//            Log.d("인텐트", urm_s_pimg+"들어왔나용?");
//
//            urm_s_pname = intent_from_user_record.getStringExtra("p_name");
//            urm_s_pcompany = intent_from_user_record.getStringExtra("p_company");
//            urm_s_potcetc = intent_from_user_record.getStringExtra("p_otcetc");
//            urm_s_pgroup = intent_from_user_record.getStringExtra("p_group");
//            urm_s_mysymptom = intent_from_user_record.getStringExtra("p_my_symptom");
//            urm_s_date = intent_from_user_record.getStringExtra("p_taking_date");
//            urm_s_day = intent_from_user_record.getStringExtra("p_user_time");
//            urm_s_height = intent_from_user_record.getStringExtra("p_user_height");
//            urm_s_weight = intent_from_user_record.getStringExtra("p_user_weight");
//            urm_s_myrecord = intent_from_user_record.getStringExtra("p_user_record");
//            urm_s_alarmtime = intent_from_user_record.getStringExtra("p_user_myalartime");
//            urm_s_alarmday = intent_from_user_record.getStringExtra("p_user_myalartday");
//            urm_s_pnum = intent_from_user_record.getStringExtra("p_num");
//            urm_s_userid = intent_from_user_record.getStringExtra("user_id");
//
//        }
//
//
//        p_img_link = urm_s_pimg;
//
//        user_record_modify.DownloadFilesTask task = new user_record_modify.DownloadFilesTask();
//        task.execute();
//
//
//        //Integer.parseInt();
//        Log.d("인텐트", urm_s_pname + "이름값이 있나용?");
//
//
//        urm_pname.setText(urm_s_pname);
//        urm_pcompany.setText(urm_s_pcompany);
//        urm_otcetc.setText(urm_s_potcetc);
//        urm_mysymptom.setText(urm_s_mysymptom);
//        urm_date_start.setText(urm_s_date);
//        urm_takingtime1.setText(urm_s_day);
//        urm_takingtime2.setText(urm_s_day);
//        urm_takingtime3.setText(urm_s_day);
//        urm_takingtime4.setText(urm_s_day);
//        urm_takingtime5.setText(urm_s_day);
//        urm_alarmtime.setText(urm_s_alarmtime);
//        urm_alarmday.setText(urm_s_day);
//        urm_height.setText(urm_s_height);
//        urm_weigth.setText(urm_s_weight);
//        urm_myrecord.setText(urm_s_myrecord);
//
//
//
//
//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_send_ur = new Intent(getApplicationContext(),user_record.class);
//                intent_send_ur.putExtra("path",path);
//                intent_send_ur.putExtra("p_img",urm_s_pimg);
//                intent_send_ur.putExtra("p_num",urm_s_pnum );
//                intent_send_ur.putExtra("p_name",urm_s_pname );
//                intent_send_ur.putExtra("p_company",urm_s_pcompany );
//                intent_send_ur.putExtra("p_otc",urm_s_potcetc );
//                intent_send_ur.putExtra("p_group",urm_s_pgroup );
//                intent_send_ur.putExtra("p_symp",urm_s_mysymptom );
//                intent_send_ur.putExtra("p_weight",urm_s_weight);
//                intent_send_ur.putExtra("p_height",urm_s_height);
//                intent_send_ur.putExtra("p_record",urm_s_myrecord );
//                intent_send_ur.putExtra("p_myaltime",urm_s_alarmtime );
//                intent_send_ur.putExtra("p_myalday",urm_s_alarmday );
//                intent_send_ur.putExtra("p_uid",user_id);
//
//
////                intent1.putExtra("p_utime",p_utime); //수정해야해ㅐ해ㅐ해수정수정
////                intent1.putExtra("p_udate",p_udate);
//                startActivity(intent_send_ur);
//            }
//        });
//
    }
//
    private void initial() {

//        urm_pname = findViewById(R.id.urm_pname);
//        urm_pcompany = findViewById(R.id.urm_pcompany);
//        urm_otcetc = findViewById(R.id.urm_potcetc);
//        urm_mysymptom = findViewById(R.id.urm_mysymptom);
//        urm_date_start = findViewById(R.id.urm_date_start);
//        urm_takingtime1 = findViewById(R.id.urm_takingtime1);
//        urm_takingtime2 = findViewById(R.id.urm_takingtime2);
//        urm_takingtime3 = findViewById(R.id.urm_takingtime3);
//        urm_takingtime4 = findViewById(R.id.urm_takingtime4);
//        urm_takingtime5 = findViewById(R.id.urm_takingtime5);
//        urm_alarmtime = findViewById(R.id.urm_alarmtime);
//        urm_alarmday = findViewById(R.id.urm_alarmday);
//        urm_p_img = findViewById(R.id.urm_p_img);
//        btn_send = findViewById(R.id.btn_send_modify);

    }
//
//
//    private class DownloadFilesTask extends AsyncTask {
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            try {
//                URL url = new URL(p_img_link + "");
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                bitmap = BitmapFactory.decodeStream(con.getInputStream());
//
//                Message message = myHandler.obtainMessage();
//                message.obj = bitmap;
//                myHandler.sendMessage(message);
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//    }
//
//    private Handler myHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            Bitmap bit = (Bitmap) msg.obj;
//            urm_p_img.setImageBitmap(bit);
//        }
//    };
}
