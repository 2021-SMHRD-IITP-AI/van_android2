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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class new_user_record_modify extends AppCompatActivity {

    private TextView nurm_pname,nurm_pcompany,nurm_potcetc,nurm_mysymptom,nurm_taking_date,nurm_taking_day,nurm_takingtime1,nurm_takingtime2,nurm_takingtime3,nurm_takingtime4,nurm_takingtime5,nurm_alarmtime,nurm_taking_date4,nurm_height,nurm_height2,nurm_myrecor;

    private ImageView urm_p_img;

    private String urm_s_pimg, urm_s_pname, urm_s_pcompany, urm_s_potcetc, urm_s_pgroup, urm_s_mysymptom, urm_s_date,
            urm_s_day, urm_s_height, urm_s_weight, urm_s_myrecord, urm_s_alarmtime, urm_s_alarmday, urm_s_pnum, urm_s_userid;

    private String p_img_link;
    private Button btn_send;

    Bitmap bitmap;

    private RequestQueue queue; //요청하는 개체
    private StringRequest stringRequest; // queue가 정보를 통해서...

    private String path = "1"; // 모디피에서 다시 레코드로 넘어갈때.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_record_modify);

        initial();

        Intent intent = getIntent();//user_record_list에서 받아온 인텐트
        String urm_state = intent.getStringExtra("state");
        Log.d("무슨인텐트", urm_state + "값있어?");

        if(urm_state.equals("팽팽")){//user_record_list에서 넘어왔을 때

            urm_s_pimg = intent.getStringExtra("p_arr_img");
            urm_s_pnum = intent.getStringExtra("p_arr_num");
            urm_s_pname = intent.getStringExtra("p_arr_name");
            urm_s_potcetc = intent.getStringExtra("p_arr_otcetc");
            urm_s_pgroup = intent.getStringExtra("p_arr_group");
            urm_s_mysymptom = intent.getStringExtra("p_arr_symptom");
            urm_s_weight = intent.getStringExtra("p_arr_weight");
            urm_s_height = intent.getStringExtra("p_arr_height");
            urm_s_myrecord = intent.getStringExtra("p_arr_record");
            urm_s_alarmtime = intent.getStringExtra("p_arr_myalatime");
            urm_s_alarmday = intent.getStringExtra("p_arr_myaladay");
            urm_s_userid = intent.getStringExtra("p_arr_user_id");
            urm_s_date = intent.getStringExtra("p_arr_user_time");//시작 ~ 끝
            Log.d("이게뭐야",urm_s_date+"잉");
            urm_s_day = intent.getStringExtra("p_arr_user_date");

        }
        else{
            Intent intent_from_user_record = getIntent();

            urm_s_pimg = intent_from_user_record.getStringExtra("p_ur_img");

            Log.d("인텐트", urm_s_pimg+"들어왔나용?");

            urm_s_pname = intent_from_user_record.getStringExtra("p_name");
            urm_s_pcompany = intent_from_user_record.getStringExtra("p_company");
            urm_s_potcetc = intent_from_user_record.getStringExtra("p_otcetc");
            urm_s_pgroup = intent_from_user_record.getStringExtra("p_group");
            urm_s_mysymptom = intent_from_user_record.getStringExtra("p_my_symptom");
            urm_s_date = intent_from_user_record.getStringExtra("p_taking_date");
            urm_s_day = intent_from_user_record.getStringExtra("p_user_time");
            urm_s_height = intent_from_user_record.getStringExtra("p_user_height");
            urm_s_weight = intent_from_user_record.getStringExtra("p_user_weight");
            urm_s_myrecord = intent_from_user_record.getStringExtra("p_user_record");
            urm_s_alarmtime = intent_from_user_record.getStringExtra("p_user_myalartime");
            urm_s_alarmday = intent_from_user_record.getStringExtra("p_user_myalartday");
            urm_s_pnum = intent_from_user_record.getStringExtra("p_num");
            urm_s_userid = intent_from_user_record.getStringExtra("user_id");

        }


        p_img_link = urm_s_pimg;

        new_user_record_modify.DownloadFilesTask task = new new_user_record_modify.DownloadFilesTask();
        task.execute();


        //Integer.parseInt();
        Log.d("인텐트", urm_s_pname + "이름값이 있나용?");

        nurm_pname.setText(urm_s_pname);
        nurm_pcompany.setText(urm_s_pcompany);
        nurm_potcetc.setText(urm_s_potcetc);
        nurm_mysymptom.setText(urm_s_mysymptom);
        nurm_taking_date.setText(urm_s_date);
        nurm_taking_day.setText(urm_s_day);
        nurm_takingtime1.setText(urm_s_day);
        nurm_takingtime2.setText(urm_s_day);
        nurm_takingtime3.setText(urm_s_day);
        nurm_takingtime4.setText(urm_s_day);
        nurm_takingtime5.setText(urm_s_day);
        nurm_alarmtime.setText(urm_s_alarmtime);
        nurm_taking_date4.setText(urm_s_alarmday);
        nurm_height.setText(urm_s_height);
        nurm_height2.setText(urm_s_weight);
        nurm_myrecor.setText(urm_s_myrecord);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_send_ur = new Intent(getApplicationContext(),user_record.class);
                intent_send_ur.putExtra("path",path);
                intent_send_ur.putExtra("p_img",urm_s_pimg);
                intent_send_ur.putExtra("p_num",urm_s_pnum );
                intent_send_ur.putExtra("p_name",urm_s_pname );
                intent_send_ur.putExtra("p_company",urm_s_pcompany );
                intent_send_ur.putExtra("p_otc",urm_s_potcetc );
                intent_send_ur.putExtra("p_group",urm_s_pgroup );
                intent_send_ur.putExtra("p_symp",urm_s_mysymptom );
                intent_send_ur.putExtra("p_weight",urm_s_weight);
                intent_send_ur.putExtra("p_height",urm_s_height);
                intent_send_ur.putExtra("p_record",urm_s_myrecord );
                intent_send_ur.putExtra("p_myaltime",urm_s_alarmtime );
                intent_send_ur.putExtra("p_myalday",urm_s_alarmday );
                intent_send_ur.putExtra("p_uid",urm_s_userid);


                startActivity(intent_send_ur);
            }
        });


    }

    private void initial() {
        nurm_pname = findViewById(R.id.nurm_pname);
        nurm_pcompany = findViewById(R.id.nurm_pcompany);
        nurm_potcetc = findViewById(R.id.nurm_potcetc);
        nurm_mysymptom = findViewById(R.id.nurm_mysymptom);
        nurm_taking_date = findViewById(R.id.nurm_taking_date);
        nurm_taking_day = findViewById(R.id.nurm_taking_day);
        nurm_takingtime1 = findViewById(R.id.nurm_takingtime1);
        nurm_takingtime2 = findViewById(R.id.nurm_takingtime2);
        nurm_takingtime3 = findViewById(R.id.nurm_takingtime3 );
        nurm_takingtime4 = findViewById(R.id.nurm_takingtime4);
        nurm_takingtime5 = findViewById(R.id.nurm_takingtime5);
        nurm_alarmtime = findViewById(R.id.nurm_alarmtime);
        nurm_taking_date4 = findViewById(R.id.nurm_taking_date4);
        nurm_height = findViewById(R.id.nurm_height);
        nurm_height2 = findViewById(R.id.nurm_height2);
        nurm_myrecor = findViewById(R.id.nurm_myrecor);

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
            urm_p_img.setImageBitmap(bit);
        }
    };
}

