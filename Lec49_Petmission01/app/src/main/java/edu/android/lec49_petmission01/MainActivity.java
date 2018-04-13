package edu.android.lec49_petmission01;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText edittleNo, editSmsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittleNo = findViewById(R.id.editTellNo);
        editSmsText = findViewById(R.id.editSmsText);

    }

    public void useIntent(View view) {
        //폰에 있는 SMS를 사용할 수 있는 Native App을 사용해서 SMS보내기
        //  -> AndroidManifast.xml 파일에서 권한을 요처할 필요가 있음
        //  -> 암시적인 인텐트(implicit intent)를 사용

        String telNo = edittleNo.getText().toString();
        Uri uri = Uri.parse("smsto:"/*프록토콜 이름 : 어떤 기능을 사용 할 것인가에 대한 이름이므로 바꾸면 X*/ +telNo);
        Intent intent = new Intent(Intent.ACTION_SENDTO/*~에게 보내 주겠다 는 의미*/, uri/*SMS를 보낼 전화번호*/); // 암시적 인텐트
        //  >> 암시적 인텐트 :: 클래스 이름을 모를 경우
        //  ACTION 의 기능 : 은 ~ 을 하겠다. ex) 지도 보겠다 ACTION_MAP, 전화 걸겠다 ACTION_DIAL

        String smsText = editSmsText.getText().toString();
        intent.putExtra("sms_body", smsText);

        //SMS를 보낼 수 있는 앱이 있는지 없는 지 확인
        if(intent.resolveActivity(getPackageManager()) != null){
             // >>암시적 인텐트. 활동 할 수 있는지 없는지 찾아주세요(찾아달라는 위치)
            startActivity(intent);
        }else {
            //null 인경우 >> 사용자에게 알려준다
            Toast.makeText(this, "SMS 사용 가능한 앱이 없습니다", Toast.LENGTH_SHORT).show();

        }


    }

    public void useSmsMethod(View view) {

        //안드로이드 플랫폼에서 제공하는 SMS 메소드를 직접 사용해서 SMS 보내기
        //  -> 권한 요처이 해야만 SMS 메소드를 사용 할 수 있음(앱을 직접 사용하는 경우 이므로)
        //  -> 권한 요청이 없을 시 앱은 죽어버림

        String telNo = edittleNo.getText().toString();//전화번호 읽음
        String smsText = editSmsText.getText().toString();//적은 메세지 내용 읽어 들이기

        // 안드로이드 OS 상에서 실행중인 SmsManager를 가져옴
        SmsManager smsManager = SmsManager.getDefault();

        //SmsManager가 가지고 있는 send...()을 이용해서 SMS  보냄
        smsManager.sendTextMessage(telNo,     /*받는 사람 전화번호*/
                                   null,   /*보내는 사람 전화번호, null을 넣으면 본인 번호가 자동으로 들어감*/
                                   smsText ,    /*SMS 보낼 내용*/
                                   null,
                                   null);
        //Exception 이 발생하지 않은건 성공 한 것.
        Toast.makeText(this,"SMS 보내기 성공 ", Toast.LENGTH_SHORT).show();

//        SecurityException: Sending SMS message: uid 10108 does not have android.permission.SEND_SMS.
 //        >> 보안이라는 Permission 이 없기때문에 보안Exception이 발생 했다 - 민감한 기능을 권한도 없이 사용 했다
//          >> menifast 에서 권한요청


        //8.0에서만! 메세지 보내는 권한과  핸드폰의 상태를 읽는 권한까지 요청을 한다 >>READ_PHONE_STATE.
    }
}























