package edu.android.lec41_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    private EditText editText;
    private TextView textView;
    private RequestQueue queue;
    // queue 에 넣어주면 알아서  listener를 호출 해 주는데 이때 textview에 넣어주면도니다 - 버튼 눌었으때

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);


    }

    //버튼의 onClick Event Listener Method
    public void connectUrl(View view) {
        // 1>RequestQueue 인스턴스를 생성
         queue =
                 Volley.newRequestQueue/*RequestQueue를 생성하는 static 메소드 이용*/(this/*MainActivity 주소*/);


        // 2> 연결할 URL  주소 문자열
        //String url = editText.getText().toString();

        //  >> OpenApi, WebApi라고 한다다
       //String url = "http://openapi.seoul.go.kr:8088/sample/xml/StationAdresTelno/1/5/2/";
        String url = "http://openapi.seoul.go.kr:8088/sample/json/StationAdresTelno/1/5/2/";


        // 3> StringRequest 객체를 생성하면서, 요청(request)을 처리할 Listener 등록
        //    변수 선언 : StringRequest,
        StringRequest request = new StringRequest(
                                         Request.Method.GET,// 요청 방식(request method)
                                         url, // 요청 주소(reauest url)
                                         this, //  요청에 대한 정상 응답이 도착했을때 사용도리 주소
                                         this); // 요청에 대래서 에러가 도착했을때 사용도리 주고
          // 메게변수 타입 1> get 인지 host 타입인지
          // 요청 보낼 URl 주소
          // 리스너 등록 부분 (리스너 2개 등록해야함)

        // 4> 생성된 StringRequest 를 RequestQueue 에 등록
        queue.add(request);


    }

    //Response.listener 인터 페이스의 추상 메소드를 구현
    //서버에서 정상 응답(HTTP_OK)가 도착했을 때 volley 라이브러리에서
    //MainAvtivity(UI 스레드)로 정보를 전달하기 위해
    @Override
    public void onResponse(String response) {
        //서버에서 보내준 응답(response)을 TextView에 씀
        textView.setText(response);

    }

    //Response.ErrorListener 인터 페이스의 추상 메소드 구현
    //서버에서 에러 코드로 응답이 도착했을떄 Volley 라이브러리에서
    //MainAvtivity(UI Thread)로 정보를 전달할떄 호출하는 메소드
    @Override
    public void onErrorResponse(VolleyError error) {
        //서버에서 보낸 Error 내용을 TextView에 씀
        textView.setText("Volley Error : " + error.toString());

    }
}
