package edu.android.lec09_masterdetail02;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactDetailActivity extends AppCompatActivity {

    private static final String EXTRA_CONTACT_INDEX = "selected_contact_index";

    public static Intent newIntent(Context context, int index){
        //변수로 Context context, int index 이 둘이 필요한 이유는
        //시작시켜주는 activity의 주소가 context로, 시작하고 나서의 정보(position 정보)가 index로 들어감
        Intent intent = new Intent(context, ContactDetailActivity.class);//리턴타입이 Intent 이므로 선언 해야
        intent.putExtra(EXTRA_CONTACT_INDEX, index);
        return intent;
        //   >>>>MainActivity가 호출하려고 쓰는 클래스

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        //MainActivity가 보낸 Intent를 찾아서, Extra 데이터를 찾겠다
        Intent intent = getIntent();
        int index = intent.getIntExtra(EXTRA_CONTACT_INDEX/*Key값, putExtra의 변수와 같게*/,
                                        0/*int값을 찾지 못했을경우의 디폴트값*/);
        //position, index 꺼낸 이유 = Fregment에 주려고 >> 주지 않으면 정보를 꺼낼수가 없으므로

        //findFregmentById >>하려면 >> FregmentManager가 필요

        //ContactDetailActivity에 Attach된 ContactDetailFregment에게 index정보 전달
             //  >>Text에 글자를 넣고, 이미지를 넣고 등등의 일
        FragmentManager manager = getSupportFragmentManager();// >>findById하기 위해
         ContactDetailFragment fragmant = (ContactDetailFragment) manager.findFragmentById(R.id.detailFragment);
         fragmant.setIndex(index);

    }
}
