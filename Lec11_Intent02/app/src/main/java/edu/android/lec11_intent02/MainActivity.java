package edu.android.lec11_intent02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //인텐트에서 사용할 key 문자열
    public static final String KEY_MSG = "extra_message";

    private EditText edit1;
    private Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //찾기
        edit1 = findViewById(R.id.editText1);
        btn1 = findViewById(R.id.button1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                //intent에 보낼 메세지를 읽기
                String msg = edit1.getText().toString();
                intent.putExtra(KEY_MSG, msg);

                startActivity(intent);


            }
        });

        //SecondActivity가 MainActivityf를 실행 시켰다면 Intent가 존재한다.
        //만약 SecondActivity가 아니라 런처가 실행시켰다면 Intent가 없을 것이다
        //따라서 Intent를 실행시티되 반드시 Null 를 해야한다
        Intent intent = getIntent();
        if(intent != null){
            String msg = intent.getStringExtra(KEY_MSG);
            edit1.setText(msg);
        }

    }
}
