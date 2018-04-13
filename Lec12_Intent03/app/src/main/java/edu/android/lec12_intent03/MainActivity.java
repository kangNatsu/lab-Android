package edu.android.lec12_intent03;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    public static final String KEY_MSG = "extra_message";
    public static final int REQ_CODE = 100;//상호간의 약속하기 위한 숫자. 큰 의미 X

    private EditText edit1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.editText1);
        btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecontActivity.class);
                String msg = edit1.getText().toString();
                intent.putExtra(KEY_MSG,msg);

                startActivityForResult(intent, REQ_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE && resultCode == RESULT_OK){
            String msg = data.getStringExtra(KEY_MSG);
            edit1.setText(msg);
        }
    }
}
