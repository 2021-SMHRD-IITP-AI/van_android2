package com.example.egeoyag;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class my_page extends AppCompatActivity {

    private ListView my_listview;
    private TextView tv_name1,tv_mo,tv_name2, tv_ymd2,tv_2;
    private ImageView img_ar, pl_img2;
    private Bitmap bitmap;
    private mypageadapter adapter;

    private RequestQueue queue;
    private StringRequest stringRequest;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);


        tv_mo = findViewById(R.id.tv_mo);
        tv_name1 = findViewById(R.id.tv_name1);
        pl_img2 = findViewById(R.id.pl_img2);
        tv_name2 = findViewById(R.id.tv_name2);
        tv_2 = findViewById(R.id.tv_2);
        tv_ymd2 = findViewById(R.id.tv_ymd2);
        img_ar = findViewById(R.id.img_ar);
//
//        sendRequest();
//
        my_listview = findViewById(R.id.my_listview);


//        adapter = new listviewadapter();
//
//        pillListView.setAdapter(adapter);

        //sendRequest();


    }


//    public void sendRequest(){
//        adapter = new mypageadapter();
//        //adapter.additem("img","name","symptom1","ymd");
//
//        queue = Volley.newRequestQueue(this);
//        String url = "http://59.0.249.28:3000/RecordList";
//        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //Server로 부터 데이터를 받아온 곳
//                Log.v("성공",response);
//                try {
//                    JSONArray array = new JSONArray(response);
//                    for(int i = 0; i < array.length();i++){
//                        JSONObject jsonObject = array.getJSONObject(i);
//
//
//                        String img2 = jsonObject.getString("drug_img");
//                        String name2 = jsonObject.getString("drug_name");
//                        String symptom = jsonObject.getString("drug_group");
//                        String ymd2 = jsonObject.getString("my_date");
//
//                        Log.d("성공 제이슨 값 받아오기", img2 + name2 + symptom2 + ymd2 + "성공?");
//
//
//                        ListViewItem dto = new ListViewItem(img2, name2, symptom2, ymd2);
//
//                        my_page.DownloadFilesTask task = new my_page.DownloadFilesTask();
//                        task.setItem(dto);
//
//
//                    }
//                    my_listview.setAdapter(adapter);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v("1", "user_id");
//                //Server 통신시 Error발생하면 오는 곳
//                error.printStackTrace();
//            }
//        }){
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                //Server로 데이터를 보낼 시 넣어주는 곳
//                Map<String,String> params = new HashMap<String,String>();
//                // String info = PreferenceManager.getString(getApplicationContext(),"info");
//
//                // Log.v("성공",info);
//                params.put("user_id","1");
//               // Log.v("성공1",edt_input.getText().toString());
//                //return super.getParams();
//                return params;
//            }
//        };
//        queue.add(stringRequest);
//
//








    private class DownloadFilesTask extends AsyncTask {
        mypagedto dto1;
        public void setItem(mypagedto dto1){
            //Log.d("약약약","테스크 실행");
            this.dto1 = dto1;
            execute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL(dto1.getImg2());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(con.getInputStream());

                //여기 피나서 우선 주석 1      Bitmap_ListViewItem dto1 = new Bitmap_ListViewItem(this.dto1, bitmap);

                Message message = myHandler.obtainMessage();
                message.obj = dto1;

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

//            adapter.additem(dto);
//            pillListView.setAdapter(adapter);

            // Log.d("성공 로그찍기", dto.getDto().getName() +"/" + dto.getDto().getName() + "/"+dto.getDto().getSymptom1()+"/"+dto.getDto().getYmd());
//
//            pl_img.setImageBitmap(dto.getBitmap());
//            tv_name.setText(dto.getDto().getName());
//            tv_1.setText(dto.getDto().getSymptom1());
//            tv_ymd.setText(dto.getDto().getYmd());





            // 여기 피나서 우선 주석 2   adapter.additem(dto);


        }
    };

}