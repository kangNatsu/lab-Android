package edu.android.lec10_intent01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //인텐트에 데이터를 저장하거나, 인텐드에서 데이터를 꺼낼 때 사용하게 될 키
    public static final String KEY_ID = "login_id";
    public static final String KEY_PW = "login_password";

    //멤버변수 정의 - findViewBy에서 찾기 위해 정의
    //View들을 찾는 건 onCreate -> setContentView(레이아웃 생성, 각종 컨텐트 표시) 이후 하는게 좋음
    private EditText editId;
    private EditText editPw;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId = findViewById(R.id.editId);
        editPw = findViewById(R.id.editPw);
        btnlogin = findViewById(R.id.btnLogin);

        //Button을 눌렀을때 LoginActivity를 실행
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent Instance를 생성 (intent에는 보내는 사람 주소, 받는 사랍 주소가 있어야 한다)
                //Context PackageContext, Class<?> cls을 생성자로 사용
                Intent intent = new Intent(MainActivity.this, LoginResultActivity.class);
                //보내는 사람 주소 + 이름 : MainActivity.this
                //받는 사람 주소 + 이름 : LoginResultActivity.class

                //Intent 안에 들어갈 수 있는 생성자
                //-intent O, String action, String Action Uri uri, Context PackageContext Class<?> cls,
                //String Action Uri uri Context PackageContext Class<?> cls, no parameters

                //인텐트에 ID,PW 정보를 저장
                //bundle에서는 메소드 이름을 다 다르게 했으나,  Intent에서는 메소드를 오버 로딩해서 사용
                String id = editId.getText().toString();
                String pw = editPw.getText().toString();
                intent.putExtra(KEY_ID, id);
                intent.putExtra(KEY_PW, pw);



                //startActivity() 호출할 떄  Intent 객체를 매개변수로 전달
                // ->Android Run-Time이 주소를 보며 onCreat를 차례대로 부름
                startActivity(intent);



            }
        });

    }
}
