package edu.android.lec06_event;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    public static final String STATE_INDEX = "current_index";
    //위의 문자열을 이용해서 값을 읽어오고 값을 저장 할 거다

    //모델 클래스
    private Ball[] list = {
            new Ball(R.drawable.pr1, "BaseBall"),
            new Ball(R.drawable.pr2, "BasketBall"),
            new Ball(R.drawable.pr3, "BallyBall"),
            new Ball(R.drawable.pr4, "SoccerBall"),
            new Ball(R.drawable.pr5, "GolfBall")
    };

    private int currentIndex;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imageview);
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //void가 아니라 boolean인 이유/

                Toast.makeText(MainActivity.this, list[currentIndex].getName(), Toast.LENGTH_SHORT).show();

                return true;
                //false로 리턴- 꾹 눌렀다 떼면 공의 이미지가 바뀜. 이벤트 핸들러가 2개가 전부 실행
                //true로 이턴 - 길게 누르면 이름만 출력, 짤게 터치 할때만 이미지 변경, 하나의 이벤트를 처리 했으니 나머지 이벤트는
                //             실행되지 말아라
                //**리턴값의 의미 **
                //true : 이벤트 처리 완료 -> 다른 이벤트 핸들러는 동작하지 않음
                //false : 이벤트 처리를 완료하지 못함
                //->이벤트 처리 할 수 있는 다른 핸들러가 있다면, 다른 핸들러도 실행됨됨            }
            }
        });
    }


    public void changeImage(View view) {
        if (currentIndex < list.length - 1) {
            currentIndex++;
        } else {
            currentIndex = 0;
            //이미지를 반복할때마나 0.1.2.3.4를 반복
        }
        //1. 베열에서 공을 꺼내 보여준다
//        iv.setImageResource(list[currentIndex]);//여기까지는 Ball타입

        //2.iv.setImageResource(list[currentIndex].getImageId); 해야 끝

        iv.setImageResource(list[currentIndex].getImagedId());

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "OnSaveInstance");
        super.onSaveInstanceState(outState);

        //key값은 상수로 정의 -> 파이널로 정의한다
        //정의한 상수값을 준다

        outState.putInt(STATE_INDEX, currentIndex);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //저장되어 있는 번들에서 값을 꺼내면된다.
        super.onRestoreInstanceState(savedInstanceState);
        currentIndex = savedInstanceState.getInt(STATE_INDEX);
        iv.setImageResource(list[currentIndex].getImagedId());
    }
}
