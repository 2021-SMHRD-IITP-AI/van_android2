package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class user_record_list extends AppCompatActivity {

    private ListView pillListView;
    private Button btn_date;
    private TextView tv_ymd,tv_name,tv_1;
    private EditText edt_input;
    private ImageView img_search;
    private listviewadapter adapter;
    private ArrayList ListViewItemList;
    private ImageView pl_img;
    private Bitmap bitmap;
    private String [] taking_p_arr_img;
    private String [] taking_p_arr_num;
    private String [] taking_p_arr_name;
    private String [] taking_p_arr_otcetc;
    private String [] taking_p_arr_group;
    private String [] taking_p_arr_symptom;
    private String [] taking_p_arr_weight;
    private String [] taking_p_arr_height;
    private String [] taking_p_arr_record;
    private String [] taking_p_arr_myalatime;
    private String [] taking_p_arr_myaladay;
    private String [] taking_p_arr_user_id;
    private String [] taking_p_arr_user_time;
    private String [] taking_p_arr_user_date;
    private String [] taking_p_arr_company;




    private RequestQueue queue;
    private StringRequest stringRequest;



    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            updateLabel();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_record_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edt_input = findViewById(R.id.edt_input);

        pl_img = findViewById(R.id.pl_img);
        tv_name = findViewById(R.id.tv_name);
        tv_1 = findViewById(R.id.tv_1);

        tv_ymd = findViewById(R.id.tv_ymd);



        img_search = findViewById(R.id.img_search);

        sendRequest();   //2는 아직 이렇게 적지는 않았어요 위치는

        pillListView = findViewById(R.id.pillListView);
        adapter = new listviewadapter();

        pillListView.setAdapter(adapter);            //이 부분이 리스트뷰 실행입니당  넹 일단 ppt 부분 해주세용~~~네네에에네에
        pillListView.setTextFilterEnabled(true);

        pillListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String p_arr_img = taking_p_arr_img[position];
                String p_arr_num = taking_p_arr_num[position];
                String p_arr_name = taking_p_arr_name[position];
                String p_arr_otcetc = taking_p_arr_otcetc[position];
                String p_arr_group = taking_p_arr_group[position];
                String p_arr_symptom = taking_p_arr_symptom[position];
                String p_arr_weight = taking_p_arr_weight[position];
                String p_arr_height = taking_p_arr_height[position];
                String p_arr_record = taking_p_arr_record[position];
                String p_arr_myalatime = taking_p_arr_myalatime[position];
                String p_arr_myaladay = taking_p_arr_myaladay[position];
                String p_arr_user_id = taking_p_arr_user_id[position];
                String p_arr_user_time = taking_p_arr_user_time[position];
                String p_arr_user_date = taking_p_arr_user_date[position];
                String p_arr_company = taking_p_arr_company[position];

                Intent intent = new Intent(getApplicationContext(), user_record_modify.class);
                intent.putExtra("p_arr_img", p_arr_img);
                intent.putExtra("p_arr_num", p_arr_num);
                intent.putExtra("p_arr_name", p_arr_name);
                intent.putExtra("p_arr_otcetc", p_arr_otcetc);
                intent.putExtra("p_arr_group", p_arr_group);
                intent.putExtra("p_arr_symptom", p_arr_symptom);
                intent.putExtra("p_arr_weight", p_arr_weight);
                intent.putExtra("p_arr_height", p_arr_height);
                intent.putExtra("p_arr_record", p_arr_record);
                intent.putExtra("p_arr_myalatime", p_arr_myalatime);
                intent.putExtra("p_arr_myaladay", p_arr_myaladay);
                intent.putExtra("p_arr_user_id", p_arr_user_id);
                intent.putExtra("p_arr_user_time", p_arr_user_time);
                intent.putExtra("p_arr_user_date", p_arr_user_date);
                intent.putExtra("p_arr_company", p_arr_company);

                startActivity(intent);




            }
        });


        img_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                edt_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        pillListView.setFilterText(edt_input.getText().toString());
                    }

                    //스노우볼 스노우볼 스노우볼

                    @Override
                    public void afterTextChanged(Editable s) {

                        String filterText = s.toString();
                        adapter.fillter(filterText);
                        if(filterText.length()>0){

                            Log.d("확인",filterText);

                            pillListView.setAdapter(adapter);

                            pillListView.setFilterText(filterText);
                            adapter.notifyDataSetChanged();
                            pillListView.setAdapter(adapter);

                            for(int i = 0; i < adapter.getCount(); i++){
                                Log.d("확인1",adapter.getItem(i).toString());
                            }



                        }else{
                            pillListView.clearTextFilter();
                        }
                    }
                });



            }
        });




        btn_date = findViewById(R.id.btn_date);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(user_record_list.this,myDatePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        pillListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });




    }




    private void updateLabel() {
        String myFormat ="yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

    }



    public void sendRequest(){
        adapter = new listviewadapter();
        //adapter.additem("img","name","symptom1","ymd");

        queue = Volley.newRequestQueue(this);
        String url = "http://59.0.249.28:3000/RecordList";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로 부터 데이터를 받아온 곳
                Log.v("성공",response);
                try {
                    JSONArray array = new JSONArray(response);
                    Log.v("성공3",response);
                    for(int i = 0; i < array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);

                        taking_p_arr_img[i] = jsonObject.getString("drug_img");
                        taking_p_arr_num[i] = jsonObject.getString("drug_num");
                        taking_p_arr_name[i] = jsonObject.getString("drug_name");
                        taking_p_arr_otcetc[i] = jsonObject.getString("drug_otcetc");
                        taking_p_arr_group[i] = jsonObject.getString("drug_group");
                        taking_p_arr_symptom[i] = jsonObject.getString("my_symptom");
                        taking_p_arr_weight[i] = jsonObject.getString("my_weight");
                        taking_p_arr_height[i] = jsonObject.getString("my_height");
                        taking_p_arr_record[i] = jsonObject.getString("my_record");
                        taking_p_arr_myalatime[i] = jsonObject.getString("my_alarm_time");
                        taking_p_arr_myaladay[i] = jsonObject.getString("my_alarm_day");
                        taking_p_arr_user_id[i] = jsonObject.getString("user_id");
                        taking_p_arr_user_time[i] = jsonObject.getString("my_time");
                        taking_p_arr_user_date[i] = jsonObject.getString("my_date");
                        taking_p_arr_company[i] = jsonObject.getString("drug_company");

                        //여기서 모든 정보를 싹 다 받아요

                        String img = jsonObject.getString("drug_img");
                        String name = jsonObject.getString("drug_name");
                        String symptom1 = jsonObject.getString("drug_group");
                        String ymd = jsonObject.getString("my_date");

                        // Log.d("성공 제이슨 값 받아오기",  name + id + num + date + "성공?");


                        ListViewItem dto = new ListViewItem(img, name, symptom1, ymd);

                        user_record_list.DownloadFilesTask task = new user_record_list.DownloadFilesTask();
                        task.setItem(dto);


                    }

                    pillListView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("1", "user_id");
                //Server 통신시 Error발생하면 오는 곳
                error.printStackTrace();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Server로 데이터를 보낼 시 넣어주는 곳
                Map<String,String> params = new HashMap<String,String>();
                // String info = PreferenceManager.getString(getApplicationContext(),"info");

                // Log.v("성공",info);
                params.put("user_id","1");


                Log.v("성공1",edt_input.getText().toString());
                //return super.getParams();
                return params;
            }
        };
        queue.add(stringRequest);
    }


//왜 스트링을 제이슨 어레이로 못바꿔 왜왜왜



    private class DownloadFilesTask extends AsyncTask {
        ListViewItem dto;
        public void setItem(ListViewItem dto){
            //Log.d("약약약","테스크 실행");
            this.dto = dto;
            execute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL(dto.getImg());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(con.getInputStream());

                Bitmap_ListViewItem dto = new Bitmap_ListViewItem(this.dto, bitmap);

                Message message = myHandler.obtainMessage();
                message.obj = dto;

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

    private Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            Log.d("성공 핸들러", msg.obj.toString());

            Bitmap_ListViewItem dto = (Bitmap_ListViewItem) msg.obj;

//            Bitmap bit = (Bitmap)msg.obj;

            // adapter.additem(dto);
            //  pillListView.setAdapter(adapter);

            // Log.d("성공 로그찍기", dto.getDto().getName() +"/" + dto.getDto().getName() + "/"+dto.getDto().getSymptom1()+"/"+dto.getDto().getYmd());
//
//            pl_img.setImageBitmap(dto.getBitmap());
//            tv_name.setText(dto.getDto().getName());
//            tv_1.setText(dto.getDto().getSymptom1());
//            tv_ymd.setText(dto.getDto().getYmd());

            adapter.additem(dto);


        }
    };



}





