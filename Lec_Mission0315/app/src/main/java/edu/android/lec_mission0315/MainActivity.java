package edu.android.lec_mission0315;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ORDERS = "orders";
    public static final String EXTRA_PAYMENT = "payment";

    // 배열 선언
       //setContentsView가 끝난 뒤에 불러온다, 배열의 갯수만 알려 두고 findViewBy는 무조선 CetContentsView가 끝난 뒤어 넣어줘야야
   private TextView[] texts = new TextView[3];//원소 3개인 배열, 원소는 각각 null상태
    private CheckBox[] checks = new CheckBox[3];
    private Spinner[] spinners = new Spinner[4];
    private EditText[] editTexts = new EditText[3];

    //변수 선언
    private CheckBox cbAmericano, cbLatte, cbMocha;
    private Spinner spinnerAme, spinnerLa, spinnerMo;
    private EditText editTextA, editTextL, editTextM;
    private RadioGroup rg;
    private Button btnOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //선언한 배열로 출력가능하게 만들기
        checks[0] = findViewById(R.id.cbAmericano);
        checks[1] = findViewById(R.id.cbLatte);
        checks[2] = findViewById(R.id.cbMocah);

        //CheckBox찾기
        cbAmericano = findViewById(R.id.cbAmericano);
        cbLatte = findViewById(R.id.cbLatte);
        cbMocha = findViewById(R.id.cbMocah);

        //Spinner 찾기
        spinnerAme = findViewById(R.id.spinnerAme);
        spinnerLa = findViewById(R.id.spinnerLa);
        spinnerMo = findViewById(R.id.spinnerMo);

        //EditText찾기
        editTextA = findViewById(R.id.editTextA);
        editTextL = findViewById(R.id.editTextL);
        editTextM = findViewById(R.id.editTextMo);

        //RsdioGroup & RadioButton찾기
        rg = findViewById(R.id.radioGroup);
        btnOrder = findViewById(R.id.button);

        //동작은 버튼을 눌렀을때 한꺼번에 되도록
        //setOnClickListener를 버튼에
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //메소드 하나 만들어서 onCreat 밖에서 일을 따로 하고 불러오기 - 할 일이 많아서
                makeOrder();


            }
        });




    }

    private  void makeOrder(){
        // 1)주문들을 저장할 ArrayList 선언/생성
        ArrayList<CoffeeOrder> orders = new ArrayList<>();//비어있는 상태

        //주문이 체크되어 있는지 아닌지 체크(음료 마다 반복)
           //변수만 바꿔준다면 for문 안에도 넣을 수 있다 - 배열을 선언
        if(cbAmericano.isChecked()){
            String name = cbAmericano.getText().toString();//이름이 있으면 커피 만들 수 있다 - 기본 가격은 자동 생성
            Coffee coffee = new Coffee(name);
            String size = spinnerAme.getSelectedItem().toString();
            int quantitiy = Integer.parseInt(editTextA.getText().toString());

            //edu.android.lec_mission0315.Coffee Order 만들기
            CoffeeOrder order = new CoffeeOrder(coffee, size, quantitiy);
            orders.add(order);
        }

        if (cbLatte.isChecked()){
            String name = cbLatte.getText().toString();//이름을 불러옴
            Coffee coffee = new Coffee(name);///커피 이름 생성
            String size = spinnerLa.getSelectedItem().toString();//스피너에서 선택된 아이템을 읽어서 사이즈저장
            int quantitiy = Integer.parseInt(editTextL.getText().toString());//parseInt : 문자열을 int로 바꾸겠다

            CoffeeOrder order = new CoffeeOrder(coffee, size, quantitiy);//이름, 사이즈, 수량을 주문 -order
            orders.add(order);//ArrayList에 넣는다
        }


        if (cbMocha.isChecked()){
            String name = cbMocha.getText().toString();
            Coffee coffee = new Coffee(name);
            String size = spinnerMo.getSelectedItem().toString();
            int quantitiy = Integer.parseInt(editTextM.getText().toString());

            CoffeeOrder order = new CoffeeOrder(coffee, size, quantitiy);
            orders.add(order);
        }

        //결제 방법 -> 라디오 그룹에 결제 방법이 있음
        //체크가 되어있는 라디오 버튼 ID : getCheckedRadioButtonId()
        int id = rg.getCheckedRadioButtonId();//라디오 버튼에서 체크되어 있는 버튼의 아이디를 찾겠습니다.
        //**만약 결제수단라디오 버튼을 체크하지 않고 결제 버튼을 누르면 앱이 죽어버린다
         //RadioButton이 null이 되기 때문이다.
        //에러가 없게 하려면
        //1. if구문 안에 밑의 두 문장을 넣는다
        //2. 라디오 버튼을 만들 때 부터 하나만 true로 변경해 한가지가 체크되어 있게 만든다
        if(id == -1){
            Toast.makeText(MainActivity.this, "결제 수단을 고르세요", Toast.LENGTH_SHORT).show();
        }
            RadioButton rb = findViewById(id);
            String payment = rb.getText().toString();



        //OrderDetailActivity에게 ArrayList, payment를 전달, 실행

        Intent intent = new Intent(MainActivity.this, OrderResultMain.class);
        intent.putExtra(EXTRA_ORDERS, orders);
                //Serializable : 파일에 넣고 빼는게 가능하게 하는 메소드
                //ArrayList는 Serializanle이라는 인터페이스를 구현
        intent.putExtra(EXTRA_PAYMENT, payment);

        //상수 정의
        startActivity(intent);



    }


}
