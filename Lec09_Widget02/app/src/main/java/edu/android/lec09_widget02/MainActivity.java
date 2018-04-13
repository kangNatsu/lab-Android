package edu.android.lec09_widget02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //6:Logcat에서 Edit Filter로 로그 출력
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //모든 CheckBox를 찾는 것 보다는 체크박스 클릭 시 --를 보여주세요 라고 등록을 하는게 좋다

        //ToggleButton  찾기
        ToggleButton toggle = findViewById(R.id.toggleButton);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ToggleButton의 현 상태를 그래로 문자열로 준다/ Off 상태이면 Off 출력
                String text = ((ToggleButton)v).getText().toString();
                boolean checked = ((ToggleButton)v).isChecked(); //토글 버튼이 체크 되어 있는지 확인
                String msg = text + ", " + checked;
                Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
            }
        });

        Switch swWifi = findViewById(R.id.switchWifi);
        swWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = ((Switch)v).getText().toString();
                boolean checked = ((Switch)v).isChecked();
                String msg = text +" , "+checked;
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        //Spinner객체 찾기
        Spinner sp = findViewById(R.id.spinner);

        //Spinner는 OnItemSelectedListener를 등록
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Item이 선택 되었을때 -> 어떠한 일을 해야

                //로그 출력 -> 태그 정의 필요(위로)
                Log.i(TAG, "parent" + parent);
                //Parent : Spinner 객체
                Log.i(TAG, "view" + view);
                //View : 선택된 아이템 객체
                Log.i(TAG,"position" +position);
                //Position : 선택된 아이템 위치
                Log.i(TAG, "id"+ id);
                //id : 선택된 아이템 아이디
                //특별한 경우가 아니면 Position의 값과 id의 값은 같다

                //버전 정보
                String version = ((TextView)view).getText().toString();
                Toast.makeText(MainActivity.this, version, Toast.LENGTH_SHORT).show();

                Spinner spinner = (Spinner) parent;
                spinner.getSelectedItem();//스피너에서 선택된 아이템 객체를 리턴
                spinner.getItemAtPosition(position);//스피너에서 특정 위치의 아이템을 리턴

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Item이 선택되지 않았을때 (만약 선택되지 않은 상태에 일을 한다면 메소드 구현, 없으면 비어있는 바디로 두기)
                //->굳이 구형하지 않아도 된다. 단 메소드의 바디는 만들어 두고 비어있게 둔다

                //Do Nothing
            }
        });

    }




    public void showToast(View view) {
        //클릭된 view 객체가 CheckBox인지 확인
        if( view instanceof CheckBox){
            CheckBox cb =(CheckBox) view;//view를 체크박스로 형변환
            //CheckBox의 Text를 읽음
            String coffee = cb.getText().toString();
            //CheckBox가 체크되어 있는지 확인
             boolean checked = cb.isChecked();

             String msg = coffee + " , " + checked;
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }


    }
}
