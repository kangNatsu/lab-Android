package edu.android.lec03_simpleapp02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE1 = "Hello, World!";
    public static final String MESSAGE2 = "안녕이라네~!";

//    public static final String[] object = {MESSAGE1, MESSAGE2};

    private boolean toggle;
    //버튼을 누르면 안녕하세요가 나오세 하고 한번 더 누르면 HW가 나오게. 또 누르면 안녕~(반복 되게)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //1.레이아웃 파일에 정의 된 TextView찾기
        final TextView textView = findViewById(R.id.TextView);

        //3.button찾기
        Button btn = findViewById(R.id.button);

        //4.button 클릭 했을깨 런타임이 호출되는 메소드 찾음
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               if ( curI == 0){
//                   Toast.makeText(MainActivity.this, MESSAGE1, Toast.LENGTH_SHORT).show();
//                   String str = textView.getText().toString();
//                   curI++;
//               }else if (curI ==1){
//                   Toast.makeText(MainActivity.this, MESSAGE2, Toast.LENGTH_LONG).show();
//                   String str = textView.getText().toString();
//                   curI--;
//               }
//
//            }
//        });
                if (toggle) {
                    textView.setText(MESSAGE1);
                    toggle = false;
                } else {
                    textView.setText(MESSAGE2);
                    toggle = true;
                }


            }

//                if (textView.equals(MESSAGE1)) {
//
//                    textView.setText(MESSAGE2);
//                } else {
//                    textView.setText(MESSAGE1);
//
//                }
//
//            }
        });


        }
}




