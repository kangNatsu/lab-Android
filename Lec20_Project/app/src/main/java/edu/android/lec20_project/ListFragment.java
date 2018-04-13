package edu.android.lec20_project;


import android.content.Context;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.time.Instant;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    interface listSelectedListener{

        void clickSpinnerSelected(int position);
    }

    private Spinner spinner;
    private listSelectedListener listenern;


    private MainActivity main;

    public ListFragment() {
        // Required empty public constructor
    }

    public  static  ListFragment newInstance(){
        ListFragment list = new ListFragment();
        return list;

    }
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if(context instanceof listSelectedListener){
            listenern = (listSelectedListener) context;
        }else{
            //개발자가 실수로 개발을 안할 경우
            throw new RuntimeException("반드시 listSelectedListner를 구현해라");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        spinner = view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                                  int position, long id) {
                //MainActivit에게 position 정보를 넘겨줌
                // 1.MainActivity 주소 알기 -> onAttach 메소드
                // 2. 주소 넘겨주기
                listenern.clickSpinnerSelected(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
