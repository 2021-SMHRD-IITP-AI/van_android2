package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt_join_email.getText().toString().trim().equals("")){
                    btn_next.setEnabled(true);

                    sendRequest();
                }else {
                    Toast.makeText(getApplicationContext(),"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
                    btn_next.setEnabled(false);
                }
            }
        });
    }

    public void sendRequest(){
        queue = Volley.newRequestQueue(this);
        String url = "http://172.17.96.1:3000/EmailCheck";

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