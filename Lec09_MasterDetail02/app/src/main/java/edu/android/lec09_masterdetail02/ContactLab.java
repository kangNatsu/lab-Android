package edu.android.lec09_masterdetail02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-23.
 *
 * ---
 * 연락처(contact)의 리스트(List)를 관리하는 DAO CLASS
 *     >>>DB와 연결해서 정보 가져오는 일, Dummy데이터 만들어 내 ArrayList로 관리
 *     여럿 생성자에서 부르지X, 싱글톤 디자인 패턴 사용
 */

public class ContactLab {

    private static final int[] IMAGE_IDS = {
            R.drawable.android_1_5_cupcake, R.drawable.android_1_6_donut,R.drawable.android_2_0_eclair,
            R.drawable.android_2_2_froyo,R.drawable.android_2_3_ginerbread,R.drawable.android_3_0_honeycomb,
            R.drawable.android_4_0_icecreamsandwich,R.drawable.android_4_1_jellybean,R.drawable.android_4_4_kitkat,
            R.drawable.android_5_0_lollipop,R.drawable.android_6_0_marshmallow,R.drawable.android_7_0_nougat,
            R.drawable.android_8_0_oreo
    };

    private List<Contact> contactList = new ArrayList<>();
    //Contact의 ArrayList를 DAO클래스가 관리

    //싱글톤 디자인 패턴 적용
    private static ContactLab instance = null;

    private ContactLab() {
        //더미 데이터 만들기 가장 좋은 자리는 생성자 안
        makeDummyData();
    }

    private void makeDummyData() {
        for(int i =0; i<100 ;i++ ){
            Contact contact = new Contact(i,"Name :: " +i,
                                            "Phone :: " +i,
                                            "E-MAil :: " + i,
                                            IMAGE_IDS[ i % IMAGE_IDS.length] );
            contactList.add(contact);
        }
    }

    //ArrayList를 꺼내기 위한 getter 메소드
    //    >>> 멤버 변수를 리턴
    public List<Contact> getContactList(){
        return contactList;
    }



    //생성자 대신 인스턴스 생성할 수 있는 메소드 제공
    //   >>>만들어져 있으면 만들어져 있는거 리턴, 없으면 새로 만들어 리턴
    protected static ContactLab getInstance(){
        if (instance == null){
            instance = new ContactLab();
        }
        return instance;
    }


}
