package edu.android.lec34_sqlite03;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";

    private Button btnGetDb, btnTableDesc;
    private TextView textView;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetDb = findViewById(R.id.btnGetDb);
        btnGetDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDbVersion();
            }
        });



        btnTableDesc = findViewById(R.id.btnDbDesc);
        btnTableDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTableDesc();
            }
        });


        textView = findViewById(R.id.textView);

        //OpenHelper 클래스 필요 >> Openhelper를 통해 DB를 가지고와 SQL을 실행하기 위해

        Log.i(TAG, "MainActivity onCreate");
        DbHelper helper = new DbHelper(getApplicationContext());

        Log.i(TAG, "MainActivity getReadableDatabase() 호출전");
        database = helper.getReadableDatabase();//select만 가능하게 하겠다
        //insert, delete도 원하면 getwritableDatabase()사용
        Log.i(TAG, "MainActivity getReadableDatabase() 호출후");



    }

    private void getTableDesc() {
        //desc contacts; - Oracle, MySQL 테이블 구조
        String SQL_TABLE_DESC = "pragma table_info(contacts)";

        // void SQLiteDatabase.executeSQL() :
//            >> SQL 문장 실행 결과를 리턴받지 않아도 괜찮은 경우 - create, table, insert,....
        // Cursor SQLiteDatabase.rawQuery() :
//            >> select 문장과 같이 SQL 문장 실행 결과를 테이블과 같은 형대(Cursor)로 리턴을 받아야 할 떄

        Cursor cursor = database.rawQuery(SQL_TABLE_DESC, null);
        int count = cursor.getCount();
        Log.i(TAG, "count :: " + count);
        int colcount = cursor.getColumnCount();
        Log.i(TAG, " colcount :: " + colcount);

        String[] columnNames = cursor.getColumnNames();
        for (String name : columnNames){
            Log.i(TAG, name);
        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            for (int i = 0; i < colcount; i++){
                String values = cursor.getString(i);
                buffer.append(values).append(",");
            }
            buffer.append("\n");
        }
        textView.setText(buffer.toString());

    }

    private void getDbVersion() {
        int version = database.getVersion();
        textView.setText("DB Version ::" + version);



    }


}
