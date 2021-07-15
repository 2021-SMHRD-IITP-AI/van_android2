package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class subscription_information extends AppCompatActivity {
    private EditText edt_join_name,edt_join_pw,edt_join_pw_ch,edt_join_phone,edt_join_date;
    private RadioButton rd_sex_m,rd_sex_w;
    private Button btn_join;
    private RequestQueue queue;
    private StringRequest stringRequest;
    private String user_id;
    private RadioButton rd_re;
    private String gender;

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
        setContentView(R.layout.activity_subscription_information);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");// 인텐트 받아온값
        Toast.makeText(getApplicationContext(),user_id,Toast.LENGTH_SHORT).show();


        edt_join_name = findViewById(R.id.edt_join_name);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_pw_ch = findViewById(R.id.edt_join_pw_ch);
        edt_join_phone = findViewById(R.id.edt_join_phone);
        edt_join_date = findViewById(R.id.edt_join_date);

        btn_join = findViewById(R.id.btn_join);
        RadioGroup rd_group = findViewById(R.id.rd_group);

        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rd_sex_m) {                // 첫 번째 버튼이 선택 되었을 때
                    gender = "남";
                } else if (checkedId == R.id.rd_sex_w) {      // 두 번째 버튼이 선택 되었을 때
                    gender = "여";
                }
            }
        });


        //서버에 보내는 버튼
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_join_name.equals("")||edt_join_pw.equals("")||edt_join_pw_ch.equals("")||edt_join_phone.equals("")||edt_join_date.equals("")){
                    Toast.makeText(getApplicationContext(), "회원정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    sendRequest();
                }
            }

        });

        //달력피커 출력
        edt_join_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                new DatePickerDialog(subscription_information.this,myDatePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }


    //달력 포맷
    private void updateLabel() {
        String myFormat ="yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        edt_join_date.setText(sdf.format(myCalendar.getTime()));
    }

    private void sendRequest() {
        queue = Volley.newRequestQueue(this);
        String url ="http://59.0.249.28:300/Join";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject.getString("check");
                    if (value.equals("true")) {
                        Intent intent = new Intent(getApplicationContext(),login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "회원가입에 실패 했습니다.", Toast.LENGTH_SHORT).show();
                        edt_join_name.setText("");
                        edt_join_pw.setText("");
                        edt_join_pw_ch.setText("");
                        edt_join_date.setText("");
                        edt_join_phone.setText("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paramas = new HashMap<String, String>();
                paramas.put("user_id",user_id); //인텐트 받은 값 서버에 올리기
                paramas.put("user_pw",edt_join_pw_ch.getText().toString());
                paramas.put("user_name",edt_join_name.getText().toString());
                paramas.put("user_tel",edt_join_phone.getText().toString());
                paramas.put("user_gender",gender);
                paramas.put("user_birth",edt_join_date.getText().toString());
                return paramas;
            }

        };
        queue.add(stringRequest);
    }
}