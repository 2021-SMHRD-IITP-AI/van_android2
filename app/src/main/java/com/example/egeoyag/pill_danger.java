package com.example.egeoyag;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class pill_danger extends AppCompatActivity {//해당 의약품의 주의사항 더보기 눌렀을 때 보이는 화면 의약품에 대한 상세상세 정보 출력

    private TextView pd_tv1, pd_tv2, pd_tv3, pd_tv4, pd_tv5;
    private Thread tt;
    private String[] arr;
    private Thread t;
    private String pd_pnum, pd_info1, pd_info2, pd_info3, pd_info4, pd_info5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_danger);

        initial();


        //전 화면(search_pill_explanation)에서 의약품 일련번호를 가져와 api에서 해당 의약품 정보 호출 후 출력
        Intent intent = getIntent();
        String intent_p_num = intent.getStringExtra("intent_p_num");
        pd_pnum = intent_p_num;

        t = new Thread(new MyThread3());
        t.start();


    }

    private void initial() {

        pd_tv1 = findViewById(R.id.pd_tv1);
        pd_tv2 = findViewById(R.id.pd_tv2);
        pd_tv3 = findViewById(R.id.pd_tv3);
        pd_tv4 = findViewById(R.id.pd_tv4);
        pd_tv5 = findViewById(R.id.pd_tv5);

    }

    public class MyThread3 implements Runnable{

        @Override
        public void run() {

            while(!Thread.currentThread().isInterrupted()){

                String pd_data = getPhillData(pd_pnum);
                arr = pd_data.split("#");
                for(int i = 0 ; i< arr.length ; i++){
                    Log.d("순서확인", arr[i]);//순서확인하고 정리하기
                }
                if(arr[0].equals("")){ //정보가 부족한 의약품 NULL 값 처리
                    pd_info1 = "정보가 부족합니다.";
                    pd_info2 = "정보가 부족합니다.";
                    pd_info3 = "정보가 부족합니다.";
                    pd_info4 = "정보가 부족합니다.";
                    pd_info5 = "정보가 부족합니다.";
                }else{
                    pd_info1 = arr[0];
                    pd_info2 = arr[4];
                    pd_info3 = arr[2];
                    pd_info4 = arr[1];
                    pd_info5 = arr[3];
                }


                pillDangerDTO pddto = new pillDangerDTO(pd_info1, pd_info2, pd_info3, pd_info4, pd_info5);



                Message message = myHandler.obtainMessage();
                message.obj = pddto;

                myHandler.sendMessage(message);

                t.interrupt();

            }
        }
    }

    private String getPhillData(String pnum) {//e약 API 호출
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

                        if (tag.equals("useMethodQesitm")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        } else if (tag.equals("depositMethodQesitm")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }else if (tag.equals("atpnQesitm")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }else if (tag.equals("atpnWarnQesitm")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        }else if (tag.equals("intrcQesitm")) {
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
    }//e약 API 호출

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pillDangerDTO pddto = (pillDangerDTO) msg.obj;

            pd_tv1.setText(pddto.getPd_info1());
            pd_tv2.setText(pddto.getPd_info2());
            pd_tv3.setText(pddto.getPd_info3());
            pd_tv4.setText(pddto.getPd_info4());
            pd_tv5.setText(pddto.getPd_info5());

        }
    };



}