package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class user_record extends AppCompatActivity {//기록해둔 내용 확인 부분
                // (main 에서 들어올 경우 : 입력 값 가져온 후 출력, user_record_list에서 들어올 경우 : 서버에서 정보 가져온 후 출력)
                // main : intent - path 0,   user_record_list : intent - path 1

    private String dnum= "";
    private String p_g = "";
    private String data1, data2,dataA ,dataB, timeData, dateall, p_img_link;
    private String p_name, p_company, p_otcetc, p_group, p_img, p_num, p_user_id;
    private ImageView ur_p_img;

    int minday,data3,data4;
    String recordmytime ;


    private String user_id;
    Bitmap bitmap;
    TextView et_time;

    Button btn_send;
    EditText nae,length, weight,user_record;
    TextView p_n, p_c, jun,bun, date1, date2, mytime, daybreak, morning, afternoon, evening, midnight, time, day ;
    TextView minus_day;
    int cnt1, cnt2, cnt3, cnt4, cnt5;
    private RequestQueue queue; //요청하는 개체
    private StringRequest stringRequest; // queue가 정보를 통해서...
    Switch alarm_switch; //스위치

    Calendar myCalendar = Calendar.getInstance();
    Calendar myCalendar2 = Calendar.getInstance();
    Calendar myCalendar3 = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_record);
        day = findViewById(R.id.urm_alarmday);
        //dnum = findViewById(R.id.dnum);
        ur_p_img = findViewById(R.id.ur_p_img); //이미지 뷰 이름
        p_n = findViewById(R.id.ur_pname);
        p_c = findViewById(R.id.ur_pcompany);
        jun = findViewById(R.id.ur_potcetc);
        nae = findViewById(R.id.ur_mysymptom);
        date1 = findViewById(R.id.ur_date_start);
        date2 = findViewById(R.id.ur_date_end);
        mytime=findViewById(R.id.my_time);
        daybreak = findViewById(R.id.ur_takingtime1);
        morning = findViewById(R.id.ur_takingtime2);
        afternoon = findViewById(R.id.ur_takingtime3);
        evening = findViewById(R.id.ur_takingtime4);
        midnight = findViewById(R.id.ur_takingtime5);
        time = findViewById(R.id.ur_alarmtime);
        day = findViewById(R.id.ur_alarmday);
        length = findViewById(R.id.ur_height);
        weight = findViewById(R.id.ur_weigth);
        user_record=findViewById(R.id.ur_myrecord);
        et_time=findViewById(R.id.ur_alarmtime);


        minus_day=findViewById(R.id.ur_date_minus);

        btn_send = findViewById(R.id.btn_send);

        alarm_switch=findViewById(R.id.alarm_switch);

        //path로 어느 화면에서 넘어왔는지 확인
        //0 - main
        //1 - user_record_list
        String path = getIntent().getStringExtra("path");
        Log.d("인텐트 확인", path + "타단");
        String p_otcetc,p_nae,p_weight,p_height,p_record,p_mytime,p_myday,p_user_time,p_user_date;


        if(path.equals("0")){
            // 0 일경우 : main 에서 넘어 옴
            Intent intent = getIntent();
            p_name = intent.getStringExtra("intent_p_name");
            p_company = intent.getStringExtra("intent_p_company");
            p_otcetc = intent.getStringExtra("intent_p_otcetc");
            p_group = intent.getStringExtra("intent_p_group");
            p_img = intent.getStringExtra("intent_p_img");
            p_num = intent.getStringExtra("intent_p_num");

            p_img_link = p_img;
            user_record.DownloadFilesTask task = new user_record.DownloadFilesTask();
            task.execute();

            String inputValue = PreferenceManager.getString(getApplicationContext(),"info");

            try {
                JSONObject jsonObject = new JSONObject(inputValue);
                user_id = jsonObject.getString("id");

            } catch (JSONException e) {

                e.printStackTrace();
            }

            p_n.setText(p_name);
            jun.setText(p_otcetc);
            p_c.setText(p_company);

        }else {
            //path =1, user_record_list에서 값 가져오기
            Intent intent1 = getIntent();

            p_img = intent1.getStringExtra("p_arr_img");
            p_num = intent1.getStringExtra("p_arr_num");
            p_name = intent1.getStringExtra("p_arr_name");
            p_otcetc = intent1.getStringExtra("p_arr_ptcetc");
            p_group = intent1.getStringExtra("p_arr_group");
            p_nae= intent1.getStringExtra("p_arr_symptom");
            p_weight = intent1.getStringExtra("p_arr_weight");
            p_height = intent1.getStringExtra("p_arr_height");
            p_record = intent1.getStringExtra("p_arr_record");
            p_mytime = intent1.getStringExtra("p_arr_myalatime");
            p_myday = intent1.getStringExtra("p_arr_myaladay");
            p_user_id = intent1.getStringExtra("p_arr_user_id");
            p_user_time = intent1.getStringExtra("p_arr_user_time");
            p_user_date = intent1.getStringExtra("p_arr_user_date");
            p_company = intent1.getStringExtra("p_arr_company");

            user_record.DownloadFilesTask task = new user_record.DownloadFilesTask();
            task.execute();

            p_img_link = p_img;

            p_n.setText(p_name);
            jun.setText(p_otcetc);
            nae.setText(p_nae);
            weight.setText(p_weight);
            length.setText(p_height);
            user_record.setText(p_record);
            mytime.setText(p_mytime);
            date1.setText(p_myday);
            time.setText(p_user_time);
            day.setText(p_user_date);
            p_c.setText(p_company);


        }


        //p_n.setText(p_name);
        //p_c.setText(p_company);
        //jun.setText(p_otcetc);
        //bun.setText(p_group);


        // user_record.DownloadFilesTask task = new user_record.DownloadFilesTask();


        alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    Log.d("알람기능","켰음");

                }else{
                    time.setText("시간선택");
                    day.setText("요일선택");
                    Log.d("알람기능","껐음");
                }
            }
        });


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeData = dataA + "~" + dataB;
                Log.d("날짜1",timeData);


                Log.d("날짜2",String.valueOf(data3+data4));

                Intent intent_from_user_record = new Intent(getApplicationContext(), new_user_record_modify.class);
                intent_from_user_record.putExtra("p_ur_img", p_img);
                intent_from_user_record.putExtra("p_name", p_n.getText().toString());
                intent_from_user_record.putExtra("p_company",p_company);
                intent_from_user_record.putExtra("p_otcetc", p_otcetc);
                intent_from_user_record.putExtra("p_group",p_group);
                intent_from_user_record.putExtra("p_my_symptom", nae.getText().toString());
                intent_from_user_record.putExtra("p_taking_date",dateall);
                intent_from_user_record.putExtra("p_user_time",mytime.getText().toString());
                intent_from_user_record.putExtra("p_user_height",length.getText().toString());
                intent_from_user_record.putExtra("p_user_weight",weight.getText().toString());
                intent_from_user_record.putExtra("p_user_record", user_record.getText().toString());
                intent_from_user_record.putExtra("p_user_myalartime", time.getText().toString());
                intent_from_user_record.putExtra("p_user_myalartday", day.getText().toString());
                intent_from_user_record.putExtra("p_num",p_num);
                intent_from_user_record.putExtra("user_id", user_id);
                intent_from_user_record.putExtra("state","응애");


                if(path.equals("0")){
                    sendRequest();


                    startActivity(intent_from_user_record);

                }else {
                    //현우님이가 보내는 통로
                    sendRequest1();
//                    Intent intent = new Intent(getApplicationContext(), user_record_list.class);//여기가 아니라 user_record_modify 이동 아닌가여?
//
//                    startActivity(intent);
                    //여기에 인텐트자리가 맞는지 ?
                }



            }
        });


        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(user_record.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(user_record.this, myDatePicker3, myCalendar3.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(user_record.this, myDatePicker2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
                alarm_switch.setChecked(true);
            }
        });




        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm_switch.setChecked(true);
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(user_record.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        et_time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");


                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        daybreak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (cnt1 == 0) {
                    //     checked();
                    mytime.setText(daybreak.getText().toString());
                    daybreak.setBackgroundColor(Color.parseColor("#404677"));
                    daybreak.setTextColor(Color.WHITE);
                    cnt1 = 1;

                } else if (cnt1 == 1) {
                    mytime.setText("");
                    daybreak.setBackgroundColor(Color.parseColor("#E4DFDF"));
                    daybreak.setTextColor(Color.BLACK);



                    cnt1 = 0;

                }

                Log.v("cnt1", String.valueOf(cnt1));

                return false;
            }
        });
        morning.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                morning.setBackgroundColor(Color.parseColor("#404677"));
                morning.setTextColor(Color.WHITE);
                if (cnt2 == 0) {
                    mytime.setText(morning.getText().toString());
                    morning.setBackgroundColor(Color.parseColor("#404677"));
                    morning.setTextColor(Color.WHITE);
                    cnt2 = 1;
                } else if (cnt2 == 1) {
                    mytime.setText("");
                    morning.setBackgroundColor(Color.parseColor("#E4DFDF"));
                    morning.setTextColor(Color.BLACK);
                    cnt2 = 0;
                }

                Log.v("cnt1", String.valueOf(cnt2));
                return false;
            }
        });
        afternoon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                afternoon.setBackgroundColor(Color.parseColor("#404677"));
                afternoon.setTextColor(Color.WHITE);
                if (cnt3 == 0) {
                    mytime.setText(afternoon.getText().toString());
                    afternoon.setBackgroundColor(Color.parseColor("#404677"));
                    afternoon.setTextColor(Color.WHITE);
                    cnt3 = 1;
                } else if (cnt3 == 1) {
                    mytime.setText("");
                    afternoon.setBackgroundColor(Color.parseColor("#E4DFDF"));
                    afternoon.setTextColor(Color.BLACK);
                    cnt3 = 0;
                }

                Log.v("cnt1", String.valueOf(cnt3));
                return false;
            }
        });
        evening.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                evening.setBackgroundColor(Color.parseColor("#404677"));
                evening.setTextColor(Color.WHITE);
                if (cnt4 == 0) {
                    mytime.setText(evening.getText().toString());
                    evening.setBackgroundColor(Color.parseColor("#404677"));
                    evening.setTextColor(Color.WHITE);
                    cnt4 = 1;
                } else if (cnt4 == 1) {
                    mytime.setText("");
                    evening.setBackgroundColor(Color.parseColor("#E4DFDF"));
                    evening.setTextColor(Color.BLACK);
                    cnt4 = 0;
                }

                Log.v("cnt1", String.valueOf(cnt4));
                return false;
            }
        });
        midnight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                midnight.setBackgroundColor(Color.parseColor("#404677"));
                midnight.setTextColor(Color.WHITE);
                if (cnt5 == 0) {
                    mytime.setText(midnight.getText().toString());
                    midnight.setBackgroundColor(Color.parseColor("#404677"));
                    midnight.setTextColor(Color.WHITE);
                    cnt5 = 1;
                } else if (cnt5 == 1) {
                    mytime.setText("");
                    midnight.setBackgroundColor(Color.parseColor("#E4DFDF"));
                    midnight.setTextColor(Color.BLACK);
                    cnt5 = 0;
                }

                Log.v("cnt1", String.valueOf(cnt5));
                return false;
            }
        });
    }


    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        String myFormat2 = "dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.KOREA);


        TextView et_date = (TextView) findViewById(R.id.ur_date_start);
        et_date.setText(sdf.format(myCalendar.getTime()));
        data1 = sdf2.format(myCalendar.getTime());
        dataA = sdf.format(myCalendar.getTime());
        data3= Integer.parseInt(data1);
    }

    private void updateLabel3() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        String myFormat2 = "dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.KOREA);

        TextView et_date2 = (TextView) findViewById(R.id.ur_date_end);
        et_date2.setText(sdf.format(myCalendar2.getTime()));
        data2 = sdf2.format(myCalendar2.getTime());


        dataB = sdf.format(myCalendar.getTime());



        Log.d("확인", dataB);
        data4= Integer.parseInt(data2);

        minus_day.setText(String.valueOf(data4-data3+"day"));

        dateall = dataA+"~"+dataB;



    }

    private void updateLabel2() {

        if (myCalendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            day.setText("일요일");
        } else if (myCalendar2.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            day.setText("월요일");
        } else if (myCalendar2.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            day.setText("화요일");
        } else if (myCalendar2.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            day.setText("수요일");
        } else if (myCalendar2.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            day.setText("목요일");
        } else if (myCalendar2.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            day.setText("금요일");
        } else if (myCalendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            day.setText("토요일");
        }
    }


    private void sendRequest() {
        // adapter = new MemberAdapter();
        // adapter.addItem("아이디","일련넘버","약이름","약회사","전문의약","분류명","내증상","날짜1","날짜2","기상","아침","점심","저녁","취침전","시간","요일","키","몸무게");
        queue = Volley.newRequestQueue(this);
        String url = "http://59.0.249.28:3000/UserRecord";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로 부터 데이터를 받아온 곳
                Log.v("성공", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject.getString("check");

                    //  adapter.addItem(user_id,s_num, p_name, p_com, p_jun, p_bun,p_nae,p_date1,p_date2,p_daybreak,p_morning, p_afternoon,p_evening,p_midnight,p_time,p_day,p_length,p_weight);
                    Log.v("성공2",value);
                    if (value.equals("true")) {
                        Toast.makeText(user_record.this, "수정완료", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "완료버튼을 눌러주세요.", Toast.LENGTH_SHORT).show();
//                        edt_dn.setText("");
//                        edt_date.setText("");
//                        edt_time.setText("");
//                        edt_height.setText("");
//                        edt_weight.setText("");
//                        my_record.setText("");

                    }
                    //img,


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //에러발생했을때
                error.printStackTrace();
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);
            }

            ///
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                //server로 데이터를 보낼 시 넣어주는 곳

                //=======\
                Map<String, String> params = new HashMap<String, String>();


                params.put("drug_num", p_num);//짜잔
                params.put("drug_name", p_n.getText().toString());
                params.put("otcetc", jun.getText().toString());
                params.put("drug_group", p_group);
                params.put("drug_img", p_img);
                params.put("my_symptom", nae.getText().toString());
                params.put("user_taking_date", dateall);
                params.put("user_time", mytime.getText().toString());
                params.put("user_height", length.getText().toString());
                params.put("user_weight", weight.getText().toString());
                params.put("user_record", user_record.getText().toString());
                params.put("user_myalartime", time.getText().toString());
                params.put("user_myalartday", day.getText().toString());
                params.put("user_id", user_id);

                Log.v("약이름 값확인 김효매 샌드리퀘스트", p_n.getText().toString() + "확인");
                Log.v("약이미지 값확인", p_img + "확인");
                Log.v("약번호 값확인", dnum+ "확인");
                Log.v("유저아이디 값확인", user_id+ "확인");
                Log.v("복용날짜 값확인", dateall+ "확인");
                Log.v("복용시간 값확인", mytime.getText().toString()+ "확인");
                Log.v("복용알람날짜 값확인", day.getText().toString()+ "확인");
                Log.v("복용알람시간 값확인", time.getText().toString()+ "확인");
                Log.v("키 값확인", length.getText().toString()+ "확인");
                Log.v("몸무게 값확인", weight.getText().toString()+ "확인");
                Log.v("기록 값확인", user_record.getText().toString()+ "확인");


                // params.put("p_n",.getText().toString());
                // params.put("p_n",.getText().toString());
                return params;

            }
        };

        queue.add(stringRequest);
    }

    //수정한 값을 다시 보내주는 곳
    private void sendRequest1() {
        queue = Volley.newRequestQueue(this);
        String url = "http://59.0.249.28:3000/Modify";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Server로 부터 데이터를 받아온 곳
                Log.v("성공", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject.getString("check");

                    Log.v("성공2",value);
                    if (value.equals("true")) {
                        Toast.makeText(user_record.this, "수정완료", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "완료버튼을 눌러주세요.", Toast.LENGTH_SHORT).show();
//                        edt_dn.setText(""); //여기 민우님 껄로 바꾸세요.
//                        edt_date.setText("");
//                        edt_time.setText("");
//                        edt_height.setText("");
//                        edt_weight.setText("");
//                        my_record.setText("");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //에러발생했을때
                error.printStackTrace();
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return super.parseNetworkResponse(response);
            }

            ///
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("drug_num", p_num);
                params.put("drug_name", p_n.getText().toString());
                params.put("otcetc", jun.getText().toString());
                params.put("drug_group", p_group);
                params.put("drug_img", p_img);
                params.put("my_symptom", nae.getText().toString());
                params.put("user_taking_date", dateall);
                params.put("user_time", mytime.getText().toString());
                params.put("user_height", length.getText().toString());
                params.put("user_weight", weight.getText().toString());
                params.put("user_record", user_record.getText().toString());
                params.put("user_myalartime", time.getText().toString());
                params.put("user_myalartday", day.getText().toString());
                params.put("user_id", user_id);


                Log.v("약이름 값확인", p_n.getText().toString() + "확인");
                Log.v("약이미지 값확인", p_img + "확인");
                Log.v("약번호 값확인", dnum+ "확인");
                Log.v("유저아이디 값확인", user_id+ "확인");
                Log.v("복용날짜 값확인", dateall+ "확인");
                Log.v("복용시간 값확인", mytime.getText().toString()+ "확인");
                Log.v("복용알람날짜 값확인", day.getText().toString()+ "확인");
                Log.v("복용알람시간 값확인", time.getText().toString()+ "확인");
                Log.v("키 값확인", length.getText().toString()+ "확인");
                Log.v("몸무게 값확인", weight.getText().toString()+ "확인");
                Log.v("기록 값확인", user_record.getText().toString()+ "확인");

                return params;

            }
        };

        queue.add(stringRequest);
    }

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }

    };
    DatePickerDialog.OnDateSetListener myDatePicker2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, month);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel2();

            minday =  data4 - data3;
            minus_day.setText(String.valueOf(minday));
        }

    };
    DatePickerDialog.OnDateSetListener myDatePicker3 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar2.set(Calendar.YEAR, year);
            myCalendar2.set(Calendar.MONTH, month);
            myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel3();

        }

    };

    private class DownloadFilesTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL(p_img_link+"");
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
    private Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            Bitmap bit = (Bitmap)msg.obj;
            ur_p_img.setImageBitmap(bit);
        }
    };
}
