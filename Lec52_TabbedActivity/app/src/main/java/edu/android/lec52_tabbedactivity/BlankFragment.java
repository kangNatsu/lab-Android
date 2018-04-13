package edu.android.lec52_tabbedactivity;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class Main{
    BlankFragment f = new BlankFragment();
//    f.setPosition(1);

}


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    private int position;
    public void setPosition(int position){this.position = position;}


    public BlankFragment() {
        // Required empty public constructor
    }


    //BlankFragment 인스턴스 생성
    public static BlankFragment newInstance(){
        BlankFragment blankFragment = new BlankFragment();

        return  blankFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
