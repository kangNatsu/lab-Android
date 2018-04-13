package edu.android.lec14_implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 명시적 인텐트(Explicit Intent)
 *   ->실행시킬 Activity 클래스의 이름을 알고 있는 경우 생성하는 Intent
 *   -> new Intent(Context, class)
 *   -> Intent Project의 내용
 *
 * 암시적(암묵적) 인텐트(Implicit Intent)
 *   ->실행시킬 Activity 클래스의 이름을 모르는 경우 생성하는 Intent
 *   -> new Intent(Action, Uri)
 *       - ex) 전화를 걸겠다 Action에는 Dial이라는 전화거는 걸 넣고 Uri에는 전화번호부 Uri를 넣으면 된다
 *   ->Activity 클래스를 만들지 않고, 폰에 있는 어플을 사용하려고 할 떄 암시적 인텐트를 사용
 *
 */



public class MainActivity extends AppCompatActivity {

    private EditText editUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUri = findViewById(R.id.editText);

        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onCreat() 밖에서 작성해 불러오기
                showWebPage();//Alt + Enter

            }
        });
    }

    private void showWebPage() {
        //EditText에 입력된 Uri(웨 주소) 읽어야 함
        String url = editUri.getText().toString();
        //문자열을 Uri타입으로 변환을 해야 한다 - Intent에는 String이 아니라 Uri라는 타입으로 넣게 되어 있으므로
        //URI : 자바가 기본적으로 가지고 있는 클래스
        //Uri : 안드로이드가 가지고 있는 클래스
        Uri uri = Uri.parse(url);//읽어들인 문자열 url을 Uri.parse의 매개변수로 주기만 하면 된다

        //암시적 Intent를 생성
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        //바로 startActivity()를 해 버리면 웹브라우저는 열리지만 권장은 하지 않음
        //이름을 명시한것이 아니기 때문에 위의 Action을 처리할 수 있는 어플이 있는지 없는지 모르기 때문에
        //어플이 있는지 검사 하고 코드를 실행해라

        //암시적 Intetn를 실행 할 수 있는 어플이 있는지를 검사
        //검사 방법
        //resolveActivity() : Axtion을 실행 할 수 있는 Activity가 있는지 찾겠다 - 매개변수 : getPackageManager()
        if ( intent.resolveActivity(getPackageManager()) != null){
            //null이 오면 Action을 실핼할  Activity가 없으므로 실행 X

            //Intent 를 실행 할 수 있는 Activity(어플)을 찾은 경우
            startActivity(intent);
        }





    }
}
