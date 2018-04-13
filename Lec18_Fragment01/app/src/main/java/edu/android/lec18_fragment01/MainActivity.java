package edu.android.lec18_fragment01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {//1번으로 실행
        Log.i(TAG,"Activity onCreate");//로그 출력
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//화면의 view들을 실행

    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"Activity onRestart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        //앱이 종료된 상태에서 다시시작할때
        Log.i(TAG,"Activity onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"Activity onResume");
        super.onResume();
    }

    //앱이 정지할때(백키 눌렀다 다시 실행)

    @Override
    protected void onPause() {
        Log.i(TAG,"Acticity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"Avtivity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"Activity onDestroy");
        super.onDestroy();
    }
}
