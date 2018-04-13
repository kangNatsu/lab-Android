package edu.android.lec20_fragment03;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import static  edu.android.lec20_fragment03.MainActivity.TAG;
/**
 * EventHandler - setOnClickLister 사용해서 값 읽어옴( -> 읽은 정보를 TextFragment에 전달)
             >>주소 알고 있다면 바로 전달 해 주면 되지만 ToolBarFragment 가 TextFragment의 주소를 모른다
             >> 메인의 주소는 알고 있다 를 이용
 *
 */



/**
 * A simple {@link Fragment} subclass.
 */
public class ToolBarFragment extends Fragment {

    // Interface CallBack - 클래스 안의 내부 인터페이스 정의
    interface ToolBarListener{
        void onChangeButtonClicked(String msg, int size);
    }

    //멤버변수

    private ToolBarListener listener;//interface callback하면 자동으로 만들어 주는 코드
    private EditText editText;
    private SeekBar seekBar;
    private Button changeButton;

    public ToolBarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        // Activity와 Fragment가 결합될때 호출되는 메소드 -> Fragment가 Activity의 주소 가지고 있음

        //context : Attach만 가지고 있는 메소드, context가 MainActivity를(주소) 저장하고 있다
        //호출하면 메모리정보등을 넘겨주면 된다.
        super.onAttach(context);

        if(context instanceof ToolBarListener){
            listener = (ToolBarListener) context;
        }else {
           throw new RuntimeException("엑티비티는 반드시 ToolBarListner를 구현해야 한다");
           //Exception을 없애기 위해서는 MainActivity가 구현 해야함
        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //  ->아직 View가 만들어지지 않은 상태이므로 findViewById를 해도 Null이 나오므로 View가 생성 된 이루 찾아준다 <<onCreateView이후에 하는게 좋다
        //    >>onCreateView에서도 View가 만들어진 상태이지만 아직 전부 만들어지지 않았을 수 있으므로 Button의 EventHandler가 작동 안할수고 있다
        //    >>onStart에서 하는게 좋다
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tool_bar, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        //레이아웃에 있는 View 들을 찾고, 필요한 EventListener 를 등록
        View view = getView(); //  getView(); 는 onCreateView에서 inflate를 이용해 만든 View들을 리턴해준다( = xml의 자신이 만든 ID들)
        editText = view.findViewById(R.id.editText);
        seekBar = view.findViewById(R.id.seekBar);
        changeButton = view.findViewById(R.id.button);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인 엑티비티에게 EditText에 있는 문자열과 SeekBar에 설정된 값을 전달
                //MainActivity에 있는 메소드에 메소드를 넘겨준다
                String msg = editText.getText().toString();
                int size = seekBar.getProgress();
                listener.onChangeButtonClicked(msg, size);
                //onChangeButtonClicked는 MainActivity가 가지고 있어야 함 ->MainActivity의 주소는 Fragment의 onAttach

            }
        });
    }

    @Override
    public void onDetach() { //Activity 와 Fragment가 분리될때 호출되는 메소드, 따라서 Activity의 주소를 더이상 가지고 있으면 안된다
        super.onDetach();
        listener = null; //  멤버변수 값 다시 초기화
    }
}
