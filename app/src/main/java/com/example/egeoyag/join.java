package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class join extends AppCompatActivity {
    private EditText edt_join_email;
    private CheckBox cb_all,cb_servie_use,cb_service_in,cb_marketing,cb_ad;
    private Button btn_next;
    private RequestQueue queue;
    private StringRequest stringRequest;

    //private
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_join_email = findViewById(R.id.edt_join_email);
        cb_all = findViewById(R.id.cb_all);
        cb_servie_use = findViewById(R.id.cb_servie_use);
        cb_service_in = findViewById(R.id.cb_service_in);
        cb_marketing = findViewById(R.id.cb_marketing);
        cb_ad = findViewById(R.id.cb_ad);
        btn_next = findViewById(R.id.btn_next);

        cb_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb_all.isChecked()){
                    cb_servie_use.setChecked(true);
                    cb_service_in.setChecked(true);
                    cb_marketing.setChecked(true);
                    cb_ad.setChecked(true);
                }
                else{
                    cb_servie_use.setChecked(false);
                    cb_service_in.setChecked(false);
                    cb_marketing.setChecked(false);
                    cb_ad.setChecked(false);

                }
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edt_join_email.getText().toString().trim().equals("")&&cb_servie_use.isChecked()==true&&cb_service_in.isChecked()==true){
                    btn_next.setEnabled(true);
                    sendRequest();
                    Intent intent = new Intent(getApplicationContext(), subscription_information.class);
                    String user_id = edt_join_email.getText().toString();

                    intent.putExtra("user_id", user_id);
                    startActivity(intent);
                }
                if(edt_join_email.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(),"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
                    btn_next.setEnabled(false);
                }

                if(cb_servie_use.isChecked()==false||cb_service_in.isChecked()==false){
                    Toast.makeText(getApplicationContext(),"'이거약 서비스 이용약관'과 '개인정보 수집 및 이용약관' 은 필수 선택사항입니다.",Toast.LENGTH_SHORT).show();
                    btn_next.setEnabled(false);
                }

            }
        });
    }

    public void sendRequest(){
        queue = Volley.newRequestQueue(this);
        String url = "http://59.0.249.28:300/EmailCheck";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response );
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject.getString("check");
                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    if(value.equals("true")){


                        Intent intent = new Intent(getApplicationContext(),subscription_information.class);
                        intent.putExtra("user_id",edt_join_email.getText().toString());

                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"아이디가 중복됩니다.",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "왜 안되요??????왜!!!", Toast.LENGTH_SHORT).show(); //error.toString()
                error.printStackTrace();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paramas = new HashMap<String, String>();
                paramas.put("user_id",edt_join_email.getText().toString()); //인텐트 할값
                return paramas;
            }
        };
        queue.add(stringRequest);
    }

}