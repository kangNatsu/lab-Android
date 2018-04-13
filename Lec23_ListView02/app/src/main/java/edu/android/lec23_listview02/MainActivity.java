package edu.android.lec23_listview02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //멤버변수
    private DrinkLab lab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lab = DrinkLab.getInatance();
        //DrinkLab에 있는 getInatance()를 보고 Inatanced의 값을 본다
        List<Drink> data = lab.getDrinkList();

        //  1. 레이아웃 xml 파일에 있는 ListView찾음
        ListView listView = findViewById(R.id.listView);

        // 2. 데이터를 ListView에 끼워 넣어 줄 수 있는 Adapter를 생성


        ArrayAdapter<Drink> adapter =
                new ArrayAdapter<Drink>(this,//Comtext : 엑티비티 정보
                        android.R.layout.simple_list_item_1,//아이템 하나의 레이아웃
                        data);//아이템으로 사용할 데이터(배열, LIst)

        // 3. Adapter를 ListVirew에 설정을 해 준다(끼워준다)
        listView.setAdapter(adapter);


        // 4. 나머지는 자동으로 만들어준다
        // **Emulater에서 이름이 주소값으로 나오는게 싫다면 Drink 클래스에서 toString 오버라이드 해 주면 된다

        //ListView에 EventLietener 설정하기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //클릭한 아이템(음료수에) 이름만 토스트 메시지로 출력
//                lab.getDrinkList().get(position); //  >> Drik라는 클래스 하나
                String name = lab.getDrinkList().get(position).getDrinkName();

                Toast.makeText(MainActivity.this, name ,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
