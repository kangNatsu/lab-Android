package edu.android.lec04_lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Bundle객체에서 데이터를 저장하거나
    //Bundle객체에서 데이터를 읽어올 때 사용할 키
    public static final String STATE_INDEX = "current_index";

    //Log를 출력할때 사용할 태그
    public  static  final String TAG = "MainActivity";

    //이미지 리소스 아이디들을 저장하는 배열
    public static final int[] IMAGE_IDS ={
                                          R.drawable.n1,
                                          R.drawable.n2,
            R.drawable.n3,
            R.drawable.n4,
            R.drawable.n5
    };
    private static int currentIndex = 0;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "omCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageView 찾기
         iv = findViewById(R.id.imageView);
//         iv.setImageResource(R.drawable.n2); ->이벤트 핸들러 안에 넣을 것

        //NextButton찾기
        Button btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentIndex < IMAGE_IDS.length -1){
                    currentIndex++;
                    iv.setImageResource(IMAGE_IDS[currentIndex]);
                }else{
                    Toast.makeText(MainActivity.this, "Lst Image", Toast.LENGTH_SHORT).show();

                }

            }
        });



        Button btnprev = findViewById(R.id.btnprev);
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0){
                    currentIndex--;
                    iv.setImageResource(IMAGE_IDS[currentIndex]);

                }else{
                    Toast.makeText(MainActivity.this, "THe First Image", Toast.LENGTH_SHORT).show();
                }


            }
        });





    }

    @Override
    protected void onStart() {

        // onStart
        //Activity가 화면에 보이기 시작하는 상태, 포커스를 받고 있지 않아 이벤트 처리는 할 수 없는 상태
        Log.i(TAG, "onStart");

        super.onStart();//부모 클래스가 할 일은 하게끔 super는 지우면 안됨

    }

    @Override
    protected void onResume() {

        //onResume
        //Activity가 포커스를 받게되는 상태 - 이벤트를 처리 할 수 있는 상태
        Log.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {

        //onPause
        //Activity가 화면에는 보이지만, 포커스를 잃어버린 상태, 이벤트를 처리할 수 없는 상태
        Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {

        //onStop
        //Activity가 화면에서 사라지는 상태, Activity 인스턴스는 메모리에 남아있는 상태
        Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        //onDestroy
        //Activity가 사용하던 모든 리소스(메모리)가 해제, Activity 인스턴스가 메모리에서 해제
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {

        //onRestart
        //앱이 백그라운드(background)에서 포그라운드(foreground) 상태로 되돌아올때 호출되는 메소드
        //onStop -> onRestart -> onStart
        Log.i(TAG,"onRestart");
        super.onRestart();
    }

    //뭔가의 상태를 저장하고 싶을때

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //종료될때 Bundle 이라는 걸 줌

        //Activity가 종료될때 저장해야할 정보들이 있으면 ART(Android-RunTime)에게 정보를 저장하도록
        //할 수 있는 메소드
        Log.i(TAG,"onSaveInstanceState");
        super.onSaveInstanceState(outState);


        //Bundel 객체에 값을 저장 ->ART이 관리하게 됨
        outState.putInt(STATE_INDEX, currentIndex);



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //앱이 재시작 될때 ART이 저장하고 있던 데이터를 ACTIVITY에 전달하기 위해서 호출하는 메소드
        Log.i(TAG, "onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);

        //ART이 관리하고 있던 Bundle 객체에서 저장했던 값을 읽어옴
        currentIndex = savedInstanceState.getInt(STATE_INDEX);

        //읽어온 currentIndex를 사용해서 ImageView를 다시 그림
        iv.setImageResource(IMAGE_IDS[currentIndex]);

    }
}






















