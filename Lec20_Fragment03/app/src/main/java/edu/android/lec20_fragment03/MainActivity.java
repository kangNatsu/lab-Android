package edu.android.lec20_fragment03;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
            implements ToolBarFragment.ToolBarListener{


    public  static final String TAG = "edu.android";

   private  TextFragmnet textFragment;
    //ToolBarFragment.ToolBarListener  Interface
    @Override
    public void onChangeButtonClicked(String msg, int size) {
        Log.i(TAG,"msg = " + msg);
        Log.i(TAG, "size : " + size);
        if(textFragment != null){
            textFragment.changeTextView(msg,size);
            //textFragment는 textView를 변경 해 줄 수 있다

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FrameLayout(R.id.container)에 TextFragment 를 끼워 넣음(Hosting)
           // ->fragment가 있는 지 확인은 안해도 되지만 확인을 하고 끼워 넣겠다

        /// 1. fragment가 있는지 확인 ->>FragMentManager(support app)
        FragmentManager fm = getSupportFragmentManager();// ART이 가지고//2. Fragment 찾아보기
            // ->Fragment가 끼워있지 않다면 null 이 올 것 - 끼워져 있지 않은 경우에만 넣겠다
        Fragment fragment = fm.findFragmentById(R.id.container);
        if(fragment == null){
            //FrameLayout에 Fragment가 없는 경우에 Fragment 를 끼워 넣겠다(Hosting) ->>FragmentTransaction
            //    1) FragmentTransaction 시작
            FragmentTransaction transaction = fm.beginTransaction();
            //    2) fragment 를 replace 하기 위해서 fragment를 먼저 생성
            //       ->클래스 자체가 인스턴스를 생성하는 메소드를 가지고 있는지, 생성하는 메소드를따로 써야하는지 결정정
            textFragment = new TextFragmnet();

            //    3)fragment를 FrameLayout에 넣어준다 (replace 이용)
            transaction.replace(R.id.container, textFragment);
            //    4) fragment transaction을 종료(commit)
            transaction.commit();
        }

        //버튼을 눌렀을때 EditText, SinkBar의 정보를 밑의 EditText에 주고싶은 것


    }
}
