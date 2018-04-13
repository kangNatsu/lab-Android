package edu.android.lec11_intent02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText edit2;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edit2 = findViewById(R.id.editText2);
        //MainActivity가 보낸 Intent 꺼내기
        Intent intent = getIntent();
        if(intent!= null){
            //확인 하는게 좋다. NUll 인 상태이면 MainActivity에서 문제가 생길 수 있다 -> 정보를 주고받는 것이기 때문에
            //MainActivity에서는 Null이 될수도 있고 아닐 수 있다

            //Intent에서 MainActivity가 보낸 msg 읽음
            String msg = intent.getStringExtra(MainActivity.KEY_MSG);
            edit2.setText(msg);//setText()를 했기 때문에 MainsActivity가 보낸 글자가 그대로 나타난다

        }

        btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity로 Intent를 보낸다
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);

                //MainActivity로 보낼 정보
                String msg = edit2.getText().toString();
                intent.putExtra(MainActivity.KEY_MSG, msg);

                startActivity(intent);


            }
        });
    }
}
