package edu.android.lec22_listview01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ListView를 찾아서, 이벤트 리스너를 등록
        //아이템을 클릭했을때 그 정보를 Toast 메시지로 확인

        ListView listView = findViewById(R.id.listView);
        //EventLisrener 등록
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override  
            public void onItemClick(AdapterView<?> parent, View view,
                                     int position, long id) {
                //**position과 id는 대부분은 한세트로 위치가 같아야 한다

                //parent : 클릭된 아이템의 부모 뷰(ListVirrew)
                //view : 클릭된 아이템의 뷰(TextView)
                //position/id : 클릭된 아이템의 위치./행 아이디(row Id)
                //특별한 경우가 아니면 position과 id 값은 같음

                Log.i(TAG, "parent" + parent);// ListView - item의 부모
                Log.i(TAG, "view " + view);//android iteb 하나하에 대한 것은
                Log.i(TAG, "position " + position);
                Log.i(TAG,"Id " + id);

                String msg = ((TextView)view).getText().toString();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
