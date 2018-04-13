package edu.android.lec39_network01;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";

    private EditText editText;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);


    }

    //버튼을 클릭 했을떄 EventHandler의 기능하는 메소드
    public void connectUrl(View view) {
        //ConnectivityManager (네트워크 연결이란 의미)를 ART에 요청 ->
        //인터넷을 사용할 수 있는 네트워크 자원(LTE, Wi-fi, ... )이 있는지 검사
        // 즉, ConnectivityManager 가 네트워크 자원이 있는지 검사하는 메소드를 가지고 있다


        // 1. ConnectivityManager (네트워크 연결이란 의미)를 ART에 요청
        ConnectivityManager connMgr = (ConnectivityManager)
                                        getSystemService(CONNECTIVITY_SERVICE);
        //컴파일 에러 나는 이유 >> 형변환 하라는 이유

        // 2.인터넷을 사용할 수 있는 네트워크 자원(LTE, Wi-fi, ... )이 있는지 검사
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isAvailable()){
            Log.i(TAG, "사용 가능 : " + networkInfo.getTypeName());

            //사용 가능한 네트워크가 있는 경우
            //EditText 에서 URL(문자열)을 읽고, URL 요청을 보내는 AsyncTask 를 실행한다
            String url = editText.getText().toString();

            UrlRequestTask task = new UrlRequestTask();
            task.execute(url/*params 부분의로 들어감*/); // AsyncTask 실행하는 부분
            // 여기서 넘기는 url이 doInBackground에 들어가고  그  url 이 params 가 된다

        }else{
            Toast.makeText(MainActivity.this,"사용 가능한 네트워크가 없습니다", Toast.LENGTH_SHORT).show();
        }


    }

    //AsyncTack를 상속받는 내부 클래스의 정의
    class UrlRequestTask extends AsyncTask<String, Void/*업대이트 할  게 없으면 */,String >{


        //작업 스레드(doInBackground)는 직접 TextView에 쓸 수 없기 때문에 리턴해서 문자열 호출을 가능

        @Override
        protected String doInBackground(String... params) {
           Log.i(TAG, "doInBackground : " + params[0]);

           String result = processUrl(params[0]);
           //URL 을 가지고 어떤 일을 한 후에 그 결과로 리턴을 한다
           return  result;


        }

        private String processUrl(String urlString) {
            StringBuilder builder = new StringBuilder();
            // 문자열들을 계속 업데이트 할 것이기 때문에 사용
            URL url = null;
            HttpURLConnection conn = null;
            InputStream in = null;
            InputStreamReader reader = null;
            BufferedReader br = null;

            try {
                //1. Url 객체를 생성
                url = new URL(urlString);

                //2;,Url 객체를 사용해서 HttpUrlConnextion 을 생성
                conn = (HttpURLConnection)url.openConnection();// 통로를 열겠다- 형변환도 해 줘야 한다

                //3. Connection 설정 - 선택
                conn.setConnectTimeout(10*100);
                conn. setReadTimeout(10*100);

                //요청방식(request method) 설정 - 필수
                conn.setRequestMethod("GET"); //  GET 방식인지 POST 방식 인지

                //연결 맺음
                conn.connect();

                // 서버에서 보내준 응답 코드(response code) 확인
                //    >> 서버에서 정상적으로 처리하고 보내주는 응답인지, 실행중 오류가 생겨 보내중 응답인지
                int resCode = conn.getResponseCode();
                if (resCode == HTTP_OK) { // 서버에서 정상적인 응답을 보내준 경우

                    in = conn.getInputStream();//  '읽어들이기 위한 통로' 만들어 연결
                    reader = new InputStreamReader(in); // 인코딩(UTF-8, MS, ..)되어 있을것이므로 속도 빨리 하기 위해
                    br = new BufferedReader(reader);

                    String line = br.readLine();
                    while (line != null) { // 한줄씩 읽어가며 그 한줄이 null 이 아닐 경우 라인에 더하고, 다음줄 읽고 ...(반복)
                        builder.append(line);
                        line = br.readLine();
                    }
                }


            }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {

                try {
                    br.close(); // BufferedReader 해제
                    conn.disconnect(); // Http Connection 해제
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            return  builder.toString(); // 문자열 리턴

        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG,"onPreExecute ");
            super.onPreExecute();

            textView.setText("");//텍스트 뷰 초기화
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(TAG, "onPostExecute");
            textView.setText(s);//doInBackground() 리턴한 문자열을 화면에 출력
        }
    }



}
