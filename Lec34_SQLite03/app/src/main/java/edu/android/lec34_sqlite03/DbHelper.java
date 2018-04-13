package edu.android.lec34_sqlite03;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static edu.android.lec34_sqlite03.MainActivity.TAG;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String SQL_CREATE_TABLE =
                                   "create table contacts(_id integer primary key autoincrement)";
    private static final String SQL_DROP_TABLE = "drop table contacts";
    private static final String SQL_CREATE_TABLE2 =
            "create table contacts(_id integer primary key autoincrement," +
            "name text not null," +
            "phone text)";



    public DbHelper(Context context) {
        super(context, DB_NAME, null , DB_VERSION);
        Log.i(TAG, "DBHelper 호출");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate 호출");
        try {
            db.execSQL(SQL_CREATE_TABLE);
            Log.i(TAG, "테이블 생성 성공");
        }catch (Exception e){
            Log.i(TAG, "테이블 생성 실패" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String msg = String.format("onUpgrade [Old : %d // New : %d)호출", oldVersion, newVersion);
        Log.i(TAG, msg);


        try {
            //기존 테이블 삭제
            db.execSQL(SQL_DROP_TABLE);
            //새로운 테이블 작성
            db.execSQL(SQL_CREATE_TABLE2);
        }catch (Exception e){
            Log.i(TAG, "onUpgrade 실패 " + e.getMessage());
        }

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       String msg = String.format("onDowngrade (Old : %d // New : %d ) 호출", oldVersion, newVersion);
       Log.i(TAG, msg);


       db.execSQL(SQL_DROP_TABLE);
       db.execSQL(SQL_CREATE_TABLE);

    }
}
