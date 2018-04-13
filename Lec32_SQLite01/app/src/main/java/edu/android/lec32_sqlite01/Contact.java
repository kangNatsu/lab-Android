package edu.android.lec32_sqlite01;

import android.provider.BaseColumns;



/**
 * Created by user on 2018-03-27.
 *
 * 데이터 클래스 (MODEL CLASS)
 *
 *    >>자신이 가지고 있는 테이블의 정보들을 공개하는 그 정보들을 모아 두는 폴더 :: provider
 */

public class Contact {


    // SQLite  DB에서 사용할 테이블의 이름, 컬럼들의 이름 정의
    //내부클래스로 만들겠다
    public static abstract class ContactEntity implements BaseColumns{
//        BaseColumns = _ID하는 의미를 가지고 있어 따로 primaryKey를 만들지 않고,
//        이 BaseColumns을 PRIMARYKEY로 사용 하겠다.
        //테이블의 컬럼이 필요 - 테이블 이름
        public static final String TABLE_NAME = "contacts";
        //  연락처 저장시 이름 전화번로 이메일 필요 -> 컬럼으로
        //테이블 컬럼 이름들
        public static final String COL_CNAME = "cname";
        public static final String COL_PHONE = "phone";
        public static final String COL_EMAIL = "email";
        //primaryKey로 사용할 컬럼의 이름은 BaseColumns가 가지고 있기 때문에 따로 정의X


    }

    //멤버변수, 필드
    private int cid; // primary key로 사용(고유키)
    private String cname;
    private String phone;
    private String email;

    //생성자
    public Contact(){}//디폴트 생성자
    public Contact(int cid, String cname, String phone, String email) {
        this.cid = cid;
        this.cname = cname;
        this.phone = phone;
        this.email = email;
    }

    //  getter & setter
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //toString() @Override


    @Override
    public String toString() {
        return String.format("%d || %s || %s || %s ", cid, cname, phone, email);
    }
}
