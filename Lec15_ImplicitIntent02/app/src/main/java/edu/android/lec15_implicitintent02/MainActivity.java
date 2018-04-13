package edu.android.lec15_implicitintent02;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editPhoneN, editsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPhoneN = findViewById(R.id.editPhoneN);
        editsms = findViewById(R.id.editsms);



    }

    //xml 파일에서 설정한 전화걸기 버튼의 onClick Listener 메소드
    public void startDialActivity(View view) {
        //전화번호 읽기 - EditText에 입력죈 전화번호 문자열 읽음
        String phoneN = editPhoneN.getText().toString();
        //Uri로 바꿔 줘야 한다- 전화번호 문자열을 Uri 타입으로 변환 ->>Intent 생성할 때 사용
        Uri uri = Uri.parse("tel: " + phoneN);
        //그냥 phoneN를 넣는게 아니라 tel이란 걸 붙인 뒤 phoneN을 넣어줘야 한다

        //전화를 사용 할 수 있는 Intent를 생성
        //전화 사용할때의 Action : ACTION_DIAL
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        //ART가 DIAL이라는 걸 할 수 있는 Activity를 찾아줌 -> 그 이전에 Activity가 존재 하는지 찾아보고  start

        //Intent를 실행 할 수 있는 Activity(어플)이 있는지 검사
        if (intent.resolveActivity(getPackageManager()) != null){

        startActivity(intent);
    }
    }

    //xml파일에서 메세지 버튼에 설정한 onClick Listner 메소드
    public void startSmsActivity(View view) {

        String phoneN = editPhoneN.getText().toString();
        Uri uri = Uri.parse("smsto: " + phoneN);
        //문자 메세지 내용
        String msg = editsms.getText().toString();

        //전화번호 Uri를 가지고 Intent 생성
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);

        //문자 메세지 내용은 putExtra를 사용해서 저장하면 된다
        intent.putExtra("sms_body",msg);

        //SENDTO Action을 실행할 수 있는 Activity(어플) 이 있는지 검사
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }


    }

    //지도보기
    public void startMapActivity(View view) {
        Uri uri = Uri.parse("geo:37.499,127.031?z=18");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }

    }
}
