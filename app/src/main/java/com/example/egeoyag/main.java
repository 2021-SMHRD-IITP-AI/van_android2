package com.example.egeoyag;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class main extends AppCompatActivity {

    private ListView l_lv;

    private ArrayList p_list;

    private Button m_btn_1, m_btn_2, btn_backg;
    private ImageButton m_submit, m_hambuger, m_ham_1, m_ham_2, m_ham_3;
    private EditText m_edt_s;
    private ToggleButton tb_1, tb_2, tb_3, tb_4, tb_5, tb_6, tb_7, tb_8, tb_9, tb_10, tb_11, tb_12, tb_13, tb_14, tb_15, tb_16;
    private ToggleButton tb_s_1, tb_s_2, tb_s_3, tb_s_4, tb_s_5, tb_s_6, tb_s_7, tb_s_8, tb_s_9, tb_s_10;
    private FloatingActionButton m_f_btn;
    private Thread t;
    private ConstraintLayout menu, l_hambuger, con_1, con_3;
    private PillListViewAdapter adapter = new PillListViewAdapter();
    int state_cnt = 0; // 색상/모양 버튼 누를 때 상태 파악

    String img_pill;
    Bitmap bitmap;
    String p_num;//약 일련번호 슬래시(/)로 구분해서 저장

    String inputValue;

    String[] getphillnum;
    String[] arr;
    String[] arr2;
    String[] p_data;
    String datas = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();


        m_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (state_cnt % 2 == 0) {
                    con_3.setVisibility((View.VISIBLE));
                    state_cnt++;
                } else {
                    con_3.setVisibility((View.INVISIBLE));
                    state_cnt++;
                }

            }
        });
        m_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (state_cnt % 2 == 0) {
                    con_3.setVisibility((View.VISIBLE));
                    state_cnt++;
                } else {
                    con_3.setVisibility((View.INVISIBLE));
                    state_cnt++;
                }

            }
        });


        tb_1.setOnClickListener(new View.OnClickListener() {//black
            @Override
            public void onClick(View v) {
                if (tb_1.isChecked()) {
                    tb_1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.black_p)
                    );
                } else {
                    tb_1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.black)
                    );
                }

            }
        });//black
        tb_2.setOnClickListener(new View.OnClickListener() {//black
            @Override
            public void onClick(View v) {
                if (tb_2.isChecked()) {
                    tb_2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.blue_p)
                    );
                } else {
                    tb_2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.blue)
                    );
                }

            }
        });//blue
        tb_3.setOnClickListener(new View.OnClickListener() {//black
            @Override
            public void onClick(View v) {
                if (tb_3.isChecked()) {
                    tb_3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.brown_p)
                    );
                } else {
                    tb_3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.brown)
                    );
                }

            }
        });//brown
        tb_4.setOnClickListener(new View.OnClickListener() {//black
            @Override
            public void onClick(View v) {
                if (tb_4.isChecked()) {
                    tb_4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.gray_p)
                    );
                } else {
                    tb_4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.gray)
                    );
                }

            }
        });//gray
        tb_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_5.isChecked()) {
                    tb_5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.green_p)
                    );
                } else {
                    tb_5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.green)
                    );
                }

            }
        });//green
        tb_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_6.isChecked()) {
                    tb_6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.indigo_p)
                    );
                } else {
                    tb_6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.indigo)
                    );
                }

            }
        });//indigo
        tb_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_5.isChecked()) {
                    tb_5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.light_green_p)
                    );
                } else {
                    tb_5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.light_green)
                    );
                }

            }
        });//light_green
        tb_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_8.isChecked()) {
                    tb_8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.light_purple_p)
                    );
                } else {
                    tb_8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.light_purple)
                    );
                }

            }
        });//light_purple
        tb_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_9.isChecked()) {
                    tb_9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.orange_p)
                    );
                } else {
                    tb_9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.orange)
                    );
                }

            }
        });//orange
        tb_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_10.isChecked()) {
                    tb_10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.pink_p)
                    );
                } else {
                    tb_10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.pink)
                    );
                }

            }
        });//pink
        tb_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_11.isChecked()) {
                    tb_11.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.purple_p)
                    );
                } else {
                    tb_11.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.purple)
                    );
                }

            }
        });//purple
        tb_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_12.isChecked()) {
                    tb_12.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.red_p)
                    );
                } else {
                    tb_12.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.red)
                    );
                }

            }
        });//red
        tb_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_13.isChecked()) {
                    tb_13.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.turqoise_p)
                    );
                } else {
                    tb_13.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.turquoise)
                    );
                }

            }
        });//turqoise
        tb_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_14.isChecked()) {
                    tb_14.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.white_p)
                    );
                } else {
                    tb_14.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.white)
                    );
                }

            }
        });//white
        tb_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_15.isChecked()) {
                    tb_15.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.yellow_p)
                    );
                } else {
                    tb_15.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.yellow)
                    );
                }

            }
        });//yellow
        tb_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_16.isChecked()) {
                    tb_16.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.transparency_p)
                    );
                } else {
                    tb_16.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.transparency)
                    );
                }

            }
        });//transparency


        tb_s_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_1.isChecked()) {
                    tb_s_1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.circle_p)
                    );
                } else {
                    tb_s_1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.circle)
                    );
                }
            }
        });//circle
        tb_s_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_2.isChecked()) {
                    tb_s_2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.dia_p)
                    );
                } else {
                    tb_s_2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.dia)
                    );
                }
            }
        });//dia
        tb_s_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_3.isChecked()) {
                    tb_s_3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.eliip_p)
                    );
                } else {
                    tb_s_3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.eliip)
                    );
                }
            }
        });//eliip
        tb_s_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_4.isChecked()) {
                    tb_s_4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.etc_p)
                    );
                } else {
                    tb_s_4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.etc)
                    );
                }
            }
        });//etc
        tb_s_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_5.isChecked()) {
                    tb_s_5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.hexa_p)
                    );
                } else {
                    tb_s_5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.hexa)
                    );
                }
            }
        });//hexa
        tb_s_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_6.isChecked()) {
                    tb_s_6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.circle_p)
                    );
                } else {
                    tb_s_6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.circle)
                    );
                }
            }
        });//oct
        tb_s_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_7.isChecked()) {
                    tb_s_7.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.oxt_p)
                    );
                } else {
                    tb_s_7.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.oxt)
                    );
                }
            }
        });//oxt
        tb_s_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_8.isChecked()) {
                    tb_s_8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.pen_p)
                    );
                } else {
                    tb_s_8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.pen)
                    );
                }
            }
        });//pen
        tb_s_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_9.isChecked()) {
                    tb_s_9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.semici_p)
                    );
                } else {
                    tb_s_9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.semici)
                    );
                }
            }
        });//semici
        tb_s_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb_s_10.isChecked()) {
                    tb_s_10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.tri_p)
                    );
                } else {
                    tb_s_10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.tri)
                    );
                }
            }
        });//tri


        m_f_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                inputValue = PreferenceManager.getString(getApplicationContext(),"info");


                if(inputValue.equals("")){
                    Intent intent = new Intent(getApplicationContext(), join.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"의약품을 검색해주세요.",Toast.LENGTH_SHORT).show();
                }

            }
        });


        m_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                con_1.setVisibility(View.GONE);

                t = new Thread(new main.MyThread());
                t.start();

                l_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bitmap_ListPillDTO dto = (Bitmap_ListPillDTO)parent.getItemAtPosition(position);


                        Intent intent = new Intent(getApplicationContext(), search_pill_explanation.class);
                        String intent_p_num = dto.getDto().getL_p_num();


                        intent.putExtra("intent_p_num", intent_p_num);


                        startActivity(intent);
                        if(intent!=null){
                            con_1.setVisibility(View.VISIBLE);
                            btn_backg.setVisibility(view.INVISIBLE);
                            l_hambuger.setVisibility(view.INVISIBLE);

                        }
                    }
                });

            }

        });


        m_hambuger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_backg.setVisibility(View.VISIBLE);
                l_hambuger.setVisibility(View.VISIBLE);

            }
        });

        btn_backg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l_hambuger.setVisibility(View.INVISIBLE);
                btn_backg.setVisibility(View.INVISIBLE);
            }
        });

        m_ham_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), user_record_list.class);
                startActivity(intent);
            }
        });
        m_ham_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), family.class);
                startActivity(intent2);
            }
        });
        m_ham_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), favorites.class);
                startActivity(intent3);
            }
        });


    }


    private void initial() {

        m_btn_1 = findViewById(R.id.m_btn_1);
        m_btn_2 = findViewById(R.id.m_btn_2);
        m_submit = findViewById(R.id.m_submit);
        m_edt_s = findViewById(R.id.m_edt_s);
        btn_backg = findViewById(R.id.btn_backg);

        m_hambuger = findViewById(R.id.m_hambuger);

        m_f_btn = findViewById(R.id.m_f_btn);


        m_ham_1 = findViewById(R.id.m_ham_1);
        m_ham_2 = findViewById(R.id.m_ham_2);
        m_ham_3 = findViewById(R.id.m_ham_3);

        l_hambuger = findViewById(R.id.l_hambuger);
        con_1 = findViewById(R.id.con_1);
        con_3 = findViewById(R.id.con_3);
        //l_ham = findViewById(R.id.l_ham);

        menu = findViewById(R.id.con_3);

        l_lv = findViewById(R.id.l_lv);


        tb_1 = findViewById(R.id.tb_1);
        tb_2 = findViewById(R.id.tb_2);
        tb_3 = findViewById(R.id.tb_3);
        tb_4 = findViewById(R.id.tb_4);
        tb_5 = findViewById(R.id.tb_5);
        tb_6 = findViewById(R.id.tb_6);
        tb_7 = findViewById(R.id.tb_7);
        tb_8 = findViewById(R.id.tb_8);
        tb_9 = findViewById(R.id.tb_9);
        tb_10 = findViewById(R.id.tb_10);
        tb_11 = findViewById(R.id.tb_11);
        tb_12 = findViewById(R.id.tb_12);
        tb_13 = findViewById(R.id.tb_13);
        tb_14 = findViewById(R.id.tb_14);
        tb_15 = findViewById(R.id.tb_15);
        tb_16 = findViewById(R.id.tb_16);

        tb_s_1 = findViewById(R.id.tb_s_1);
        tb_s_2 = findViewById(R.id.tb_s_2);
        tb_s_3 = findViewById(R.id.tb_s_3);
        tb_s_4 = findViewById(R.id.tb_s_4);
        tb_s_5 = findViewById(R.id.tb_s_5);
        tb_s_6 = findViewById(R.id.tb_s_6);
        tb_s_7 = findViewById(R.id.tb_s_7);
        tb_s_8 = findViewById(R.id.tb_s_8);
        tb_s_9 = findViewById(R.id.tb_s_9);
        tb_s_10 = findViewById(R.id.tb_s_10);
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                    adapter = new PillListViewAdapter();


                    p_num = getPhilNumber();

                    getphillnum = p_num.split("@");//일련번호 담아둠

                    ArrayList<String> test = new ArrayList<String>();
                    test.add("1234");

                    for (int i = 0; i < getphillnum.length; i++) {
                        test.add(getphillnum[i]);
                        Log.d("겟필넘확인", getphillnum[i]);
                    }

                    getphillnum = new String[test.size()];//0번째에 1234 값 추가
                    for (int i = 0; i < test.size(); i++) {
                        getphillnum[i] = test.get(i);
                    }

                        //if (getphillnum != null)

                if (getphillnum[0].equals("1234")) {//getphillnum[0]은 "1234"

                        for (int i = 1; i < getphillnum.length; i++) {

                            String txt = getPhillData1(getphillnum[i]); //getphillnum[i]의 정보를 txt에 저장(#으로 구분)
                            if(txt.equals("")){i--;}


                            if (i > 0) {
                                arr = txt.split("#");
                                if(arr.length > 4) {

                                    String l_p_num = arr[0];
                                    String l_p_name = arr[1];
                                    String l_p_company = arr[2];
                                    String l_p_img = arr[3];
                                    String l_p_eff1 = arr[4];

                                    img_pill = arr[3];

                                    //Log.d("약데이터", l_p_name + "/" + l_p_company + "/" + l_p_img + "/" + l_p_eff1);

                                    ListPillDTO dto = new ListPillDTO(l_p_img, l_p_name, l_p_company, l_p_eff1, l_p_num);

                                    DownloadFilesTask task = new DownloadFilesTask();
                                    task.setItem(dto);

                                    if(i == getphillnum.length-1){t.interrupt();Log.d("인터럽트","인터럽트");}

                                }

                            }

                        }//t.interrupt();Log.d("인터럽트","인터럽트");
                    }


            }
        }
    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {//리스트뷰 뿌려주는 핸들러
            Bitmap_ListPillDTO dto = (Bitmap_ListPillDTO) msg.obj;

            adapter.addItem(dto);
            l_lv.setAdapter(adapter);

           // Log.d("약약약","어뎁터 실행");
        }
    };


    private String getPhilNumber() {
        StringBuffer buffer = new StringBuffer();

        String serviceKey = "ieYMfcOiIIb28pQBQRdpDmTt4XPNvN5FCsff1zf6nEIDPpuDig2iHxcw%2B9N%2BCZNUB4tOg%2BavRPcIyi4s5HGL3A%3D%3D";
        String str = m_edt_s.getText().toString();
        String item_name = URLEncoder.encode(str);


        String queryUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?" +
                "ServiceKey=" + serviceKey + "&item_name=" + item_name;
        // +"&DRUG_SHAPE=" + p_shpape +"&COLOR_CLASS1=" + p_color;

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

                        if (tag.equals("ITEM_SEQ")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("@");
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
    }//의약품 일련번호 가져오기

    private String getPhillData1(String pnum) {//낱알 API 호출
        StringBuffer buffer = new StringBuffer();

        String serviceKey = "ieYMfcOiIIb28pQBQRdpDmTt4XPNvN5FCsff1zf6nEIDPpuDig2iHxcw%2B9N%2BCZNUB4tOg%2BavRPcIyi4s5HGL3A%3D%3D";
        String str = pnum;
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
                        } else if (tag.equals("ENTP_NAME")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        } else if (tag.equals("ITEM_IMAGE")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("#");
                        } else if (tag.equals("CLASS_NAME")) {
                            buffer.append("");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("");
                        } else if (tag.equals("ITEM_SEQ")) {
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

        //Log.d("약정보2", buffer.toString());

        return buffer.toString();//StringBuffer 문자열 객체 반환
    }//낱알 API 호출


    private class DownloadFilesTask extends AsyncTask {
        ListPillDTO dto;
        public void setItem(ListPillDTO dto){
            //Log.d("약약약","테스크 실행");
            this.dto = dto;
            execute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL(dto.getL_p_img());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bitmap = BitmapFactory.decodeStream(con.getInputStream());

                Bitmap_ListPillDTO dto = new Bitmap_ListPillDTO(this.dto, bitmap);

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


}//메인액티비티 닫는 괄호






