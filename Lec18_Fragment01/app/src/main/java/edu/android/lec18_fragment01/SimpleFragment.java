package edu.android.lec18_fragment01;


import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static edu.android.lec18_fragment01.MainActivity.TAG;

/*
* Fragment : UI의 일부 화면 조각을 담당
* */
/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    //화면을 돌렸을때 처음으로 돌아가는 걸 방지
    //Bundle객체에서 데이터를 읽거나 쓸때 사용할 키
    private  static final String STATE_TOGGLE = "bundle_toggle_state";
    //현재 어떤 문자열을 보여주고 있는지 저장하기 위한 변수
    private boolean toggle;


    private TextView textView;
    //TextView를 찾을 위치 : onStart

    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        // Fragment와 Fragment 사이에 데이터를 주고받을때 주로 사용하는 메소드
        //A라는 Fragment에 B라는 Fragment를 붙여주겠다
        //Context = 메인 Activity의 주소
        Log.i(TAG,"Fragment onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //Fragment의 여러가지 View듷이 만들어진다
        Log.i(TAG,"Fragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //초기화가 되느 과정 , 화면에 보임
        //inflater : xml파일에 있는 내용을 생성하는 역할(Button, TextView 등등..)

        Log.i(TAG,"Fragment on CreateView");
        //MainActivity 의 setContentView의 역할을 onCreateView가 한다
        //Container : xml 파일에 있는 버튼등과 같은 기능을 넣어주는 fragment의 메소드
        //마지막 기능에 true를 넣어버리면 프로그램이 죽는다
        return inflater.inflate(R.layout.fragment_simple, container,false);

    }

    //Activity의 onStart()가 될때 Fragment onActivityCreate, onStart

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"Fragment onActivityCreate");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        //화면에 모든 View들이 보이기 시작한 상태
        Log.i(TAG,"Fragment onStart");
        super.onStart();

        //Fragment에서 레이아웃 xml파이렝 있는 View들을 찾고, 이벤트 리스너를 등록할 떄에는 onStart()안에서 하는것을 권장
        // 이유: Activity으; onCreate() 실행 모두 끝난 후에 하는것이 좋다
        // 주의 : findViewBy() 메소드는 Acticity 클래스와  View클래스만 가시고 있다
        //Fragment 클래스는 finsViewBy() 메소드릃 가지고 있지 않음!!

        //
        View view = getView();
        textView = view.findViewById(R.id.textView);
        Button btn = view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = textView.getText().toString();
                if(toggle){
                    textView.setText("Hello Fragment:)");
                    toggle = false;
                    //-<toggle이 true이면 한글을 출력하고 toggle을 false로 바꿔준다
                }else{
                    textView.setText("안녕 프래그먼트:3");
                    toggle = true;
                    //toggle이 false이면 한들로 바꿔주고  맴버변수인 toggle을 true로 바꿔준다
                }
// => 이 코드 사용시 텍스트에서 값을 읽어오는 코드는 필요 없다
                //toggle값이 저장되어 있으면 되고 위의 코드는 [재사용]될 것이다

            }
        });
    }


    @Override
    public void onResume() {
        Log.i(TAG,"Fragment onResume");
        super.onResume();
    }

    //폰의 홈 버튼을 누르면 앱이 숨어 버리는 것이므로 Activity onPause,

    @Override
    public void onPause() {
        Log.i(TAG,"Fragment onPause");
        super.onPause();
    }

    //동작이 멈춘것이므로 Main Activity에서는 onStop()
    //여기서 Activity사 onStop(), onPause()이면 Fragment또한 onStop(), onPausr()으로 같다
    @Override
    public void onStop() {
        Log.i(TAG,"Fragment onStop");
        super.onStop();
    }

    //MainActivity에서 onDestroy일때
    @Override
    public void onDestroyView() {
        //소멸 될때
        Log.i(TAG,"Fragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"Fragment onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG,"Fragment onDetach");
        super.onDetach();
    }

    // 상태 저장할때
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG,"Fragment onSaveInstanceState");

        super.onSaveInstanceState(outState);
        // 화면이 변화할때 도는 안드로이드가 변화할때 뭔가 저장 할 게 있으면ㅇ onSaveInstanceState에 저장하면 된다
        outState.putBoolean(STATE_TOGGLE, toggle);
    }

    //상태 복원할때

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"Fragment onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
        //MainActivity에 같은 기능이 있으나 이르이나 매개변수가 약간 다르다
        //Create가 다시 실행될때 savedInstanceState르 부fmsek
        //toggle값 꺼내기
        if (savedInstanceState != null) {

            toggle = savedInstanceState.getBoolean(STATE_TOGGLE);
            textView = getView().findViewById(R.id.textView);
            if(toggle){
                //true인 경우
                textView.setText("안녕 프뤠그먼느 >W<");
            }else{
                textView.setText("Hello Fragment :))");
            }
        }

    }
}

//Button이 Fragment에 있는 경우 onClick속성을 사용 할 수 없으므로 익명 클래스를 이용해 setOnClick속성으로 사용 해 줘야 한다

//Fragment Class에서 Button속성을 찾을 수 있는데 그 위치는 Fragment의 onCreateView가 끝난 다음 찾아야 한다
//WHy? onCreateView에서 버튼과 같은 화면 속성을 담당하기 떄문에
//  +>>>onStart에서 이벤트 핸들러를 등록해 주는게 좋다고 하는데 그 이유 ::
//           화면에 Fragment가 여러개일 경우, 여러개의 Fragment가 전부 만들어지는 상태는 Create가 끝나고 Start를 한 뒤에 화면에 전부 보이기 때문에
//