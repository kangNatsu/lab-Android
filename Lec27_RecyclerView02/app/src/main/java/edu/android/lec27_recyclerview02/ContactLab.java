package edu.android.lec27_recyclerview02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-22.
 *
 *Contact의 List를 관리하는 DAO Class
 */

public class ContactLab {

    private static final int[] IMAGE_IDS = {
            R.drawable.android1, R.drawable.android2, R.drawable.android3,
            R.drawable.android4, R.drawable.android5, R.drawable.android6,R.drawable.android7,
            R.drawable.android8, R.drawable.android_1_5_cupcake,R.drawable.android_1_6_donut,
            R.drawable.android_2_0_eclair,R.drawable.android_2_2_froyo,R.drawable.android_2_3_ginerbread,
            R.drawable.android_3_0_honeycomb,R.drawable.android_4_0_icecreamsandwich,
            R.drawable.android_4_1_jellybean,R.drawable.android_4_4_kitkat,R.drawable.android_5_0_lollipop,
            R.drawable.android_6_0_marshmallow,R.drawable.android_7_0_nougat,R.drawable.android_8_0_oreo
    };


    private List<Contact> contactList;

    public List<Contact> getContactList() {
        return contactList;
    }


    private  static  ContactLab instance = null;

    //다른 클래스에서 생성자 호출 못하도록 private로 만들기
    private ContactLab(){
        contactList = new ArrayList<>();
        makeDummyData();
    }

    private void makeDummyData() {
        for(int i = 0; i<100; i++){
            Contact contact = new Contact(i, "Name : " + i,"Phone : " + i,
                                          "E-Mail : " + i, IMAGE_IDS[i % IMAGE_IDS.length]);
            contactList.add(contact);
        }
    }

    //private로 하면 아무도 생성자 사용을 못하므로 static메소드가 필요
    public  static  ContactLab getInstance(){
        if(instance == null){
            instance = new ContactLab();
        }
        return instance;
    }





}
