package edu.android.lec10_intent01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginResultActivity extends AppCompatActivity {

    //로그인 정보를 출력 할 TextView
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);

        textResult = findViewById(R.id.textResult);

        //ART 이 보내준 Intent 객체를 구함
        Intent intent = getIntent();



        //Intent에 저장된 데이터 (id,pw)를 읽음
        //넣을때는 putExtra 하나이면 되지만 읽어올때는 getExtra의 종류가 용도에 따라 다르게 나뉘어 있다
        String id = intent.getStringExtra(MainActivity.KEY_ID);//아이디는 문자열이므로 getStringExtre()사용
        //넣을때 KEY_ID로 넣었으므로 꺼낼때로 KEY_ID로 꺼내면 된다
         String pw = intent.getStringExtra(MainActivity.KEY_PW);

         String msg = String.format("아이디 : %s\n 비번 : %s", id, pw);
         textResult.setText(msg);

    }
}
