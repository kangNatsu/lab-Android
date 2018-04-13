package edu.android.lec02_simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //멤버 변수(필드)
    private  EditText edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //setContentView();
        //Layout 폴더 밑에 있는 activity_main.xml 파일을 찾아서
        //레이아웃 파일에 정의된 UI요소들을 내고, 화면에 출력
        //화면에 출력은 틀린 말 -> 정정)) setContentView 는 xml파일에 저장되어 있는 위젯들을 불러 생성자만 생성 하는데까지가 임무
        setContentView(R.layout.activity_main);

        // 1.레이아웃 파일에 정의된 TextView를 찾는다 -id를 이용해서 찾음
        final TextView text = findViewById(R.id.textView);
        //final 은 안드로이드 스튜디오가 자동으로 붙여준 것
        // 왜냐? 클래스 밖이 아니라 메소드 안에서 선언한 지역변수 이기 때문에 ->onCreate가 끝나면 사용 x


        //EditeText
        edit = findViewById(R.id.editText);

        //button읖 찾음
        Button btn = findViewById(R.id.button);

        //Buttond
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //버튼을 클릭 했을 때 Android run-Time(ART)이 호출하는 메소드
                        Toast.makeText(MainActivity.this , "버튼 클릭!" ,Toast.LENGTH_SHORT ).show();
                        //show가 없으면 메세지를 만들기까지만 한 것

                        //editText에 저장된 문자열을 읽어옴
                        String msg = edit.getText().toString();

                        //읽어본 문자열을 TextView에 씀
                        text.setText(msg);

                    }
                });





    }
}
