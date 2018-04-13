package edu.android.lec34_thread01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnChange, btnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnChange = findViewById(R.id.btnChangeText);
        btnToast = findViewById(R.id.btnToast);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"얍얍얍", Toast.LENGTH_SHORT).show();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeText();

            }
        });
    }

    private void changeText() {

        synchronized (this){
            try {
                wait(10*1000); //MainTrhead가 하는 일을 잠깐 멈추겠다
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //TextViwe에 쓰여진 문자열 읽음
        String text = textView.getText().toString();
        if(text.equals(getResources().getString(R.string.msg01))){
            textView.setText(R.string.msg02);
        }else{
            textView.setText(R.string.msg01);
        }
    }
}























