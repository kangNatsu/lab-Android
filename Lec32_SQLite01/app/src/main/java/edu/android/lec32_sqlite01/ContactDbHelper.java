package edu.android.lec32_sqlite01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static edu.android.lec32_sqlite01.MainActivity.TAG;

import static edu.android.lec32_sqlite01.Contact.ContactEntity.*;
//ContactEntity 밑에 있는 상수 클래스들을 정부 import하겠다
//

/**
 * Created by user on 2018-03-27.
 *
 * --
 * SQLiteOpeHelper 클래스를 상속받는 클래스
 *  >> 1) 추상 메소드(onCreate, onUpgrade)를 구현
 *     2) 생성자를 작성 - 부모 클래스의 매개변수가 있는 생성자를 직접 호출
 * 데이터 베이스를 생성(Create), 관리(upgrade, downgrade)하는 역할
 *
 */
//  >> 1.오류 이유: 메소드를 구현 해라 -implements method
//              우리가 상속 받으려는 클래스가 추상 클래스이기 때문에
//     2. 생성자를 만들어라 -> 부모 클래스가 기본 생성자가 없기 때문에 만들어 줘야 오류가 없어진다
public class ContactDbHelper /*상속받겠다*/extends SQLiteOpenHelper{

    //상수 정의
    private static final String DB_NAME = "contact.db";// DB 이름
    private static final int DB_VERSION = 1; //DB 버전 정보

    //테이블 만드는 SQL문장 구조
    //create table contacts(
    //  _id integer primary key autoincrement.
    //  cname text,
    //  phone text,
    //  email text)
    private static final String CREATE_CONTACT_TABLE =
                               "create table " + TABLE_NAME//import가 되어 있어 간단히 쓴 코드
                               + " ("
                               + _ID + " integer primary key autoincrement, "
                               + COL_CNAME + " text, "
                               + COL_PHONE + " text, "
                               + COL_EMAIL + " text"
                               +")";


//                                 "create table" + Contact.ContactEntity.TABLE_NAME;
//                                 //import static edu.android.lec32_sqlite01.Contact.ContactEntity.*;가 없을경우 쓰는 코드


    // 생성자
    public ContactDbHelper(Context context) {
      //MainActivity가 context만 남겨주면 준 MainActivity테이블 이름을 쓰고 버전만 줄 것이므로 다른 값은 필요가 없다.
        super(context, DB_NAME, null, 1);
        Log.i(TAG,"ContactDbHelper 생성자");
    }

    //메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"ContactDbHelper의 onCreate");
        //데이터 베이스가 사용 할 Table을 생성성
        db.execSQL(CREATE_CONTACT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"ContactDbHelper onUpgrade ");
        //TODO

    }
}
