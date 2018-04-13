package edu.android.lec33_sqlite02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static edu.android.lec33_sqlite02.Product.ProductEnity.*;



/**
 * Created by user on 2018-03-27.
 */

public class ProductDbhelper extends SQLiteOpenHelper {
    //DB이름과 버전 상수로 정의
    private static final String DB_NAME = "product.db";
    private static final int DB_VERSION = 1;


    //테이블을 만드는 SQL문장 구조
    //create table 테이블 이름 (
    //  _id integer primary key autoincrement(자동으로 증가했으면의 의미,
    //  pname text(not null),  >> 제약조건들
    //  price Integer,
    //  description  text)
    private static final String CREATE_PRODUCT_TABLE =
            "create table " + TABLE_NAME
            +"( "
            +_ID + " integer primary key autoincrement, "
            +COL_NAME + " text,"
            +COL_PRICE +" integer,"
            +COL_DESC + " text"
            +" )";




    public ProductDbhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //onCreate에서 테이블 생성하면 된다
        db.execSQL(CREATE_PRODUCT_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //TODO
}
