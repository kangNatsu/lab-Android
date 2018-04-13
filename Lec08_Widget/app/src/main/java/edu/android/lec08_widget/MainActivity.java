package edu.android.lec08_widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  RadioButton rdBtn1;
    private  RadioButton rdBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdBtn1 = findViewById(R.id.rdBtn1);
        boolean checked = rdBtn1.isChecked();

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //두개의 라디오 버튼 중에서 체크된 라디오 버튼을 찾고 싶음

                //1) 모든 라디오 버튼중에서 isChecked()메소드를 호출해서 검사

                //2) 라디오 그룹에서 체크된 라디오 버튼의 아이디를 찾는 방법
                RadioGroup rg = findViewById(R.id.radiogroup);
                int id =  rg.getCheckedRadioButtonId();
                //-> 변수 타입은 int타입
                //체크된 라디오 버튼의 아이디만 찾은 것
                RadioButton rb = findViewById(id);

                //TextView 찾기
                TextView tv = findViewById(R.id.textView);
                tv.setText(rb.getText());
//                if(id == R.id.rdBtn1){
//                    tv.setText("동의함");
//                }else{
//                    tv.setText("거부함");
//                }


            }
        });
    }

    //라디오 버튼 클릭 이벤트 리스너 메소드 - xml파일에서 정의
    public void showMessage(View view) {
        //메소드 중에 getText가 없으므로 형변환을 해야한다
        //여기서 View는 RadioButton이다(Imageview를 만들었다면 불렀을떄 image View가 불려옴)
        if(view instanceof RadioButton){
            //view가 RaidoButton인지 검사
            //A instanceof B A는 B타입의 변수입니까?
            String msg = ((RadioButton) view).getText().toString();
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
