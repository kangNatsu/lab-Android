package edu.android.lec32_sqlite01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import static edu.android.lec32_sqlite01.Contact.ContactEntity.*;

/**
 * Created by user on 2018-03-27.
 *
 * ---
 *
 * DAO( Data Access Object ) : 데이터 베이스의 테이블을 접근
 * select, insert, update, delete 기능들을 수행하는 클래스
 * ContactDbHelper(SQLiteOpenHelper) 클래스가 제고하는 데이터베이스 객체의 메소드 사용
 */

public class ContactDao {

    //멤버 변수 선언 >> SQLite 기능을 사용 할 수 있는 클래스
    private SQLiteDatabase database;

    //Singleton Design Pattern >> 다른 클래스에서 생성자 소환X
    private static ContactDao instance = null;

    private ContactDao(Context context){
        //DB를 오픈하고 사용 할 것이기 때문에 생성자에서 SQLiteDatabase를 만든다
        ContactDbHelper helper = new ContactDbHelper(context);
        //constext를 줘야 하는데 줄 수 없다 >> 생성자의 매개변수로 주고 부를 수 밖에 없다
         //  = ContactDao를 생성할때 context정보를 넘겨주는 것
        // helper가 만들어 지면 DB를 생성 가능 하다
        database = helper.getWritableDatabase();
        //   >>> DB가 만들어져 있지 않으므로 helper클래스의 onCreate가 불리고 Table이 생성
                //select만 하는 경우 :: getWritableDatabase
                //select, insert 하는 경우 ::

    }

    public static ContactDao getInstance(Context context){
        //생성자와 마찬가지로 getInstance의 매개변수로 Context를 준다
        if(instance == null){
            instance = new ContactDao(context);
        }
        return  instance;
    }

    public long insert(Contact contact){
        long result = 0;

        ContentValues values = new ContentValues();
        values.put(COL_CNAME, contact.getCname());
        values.put(COL_PHONE, contact.getPhone());
        values.put(COL_EMAIL, contact.getEmail());

        //insert into contacts(cname) values(null);
        //insert into contacts(cname, phone, email) values(?,?,?); >>A
        result =database.insert(//insert가 long을 리턴하므로 앞에 미리 만들때 long으로 선언 한 것
                TABLE_NAME,          //insert 할 테이블 이름
                null,  //비어있는 COntactValues를 사용 할 때만,"cname" 과 같은 형태로 준다
                ///단 위와같이 key값을 미리 선언 할 경우는 null을 넣어주면 된다. A문장 처럼 사용하고 싶을 경우만
                values);   //테이블에 insert 할 key(컬럼이름)-value(값)

        //insert() 메소드의 리턴값 : 삽입된 행 번호(row id)
        //insert가 실패한 경우 -1을 리턴

        return result;
    }

    public List<Contact> select(){
        List<Contact> list = new ArrayList<>();
        //select * from contacts
        // where 검색 조건
        //group by 컬럼이름
        //having 그룹 내에서 필터링할 조선
        // order by 정렬한 컬럼이름 (기본값 || 오름 차순)

        String[]  columns = {_ID, COL_CNAME,COL_PHONE,COL_EMAIL};
        Cursor cursor = database.query(TABLE_NAME, //select할 테이블 이름
                                        columns,  //selecrt할 컬럼의 이름들(String 배열), 테이블에서 모든 컬럼을 검색하고자 할때는 null 써도 무관
                                        null,  // where절의 내용
                                        null,  //where 절에 사용되는 값들
                                        null, // group by에 사용되는 컬럼
                                        null, //having에 사용될 조건
                                        _ID);  //orde by에 사용되 컬럼럼

        while (cursor.moveToNext()){
            //다음 검색할 내용이 있을때까지 cursor 에서 값을 계속해서 꺼내면 된다
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            Contact contact = new Contact(id, name, phone,email);
            list.add(contact);

        }

        cursor.close();

       return list;
    }

    public Contact select(int id){
        //id 로 검색하는 메소드
        Contact contact = null;
        //select * from contacts where _id = ?   >>where는 생략

        String selection = _ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};  //뭔소 1개짜리 문자열 배열
        Cursor cursor = database.query(TABLE_NAME,     //테이블 이름
                                       null,  // 검색할 컬럼 이름( null = 컬럼 전부 검색하겠단 의미)
                                       selection,     // where 구문의 내용
                                       selectionArgs, // Where 구문을 채울 수 있는 값들 물음표의 갯수만큼의 배열
                                       null,
                                       null,
                                        null);

        if(cursor.moveToNext()){
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            contact = new Contact(id, name, phone, email);
        }

        cursor.close();
        return contact;

    }

    public int update(Contact contact) {
    int result =0;
    //update contasts set cname = ? phonr = ? RMAIL }

        ContentValues values = new ContentValues();
        values.put(COL_CNAME, contact.getCname());
        values.put(COL_PHONE, contact.getPhone());
        values.put(COL_EMAIL, contact.getEmail());

        String whereclause = _ID +"=?";
        String[] whereArgs = {String.valueOf(contact.getCid())};

        database.update(TABLE_NAME, values, whereclause, whereArgs);
        result = database.update(TABLE_NAME,values, whereclause, whereArgs);
        return result;

    }

    private int delete(int id){
        int result = 0;
        //delete from contadct shewe +id=

        String whereClause = _ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };

              result = database.delete(TABLE_NAME, whereClause, whereArgs );


                return result;

    }

}
