package edu.android.lec09_masterdetail02;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.concurrent.ThreadPoolExecutor;

//폰에서 터치를 하면 ART가 MainActivity 찾고, onCreate로 이동 ->> 이 프로젝트에서는 Fregment만 보여주고
public class MainActivity extends AppCompatActivity
    implements ContactListFregment.ContactSelectedCallback{
    //ContactSelectedCallback를 구현하고 있다 -> 바디도 만들어 준다

// >>> 화면이 Fragment로만 이루어져 있는지(Single Pane >> 스마트폰),
//    화면이 Fragment와 FrameLayout으로 이루어져 있는지 (Two Pane >> 태블릿)
//    저장 할 수 있는 멤법 변수

    private boolean twopane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //layout아래에 있는 activity_main의 xml파일을 화면에 보여주기 위해 오버라이드 하는 업무
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 분석 할 Part>> 여기에 끼워넣으려는 ContecrLIstFragment가 무엇인지

        View view = findViewById(R.id.container);
        if(view != null){// FrameLayout이 있는 경우
            twopane = true;
        }else{//FrameLayout이 없는 경우
            twopane = false;
        }
    }


    @Override
    public void onContactSelected(int position) {
        if(twopane){// 태블릿인 경우 -> Fragment 교체
            //ContactDetailFragment를 생성해서 FrameLayout(id : Container)에 끼워넣음
            //생성자 호충 >> index 넣기 >>
           ContactDetailFragment fragment =
                    ContactDetailFragment.newInstance(position);

           //Fragment Manager 필요
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

            // OR
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction transaction = fm.beginTransaction();
//            transaction.replace(R.id.container, fragment);
//            transaction.commit();
//

        }else{// 스마트폰 -> Activity 실행
            //ContactListFragment가 보내준 position 정보를
            //ContactDetailActivity를 시작시키면서 다시 전달
//        Toast.makeText(this,"pos ::" + position,Toast.LENGTH_SHORT ).show();
            //여기서 startActivity를 할 것이다

            Intent intent = ContactDetailActivity.newIntent(this,position);
            startActivity(intent);
        }

    }
}
