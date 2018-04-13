package edu.android.lec31_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SHARED_PREF_ID = "shared_pref_id";

    //멤버변수 선언
    private EditText editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            //익명 클래스
            @Override
            public void onClick(View v) {
              saveSharedPreferences();

            }
        });
        
        readSharedPreferences();

    }

    private void readSharedPreferences() {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String id = pref.getString(KEY_SHARED_PREF_ID, null);
        editText.setText(id);

    }
    //외부클래스의 장점 : 밖의 변수등을 사용 가능 하다
    private void saveSharedPreferences(){
        //SharedPreference
        //  >>앱이 사용하는 간단한 정보들(ex) 환경설정, 자동 로그인, ...)을 파일에 저장할때 사용하는 클래스
        //  >> 안드로이드 플랫폼이 관리 -> 파일로 관리
        //  >> 일반적으로는 각 어플이 생성한 SjaredPreference만 읽거나 쓸 수 있고,
        //     다른 어플이 생성한 SharedPreference는 읽거나 쓸 수 없음(읽을 수 이도록 권한 풀 수는 있으나 비추)

        // 1> 아드로이드 플랫폼이 관리해주는 SharedPreference 객체를 가져옴
        //    >>getPreferences(mode) :SharedPreferences를 저장하는 파일의 이름을 지정하지 않고, 하나의 파일만 사용하는 경우
        //    >>getSharedPreferences(name, mode) : SharedPreferences를 저장하는 파일의 이름을 지정, 여러개 사용경우
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String id = editText.getText().toString();
        //다른 어플들은 사용 불가, 본인 어플만 사용 가능하도록 값을 가져오게 만든 코드
        boolean result = pref.edit()// SharedPreferences 펹비 시작
                .putString(KEY_SHARED_PREF_ID, id/*id = value값*/)// SharedPreferences에 데이터 저장
                .commit();// 저장된 내용을 파일에 씀
//               *commit() : SharedPreferences를 put 한 내용이 정상적으로 되었다면  commit이 true가 되고
//                            중간에 오류가 생겨 입력이 안되었을 경우 false가 된다

        if(result){
            Toast.makeText(this, "저장 성공 ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"저장 실패", Toast.LENGTH_SHORT).show();
        }




    }
}
