package edu.android.lec37_thread04;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InterruptedIOException;

public class MainActivity extends AppCompatActivity {


    private static final String KEY_MSG = "key_current_progress";

    //Handler 클래스를 상속받는 클래스를 정의하고 생성
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //TODO : Message에서 데이터를 읽어서 progressBar와 TextView를 업데이트

            Bundle data = msg.getData();
            int progress = data.getInt(KEY_MSG);
            progressBar.setProgress(progress);
            textView.setText(progress + "%");

        }
    };

    private ProgressBar progressBar;
    private TextView textView;
    private Thread proThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
    }

    public void startProgress(View view) {
        proThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    int progress = 0;

                    while (progress <= 100) {
                        //while 안에서 progress를 차례대로 증가시틴가 >> 100까지는 while안에.
                        // progress가 증가하다 100보다 커지는 순간 while문은 끝남.
                        Message msg = handler.obtainMessage();
                        Bundle data = new Bundle();
                        data.putInt(KEY_MSG, progress);
                        msg.setData(data);
                        handler.sendMessage(msg);

                        progress += 2;

                        synchronized (this) {
                            wait(100);
                        }

                        //Handler의 메세지를 사용해서 progress정보를 보냄
                        // 정보를 보냈으면 progress를 증가시키면 된다 >> 증가가 되면 잠시 wait

                    }//end while

                } catch (InterruptedException e) {
                    //TODO : 스레드를 interrrupt해서 종료시킬때
                }//end try



            }// end run()
        });
        proThread.start();

    }
    //화면을 돌렸을때 나는 오류 처리 - onSaved
    //스레드가 두개가 한꺼번에 작동이 안되도록 

    public void cancleProgress(View view) {
        //버튼 눌렀을때 interrupt해서 catch 끝내고 run()도 끝내고
        if( proThread != null ){
            proThread.interrupt();
        }
    }
    //setEnable 사요하면 버튼 클릭 안되는 상채
}
