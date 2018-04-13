package edu.android.lec_mission0315;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderResultMain extends AppCompatActivity {

    private TextView tv;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_result_main);

        tv = findViewById(R.id.textDetail);
        btn2 = findViewById(R.id.button2);

        Intent intent = getIntent();

        ArrayList<CoffeeOrder> orders =
                (ArrayList<CoffeeOrder>) intent.getSerializableExtra(MainActivity.EXTRA_ORDERS);
        for(CoffeeOrder order : orders){
            tv.append("\n");
            tv.append(order.toString());
        }

        //결제 정보 꺼내기
        String payment = intent.getStringExtra(MainActivity.EXTRA_PAYMENT);
        tv.append("\n");
        tv.append("결제 방식 : " + payment);



    }
}
