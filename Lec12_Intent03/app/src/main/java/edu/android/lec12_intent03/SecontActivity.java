package edu.android.lec12_intent03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecontActivity extends AppCompatActivity {

    private EditText edit2;
    private Button btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secont);

        edit2 = findViewById(R.id.editText2);
        btn2 = findViewById(R.id.button2);

        //Intent를 찾는다
        final Intent intent = getIntent();
        if(intent != null){
            String msg = intent.getStringExtra(MainActivity.KEY_MSG);
            //key값만 주면 되는데 MainActivity에 정의되어 있다
            edit2.setText(msg);
            //-------MainActivity에 입력한 문자열이 SecondActivity에서 보이도록 ----
        }

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼을 눌렀을때
                //1. EditText에 입력된 문자열을 읽는다
                String msg = edit2.getText().toString();

                //MainActivity로 보낼 Result Data를 만들어 준다
                //((Activity와  Activity 사이에 데이터를 전달 할 수 있는 건 Intend 밖에 없다
                Intent data = new Intent();
                data.putExtra(MainActivity.KEY_MSG,msg);

                //3. MainActivity호 보낼 결과(Result) 세팅
                setResult(RESULT_OK, data);

                //4. SecondActivity 종료(finish)
                finish();


            }
        });

    }
}
