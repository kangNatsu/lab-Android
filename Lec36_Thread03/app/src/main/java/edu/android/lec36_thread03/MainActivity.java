package edu.android.lec36_thread03;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MSG = "key_current_time";
    //Handler를 상속받는 클래스를 작성
    private Handler handler = new Handler(){
        //Handler를 통해 보내진 메소드를 Main이 어찌 처리 할 것이진 >> Override가 필요 >> 익명클래스 하나 필요
        //Ctrl + O => override 할 수 있는 메소드


        @Override
        public void handleMessage(Message msg) {
            //handleMessage :: 스레드가 메세지를 보내면 루퍼(Looper)는 메세지를 UIThread에 꺼내주면서
            //                 handleMessage를 호출>>

            Bundle data = msg.getData();
            String curTime = data.getString(KEY_MSG);
            textView.setText(curTime);

            //타이머 스레드가 보내준 메세지들(msg) 확인해서 UI를 업데이트
            // 할일 1) handler 메소드가 가지고 있는

        }
    };// 익명클래스




    private TextView textView;
    private Thread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void showToastMessage(View view) {

        Toast.makeText(MainActivity.this, "보이나? Toast 메세지가!", Toast.LENGTH_SHORT).show();

    }

    public void startTimmer(View view) {
        //Thread 생성
        // -> run() 메소드에서 무한루프
        // -> 무한루프 안에서 1초에 한번씩 TextView에 현재 시간을 씀
        //  Thread 시작(start();)

        th = new Thread(new Runnable() {// n스레드 생성
            @Override
            public void run() {

                // 무한루프 만들고 1초에 한번씩 Text 쓰게 만들기
                 while (true){
                     try{
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd || hh : mm : ss");
                     String curTime = sdf.format(new Date());


                     //textView.setText(curTime);
                     // 메인 스레드 (UI Thread, 즉 MainActivity)가 아닌 다른 스레드에서
                     // UI를 변경하려고 하면(TextView 변경, ImageView 변경, ....) ->
                     //  ART는 APP을 강제 종료 시켜버린다
                         // >> 스레드는 시간이 오래 걸리는 작업을 보통 만드는데 UI를 메인이 아닌 스레드가 잡고 있어 버리면
                         //    다른  기능이 수행이 안되기 때문에 ART가 강제로 종료시켜 버린다
                         // >>>>메인 스레드가 아닌 별도의 스레드 에서는  메인 에게 변경할 UI 내용의 Handler
                         //     클래스를 사용해서 메세지를 보내며 된다(직접 UI를 수정하지 말고!)

                     //TextView 무언가 쓰려는 시점에서 에러가 나 Start버튼을 누르면 앱이 죽는다
                         // >>>Only the original thread that created a view hierarchy can touch its views.
                         //  >> View 계층구조를 만들어 준 오리지널 스레즈만이 UI변경이 가능하다
                         //  >> 즉 Activity가 아닌 스레드에서는 변경해서는 안된다.

                         //1) Handler 객체에서 Message라는 객체를 가져옴
                          Message msg = handler.obtainMessage();
                                  //obtainMessage() >> obtain : 가져오다, 즉 메세지를 가져오겠다

                         //2) 메세지 객체에 데이터를 저장하기 위한 Bundle 객체를 생성
                         Bundle data = new Bundle();
                         //번들에 key, values 쌍으로 데이터를 저장 하고

                         //3) Bundle 객체에 보낼 데이터를 저장
                         data.putString(KEY_MSG, curTime);
                         //key가 필요한 이유 >> 나중에 KEY를 가지고 값을 꺼내기 위해

                         // 4) Bundle에 저장한 데이터를 Message 객체에 넣어줌
                         msg.setData(data);

                         //5) Handler 객체를 이용해서 메세지 보냄
                         handler.sendMessage(msg);
                         // Message 에 날짜 정보 데이터를 세팅해서 보내고 싶음



                     synchronized (this) {
                         wait(1000);
                     }
                         }catch(InterruptedException e){

                         Message msg = handler.obtainMessage();
                         Bundle data = new Bundle();
                         data.putString(KEY_MSG, "타이머 종료~~");
                         msg.setData(data);
                         handler.sendMessage(msg);
                         break;
                         }
                     }

                 }//무한 루프

        });
        th.start(); // 스레드 시작

    }

    public void stopTimmer(View view) {
        //생성된 타이머 스레드를 종료(interrupt)
        //   ※
        if(th != null){
            th.interrupt();
        }



    }
}
