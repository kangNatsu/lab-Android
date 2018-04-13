package edu.android.lec35_trhead2;

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
        //  ->스레드를 start()

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                //synchronized (this) >try구문 전체를 감싸도 ok, wait()만 감싸도 ok
                try {
                    synchronized (this){
                    wait(10*1000);// 10,000ms = 10초
                      // > 동기화 하지 않은채로 사용하면 앱이 죽어버린다
                    }
                    String text = textView.getText().toString();
                    if (text.equals(getResources().getString(R.string.msg01))){
                        textView.setText(R.string.msg02);
                    }else{
                        textView.setText(R.string.msg01);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        th.start();
    }

}
