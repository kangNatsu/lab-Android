package edu.android.lec19_fragment02;


import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.BIND_DEBUG_UNBIND;
import static edu.android.lec19_fragment02.MainActivity.TAG;

//FactoryMethod : 똑같은 모양으로 Fragment를 만들어서 보내주겠다.

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */



public class SimpleFragment extends Fragment {

    private static final String BUNDLE_ARGS1 = "args1";
    //번들에서 데이터를 읽어올때 KEY로 쓰기 위해서

    private String msg;
    private TextView textView;

     //메인에서 생성자 불러들이면 된다
    public SimpleFragment() {
        // Required empty public constructor
    }

    //매개변수를 갖는 생성자를 만들거나 Settet메소드 이용

    //생성자를 매개변수 있는거 없는거 나눠서 만들 수 있지만 Fragment클래스는 매개변수가 없는것만 사용하도록
    //안드로이드 프레임워크가 막아두고 있다.
    //즉 디폴트 생성자를 만들어 두고 -> 메인에서 생성자 부르고 (객체, 인스턴스 생성)-> Fragment에서 Set사용해서 문자열을 바꿔준다

    public void setMsg(String msg){
        this.msg = msg;
    }

    /**
     *펙토리 메소드 : 생성자 대신에 인스턴스를 생성하고, 멤버변수들을 초기화 할 수 있는 public static 메소드
     * @return A new instance of fragment SimpleFragment.
     *
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * 액티비티 클래스가 프래그먼트를 생성할 떄,
     * 직접 생성자를 호출하고 setter 메소드를 호출하는 대신에
     * 사용하시 위한 메소드
     *
     */
    // TODO: Rename and change types and number of parameters
    //리턴타입 - SimplrFragment, 메소드 이름 - newInstance, 하는일 - 생성자 불러주는 일
    
    public static SimpleFragment newInstance(String param1) {//param1을 매개변수로
        SimpleFragment fragment = new SimpleFragment();//-Fragment 생성
//        fragment.msg = param1;


        //Activity가  Fragment Instance를 생성할때 보내중ㄹ 매개변수를
        //Fragment의 Arguments로 설정
        // -> onCreate() 메소드에서 Arguments를 꺼내자
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_ARGS1, param1);
        fragment.setArguments(bundle);//Bundle이 Fragment의 Arguments

        return fragment;//Fragment 리턴
        
        //=>> 마중에 맴버변수에 저장될 것들을 매개변수로 넣어준 것 (param1, param2)
        //만들어서 Bundle에 넣어줌
    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG,"Frag onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Frag onCreate");
        //Bundle을 꺼낸다(args)- 꺼내서 맴버변수에 저장
        super.onCreate(savedInstanceState);


        //Fragments의 생명주기(Life-Cycle)중 onCreate()에서
        //Factory Method가 설정한 Arguments의 데이터를 읽고, 멤버 변수로 설정
        Bundle bundle = getArguments();
        msg = bundle.getString(BUNDLE_ARGS1);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG,"Frag onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"Frag onActivityCreated");
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        Log.i(TAG,"Frag onStart");
        super.onStart();
        View view = getView();
        textView = view.findViewById(R.id.textView);
        textView.setText(msg);
    }

    //포커스를 화면에 줘서 이벤트 처리가 가능하도록

    @Override
    public void onResume() {
        Log.i(TAG,"Frag onResume");
        super.onResume();
    }

    //엑티비티가 종료가 될떄

    @Override
    public void onPause() {
        Log.i(TAG, "Frag omPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG,"Frag onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG,"Frag onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Frag onDes");
        super.onDestroy();
    }
}
