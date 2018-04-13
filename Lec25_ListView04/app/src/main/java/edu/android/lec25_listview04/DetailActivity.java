package edu.android.lec25_listview04;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

    private static final String EXTRA_PRODUCT_INDEX = "product_index";

    //Intent 를 만들 수 있는 factory메소드
    public static Intent newIntent(Context context, int index){
        Intent intent = new Intent(context, DetailActivity.class);
        //매변1)인텐트를 만들어 보내는 주소, 매변2)인텐트를 받아서 실행이 될 클래스 이름
        intent.putExtra(EXTRA_PRODUCT_INDEX, index);// KEY값이 필요
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //1 .MainActivity가 Intent로 보내중 아이템의 index,position 정보를 추출
        //   >> 2.ArrayList에서 해당 인덱스의 Product 정보를 춫루 해야
        //   >> 3.ImageView, TextView의 내용을 설정 가능 (set이 가능)

        //1.
        Intent intent = getIntent();//메인이 보내준 Intent 찾기
        int index = intent.getIntExtra(EXTRA_PRODUCT_INDEX, 0);
        //두번쨰 매개변수의 의미 : int값을 못찾았을때 디폴트값으로 무엇이라 하겠는가

        //Product1새를 찾는게 가능 >>DAO Class 필요
        //2.
        Product product = ProductDao.getInstence().getProductLists().get(index);

        //3.
        ImageView iv = findViewById(R.id.imageDetailPhoto);
        iv.setImageResource(product.getPhotoId());

        TextView tx = findViewById(R.id.textDetailName);
        tx.setText(product.getPtoductName());

        TextView tx2 = findViewById(R.id.textDetailPrice);
        tx2.setText(product.getPrice()+"원");

        TextView tx3 = findViewById(R.id.textDetailDesc);
        tx3.setText(product.getDescription());


    }
}
