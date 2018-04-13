package edu.android.lec24_listview03;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-21.
 *
 * DAO Class
 *
 * 연락처(Contact)들의 리스트(ArrayList)를 관리하는 DAO Class
 */

public class ContactLab {

    private static final int[] IMAGE_IDS =  {
            R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9 };

    private List<Contact> contactList;

    //더미 데이터(가짜로 만들어둔)가 있으므로 그걸 이용  >> 생성자에서
    private static ContactLab instance = null;

    //private 생성자이기 때문에 다른 Class에서는 사용 불가
    private ContactLab(){
            contactList =new ArrayList<>();
        makeDummyData();
    }

    private void makeDummyData() {
          for(int i =0; i<100; i++) {
           Contact c = new Contact("NAME" +i, "PHONE" + i, "E-MAIL" + i ,
                                    IMAGE_IDS[i % IMAGE_IDS.length ]);
            //MAGE_IDS[i % IMAGE_IDS.length ] => %(나눈 나머지)
              contactList.add(c); //더미 데이터가 100개가 만들어 질 것이
          }
    }



    //외부 클래스에서 생성자가 private라 접근을 못하므로 public 메소드를 만들어 접근 가능하게 해 준다
    public static ContactLab getInstance(){
        //  >> instance가 없어도 생성자 생성 전에 부를 스 있어야 하기 때문에 static으로 선언
        if(instance == null){
            instance = new ContactLab();
            //instance가 만들어지지 않은 경우면 만들겠다
        }
        return instance;
        //instance가 이미 있으면 있는 값을 리턴하겠다다
    }

//getter 메소드가 있어야 다른 클래스에서 불러올 수 있음


    public List<Contact> getContactList() {
        return contactList;
    }

}
