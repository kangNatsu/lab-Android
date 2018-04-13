package edu.android.lec19_fragment02;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;
//하위버전 호환성 때문에 support 를 상속받아 사용한다

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";
    //Log Tag 부분에 선언한 상수 넣어준다

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Activity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FragMent (R.id.container)에 프래그먼트가 있는기를 검사
        //프래그먼크 매니저를 사용해서 프래그 먼트 찾을 수 있듬

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);
        if (fragment == null){
            //프레임 레이아웃 안에 프래그먼트가 없는 경우
            FragmentTransaction transaction = fm.beginTransaction();
            SimpleFragment frag = SimpleFragment.newInstance("할로할로");
//            SimpleFragment frag = new SimpleFragment();
//            frag.setMsg("헬로헬로");
            transaction.replace(R.id.container, frag);
            transaction.commit();

        }
          }

    //LifeCycle Override


    @Override
    protected void onStart() {//setText 하기 좋은 위치
        Log.i(TAG, "Activity onStart");
        super.onStart();
        //ReStart 는 Destroied 되지 않고 다시 Start로 올떄 사용->핸드폰의 Home 버튼 눌렀을때



    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"Activity onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"Activity onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"Activity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"Activity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"Activity onDesrtoy");
        super.onDestroy();
    }
}
