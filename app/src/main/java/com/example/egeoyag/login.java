package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    private EditText edt_email,edt_password;
    private Button btn_login;
    private RequestQueue queue;
    private StringRequest stringRequest;
    private ArrayList<MemberDTO> MemberList = new ArrayList<MemberDTO>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }
    public void sendRequest(){
        queue = Volley.newRequestQueue(this);
        String url ="http://172.17.96.1:3000/Login";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("성공1",response);
                if(!response.equals("null")){
                    Log.v("성공2",response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String id = jsonObject.getString("id");
                        Log.v("성공3",id);
                        String pw = jsonObject.getString("pw");
                        Log.v("성공4",id);
                        String name = jsonObject.getString("name");
                        String tel = jsonObject.getString("tel");

                        MemberDTO info = new MemberDTO(id,pw,name,tel);
                        Gson gson = new Gson();

                        String value = gson.toJson(info);

                        Log.v("resultValue", value);
                        PreferenceManager.setString(getApplicationContext(), "info", value);


                        Intent intent = new Intent(getApplicationContext(),main.class);
                        intent.putExtra("info",info);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"로그인에 실패 했습니다..",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data,"UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
                //리스폰스 전에 먼저 여기로 온다.
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Server로 데이터를 보낼 시 넣어주는 곳
                Map<String, String> paramas = new HashMap<String, String>();
                paramas.put("user_id",edt_email.getText().toString());
                paramas.put("user_pw",edt_password.getText().toString());
                return paramas; // 네트워크 끼리 데이터를 주고 받는것을
            }
        };
        queue.add(stringRequest);
    }
}