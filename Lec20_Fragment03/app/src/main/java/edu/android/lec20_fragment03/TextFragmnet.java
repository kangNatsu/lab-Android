package edu.android.lec20_fragment03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragmnet extends Fragment {


    public TextFragmnet() {
        // Required empty public constructor
    }

    //생성자를 생성할 수 있는 메소드 만들기 - 메인에서 써야한다(리턴타입 : TextFragment자체를 리턴해야한가)
    //  >> factory method : 인스턴스를 생성해서 리턴하는 메소드
    public static TextFragmnet newInstance(){
        TextFragmnet fragmnet = new TextFragmnet();
        return fragmnet;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_fragmnet, container, false);
        //inflatedml 의 리턴타입은 View
    }

    public void changeTextView(String msg, int size){
        //변수 선언
        View view = getView();// getView는 Fragment가 갖지고 있는 메소드
                               //onCreateView의 inflater의 리턴타입인 View
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(msg);
        textView.setTextSize(size);


    }

}
