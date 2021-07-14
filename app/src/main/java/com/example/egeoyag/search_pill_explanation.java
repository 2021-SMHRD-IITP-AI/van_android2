package com.example.egeoyag;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class search_pill_explanation extends AppCompatActivity {

    private pillExAdapter adapter = new pillExAdapter();


    private TextView spe_p_1, spe_p_2, spe_p_3, spe_p_4, spe_p_5, spe_p_6, spe_p_7;
    private ImageView spe_p_img;
    private String spe_p_num, p_data_not,p_data_not2, p_data_e, img_pill;
    private String[] arr;//낱
    private String[] arr2;//e
    private String[] arr3;//낱
    private String[] str_name;
    private Thread tt;
    private ListView spe_l;

    private ImageButton spe_btn_danger, spe_btn_record;

    private StringRequest stringRequest;
    private RequestQueue queue;


    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pill_explanation);

        initial();

        Intent intent = getIntent();
        String intent_p_num = intent.getStringExtra("intent_p_num");
        Log.d("인탠트", intent_p_num);

        spe_p_num = intent_p_num;
        tt = new Thread(new MyThread2());
        tt.start();


        spe_btn_danger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), pill_danger.class);

                intent.putExtra("intent_p_num", spe_p_num);



                startActivity(intent);

            }
        });

        spe_btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), user_record.class);

                intent.putExtra("intent_p_num", spe_p_num);
                intent.putExtra("intent_p_name", arr[0]);
                intent.putExtra("intent_p_company", arr[1]);
                intent.putExtra("intent_p_otcetc", arr[4]);
                intent.putExtra("intent_p_group", arr[3]);
                intent.putExtra("intent_p_img", arr[2]);

                sendRequest(); //서버로 약 정보 보내주기

                startActivity(intent);

            }
        });


    }

    public void sendRequest() {

        queue = Volley.newRequestQueue(this);



        String url = "http://59.0.249.28:3000/Drugs";
//        String url = "http://172.17.96.1:3000/Drugs";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//server 로부터 데이터를 받아온 곳


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String value = jsonObject.getString("check");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {//server 통신시 error 발생 하며 오는 곳
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace(); //로그에 에러가 난 것을 표시

            }
        }){
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

                Map<String, String> params = new HashMap<String, String>();

                if(arr2[0].equals("")){
                    arr2 = new String[3];
                    arr2[0] = "널";
                    arr2[1] = "널";
                    arr2[2] = "널";
                }

                params.put("drug_name",arr[0]);
                params.put("drug_num",spe_p_num);
                params.put("drug_company", arr[1]);
                params.put("drug_color", arr3[1]);
                params.put("drug_shape",arr3[0]);
                params.put("drug_img",arr[2]);
                params.put("drug_group", arr[3]);
                params.put("drug_effect", arr2[0]);
                params.put("drug_otcetc", arr[4]);
                params.put("drug_side_effect",arr2[2]);

                return params;//네트워크끼리 데이터를 server로 보낼 때 넣어주는 곳

            }
        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        queue.add(stringRequest);

    }//샌드 리퀘스트

    public class MyThread2 implements Runnable{
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){

                p_data_not = getPhillData1(spe_p_num);
                p_data_e = getPhillData2(spe_p_num);

                p_data_not2 = getPhillData3(spe_p_num);
                tt.interrupt();

//                Log.d("제발", p_data_not);
//                Log.d("제발2", p_data_e);

                arr = p_data_not.split("#");
                arr2 = p_data_e.split("#");
                arr3 = p_data_not2.split("#");

                Log.d("색깔", arr3[0] + arr3[1]);


                String spe_p_1 = arr[0];//약이름
                String spe_p_2 = arr[1];//약회사
                String spe_p_3 = arr[4];//전문/일반
                String spe_p_4 = arr[3];//분류명(기타의 소화기관용약, 해열제 ...)
                String spe_p_img = arr[2];//의약품 이미지

                String spe_p_5 = "정보가 부족합니다";//효능(이 약은 심근 경색, 뇌 경색)
                String spe_p_6 = "정보가 부족합니다";//부작용(쇽 증상 예 호흡곤란,...)
                String spe_p_7 = "정보가 부족합니다";//용법용량(성인은 1회 1정 ~

                if(!arr2[0].equals("")){
                    spe_p_5 = arr2[0];//효능(이 약은 심근 경색, 뇌 경색)
                    spe_p_6 = arr2[2];//부작용(쇽 증상 예 호흡곤란,...)
                    spe_p_7 = arr2[1];//용법용량(성인은 1회 1정 ~
                }


                pillExDTO dto = new pillExDTO(spe_p_1, spe_p_2, spe_p_3, spe_p_4, spe_p_5, spe_p_6, spe_p_7, spe_p_img);

                search_pill_explanation.DownloadFilesTask task = new search_pill_explanation.DownloadFilesTask();
                task.setItem(dto);

            }
        }
    }




    private void initial() {
        spe_p_1 = findViewById(R.id.spe_p_1);
        spe_p_2 = findViewById(R.id.spe_p_2);
        spe_p_3 = findViewById(R.id.spe_p_3);
        spe_p_4 = findViewById(R.id.spe_p_4);
        spe_p_5 = findViewById(R.id.spe_p_5);
        spe_p_6 = findViewById(R.id.spe_p_6);
        spe_p_7 = findViewById(R.id.spe_p_7);
        spe_p_img = findViewById(R.id.spe_p_img);

        spe_btn_danger = findViewById(R.id.spe_btn_danger);
        spe_btn_record = findViewById(R.id.spe_btn_record);

        spe_l = findViewById(R.id.spe_l);

    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {//리스트뷰 뿌려주는 핸들러
            Bitmap_pillExDTO dto = (Bitmap_pillExDTO) msg.obj;

            //adapter.addItem(dto);

            spe_p_1.setText(dto.getDto().getSpe_p_1());
            spe_p_2.setText(dto.getDto().getSpe_p_2());
            spe_p_3.setText(dto.getDto().getSpe_p_3());
            spe_p_4.setText(dto.getDto().getSpe_p_4());
            spe_p_5.setText(dto.getDto().getSpe_p_5());
            spe_p_6.setText(dto.getDto().getSpe_p_6());
            spe_p_7.setText(dto.getDto().getSpe_p_7());
            spe_p_img.setImageBitmap(dto.getBitmap());


            //spe_l.setAdapter(adapter);

            // Log.d("약약약","어뎁터 실행");
        }
    };

    private class DownloadFilesTask extends AsyncTask {
        pillExDTO dto;
        public void setItem(pillExDTO dto){
            //Log.d("약약약","테스크 실행");
            this.dto = dto;
            execute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL(dto.getSpe_p_img());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(con.getInputStream());

                Bitmap_pillExDTO dto = new Bitmap_pillExDTO(this.dto, bitmap);

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





    private String getPhillData1(String p) {//낱알 API 호출
        StringBuffer buffer = new StringBuffer();

        String serviceKey = "ieYMfcOiIIb28pQBQRdpDmTt4XPNvN5FCsff1zf6nEIDPpuDig2iHxcw%2B9N%2BCZNUB4tOg%2BavRPcIyi4s5HGL3A%3D%3D";
        String str = p;
        String item_num = URLEncoder.encode(str);


        String queryUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?" +
                "ServiceKey=" + serviceKey + "&item_seq=" + item_num;

        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;
            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//태그 이름 얻어오기

                        if (tag.equals("ITEM_NAME")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }else if (tag.equals("ENTP_NAME")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }else if (tag.equals("ITEM_IMAGE")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }
                        else if (tag.equals("ETC_OTC_NAME")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("");
                        }
                        else if (tag.equals("CLASS_NAME")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }

                        break;
                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if (tag.equals("item_name")) buffer.append("\n");
                        break;

                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }

        return buffer.toString();//StringBuffer 문자열 객체 반환
    }//낱알 API 호출

    private String getPhillData2(String pnum) {//낱알 API 호출
        StringBuffer buffer = new StringBuffer();

        String serviceKey = "ieYMfcOiIIb28pQBQRdpDmTt4XPNvN5FCsff1zf6nEIDPpuDig2iHxcw%2B9N%2BCZNUB4tOg%2BavRPcIyi4s5HGL3A%3D%3D";
        String str = pnum;
        String item_num = URLEncoder.encode(str);

        String queryUrl ="http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?" +
                "serviceKey=" +serviceKey + "&itemSeq=" + item_num;


        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;
            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//태그 이름 얻어오기

                        if (tag.equals("efcyQesitm")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        } else if (tag.equals("useMethodQesitm")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }else if (tag.equals("seQesitm")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if (tag.equals("item_name")) buffer.append("\n");
                        break;
                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }



        return buffer.toString();//StringBuffer 문자열 객체 반환
    }//e약 API 호출

    private String getPhillData3(String p) {//낱알 API 호출
        StringBuffer buffer = new StringBuffer();

        String serviceKey = "ieYMfcOiIIb28pQBQRdpDmTt4XPNvN5FCsff1zf6nEIDPpuDig2iHxcw%2B9N%2BCZNUB4tOg%2BavRPcIyi4s5HGL3A%3D%3D";
        String str = p;
        String item_num = URLEncoder.encode(str);


        String queryUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?" +
                "ServiceKey=" + serviceKey + "&item_seq=" + item_num;

        try {
            URL url = new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;
            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();//태그 이름 얻어오기

                        if (tag.equals("DRUG_SHAPE")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }else if (tag.equals("COLOR_CLASS1")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("");
                        }

                        break;
                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if (tag.equals("item_name")) buffer.append("\n");
                        break;

                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }

        return buffer.toString();//StringBuffer 문자열 객체 반환
    }//낱알 API 호출




}