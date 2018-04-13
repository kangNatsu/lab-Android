package edu.android.lec05_linearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
   implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        Toast.makeText(this, "버튼 CLICK", Toast.LENGTH_SHORT).show();
        //화면에 버튼이 많은 경우에 이 방법을 사용한다/ onclick메소드 하나 만들어 두고 모든 버튼은 this를 써서
        //MainActivity주소만 주면 OK
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button찾기
        Button btn1 = findViewById(R.id.btn1);

        //클릭 이벤트 리스너(핸들러)를 등록
        btn1.setOnClickListener(new View.OnClickListener() {
            //Ctrl+Q : 안드로이드 API 설명 문서 보기
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"버튼1 CLICK", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn2 = findViewById(R.id.btn2);
//        MyClickListener listener = new MyClickListener();
        btn2.setOnClickListener(new MyClickListener());

        //onCreat안쪽이다. 여기서 this는 MainActivity(onCreate를 포함하고 있는 클래스의 주소)
        //버튼을 찾았는데 그 매개변수로 MainActivity를 넣겠다. 단 여기서 MainActivity를 넣으려면ㅇ
         //MainActivity가 onClickListener가 되어야 한다 -> 따라서 구현해야 한다
        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);


    }

    //activity_main.xml 파일에서 onClick 속성에 지정된 이벤트 리스너 메소드를 작성
    public void onButtonClicked(View view) {
        //리턴타입은 반드시 void여야 하고 매개변수는 반드시 view를 가져야 한다
        //onClick 메소드가 가지고 있는 매개변수와 똑같아야하기 때문에 view를 매개변수로
        //단점 :activity에서만 사용이 가능하고 xml에서 지정 할 수 있는건 onclick밖에 처리를 못함
        Toast.makeText(this,"xml 설정 메소드", Toast.LENGTH_SHORT).show();
    }

    //클래스를 생성 한 이유는 클래스를 위의 btn2.setOnClickLister()에 넣어주기 위해서
    //뭘? onClickLIstner에서 생성된 메소드를 넣어주기 위해,
    //OnClickListner가 인터페이스라 직접 구현 할 수 없으므로
     class MyClickListener implements View.OnClickListener{
        //추상 클래스 이므로 abstract를 붙여줘야 에러가 나지 않는다.->바디가 없는 메소드
        //Ctrl+I : 추산 메소드 구현(Implements)
         @Override
         public void onClick(View v) {
             Toast.makeText(MainActivity.this, "버튼2 CLICK", Toast.LENGTH_SHORT).show();
         }


    }
}
