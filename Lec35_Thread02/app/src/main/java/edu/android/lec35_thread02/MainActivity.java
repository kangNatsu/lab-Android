package edu.android.lec35_thread02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void showToastMessage(View view) {

        Toast.makeText(MainActivity.this, "쨔잔! 토스트M 이지롱", Toast.LENGTH_SHORT).show();

    }

    public void changeTextMessage(View view) {
        //Thread 생성 >> run() 메소드에서 10초 대기 후 TextView를 변경

    }
}
