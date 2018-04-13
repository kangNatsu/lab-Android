package edu.android.lec25_listview04;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    class ProductAdapter extends ArrayAdapter<Product>{

        private Context context;
        private List<Product> dataset;

        public ProductAdapter(@NonNull Context context,
                              @NonNull List<Product> objects) {

            // 부모 클래스인  AffayAdatorg<>는 기본 생성자를 갑고 있지 않음
            //     -> 자식클래스인 PriductAdapter 에서는
            // 부모 클래스의 매개변수가 생성자를 명시적으로 호출해야 한다
            super(context, -1, objects);
            this.context = context;
            this.dataset = objects;


        }//상품을 리스트에 보여줄 수 있는 어뎁터 만들어 View만 상속

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //ART가 가지고 있는 메소드 + 호출 -> View를 그기려 한다 -> View에 어떤것을 만들건지 만들어 달라

            //리스트 아이템 하나에 대한 뷰를 생성하고, 세팅
            LayoutInflater inflater= LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item, parent,false);

            // xml에 있는 ImageView, TextView에 해당 위치이 데이터로 세팅


            ImageView iv = view.findViewById(R.id.imageView);
            iv.setImageResource(dataset.get(position).getPhotoId());
            //dataset.get() = product 1개

            TextView name = view.findViewById(R.id.textName);
            name.setText(dataset.get(position).getPtoductName());

            TextView price = view.findViewById(R.id.textPrice);
            price.setText(dataset.get(position).getPrice() + "원");
            // price.setText(dataset.get(position).getPrice()); >> 오류남
            //   - setText 안에 문자열이 오는 경우는그냥 써 주면 되지만 int가 오는 경우는 resid를 사용하게 된다
            //  따라서 setText()쓰고 거기에 getPrice를 주면 그 가격이 아니라 문자열을 가직 String.xml에서 해당하는
            //   문자열을 찾으려고 한다
           // 따라서 오류가 안나게 하려면  price.setText(dataset.get(position).getPrice() + "원"); 처럼 문자열 넣어
             // 변수 자체를 문자열로 만들어 버린다


            return view;
        }


    }

    private List<Product> data;   //Model 클래스가 원소에 들어가는 item 한개
    private ListView listView;//안드로이드에 있는 클래스




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayList에 값을 자징- 데이터 찾기
        //ListView에사 사용할 데이
        data = ProductDao.getInstence().getProductLists();
        //싱글톤 디자인이라 선언을 했기 떼문에 getInstance로 찾고 ArrayList를 리턴 받음

        //ListView사용
        listView = findViewById(R.id.listView);

        //ArrayAdapter<> 인스턴스 생성
           // 안드로이드가 가지고있는 기본적인 ArrayAdapter를 받아 View가 보이도록
        ProductAdapter adapter = new ProductAdapter(this,data);
        // ->>  데이터 목록과 주소를 생성

        //ArrayAdapter를 ListVIew에 생성 - 화면에 보여짐
        listView.setAdapter(adapter);


        //ListView의 이벤트 리스너를 등록- 아이템 클릭 했을때 다른 페이지 보여주겠음
        //이벤트 리스어 이용 - onItemClick
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //아이템이 들어있는 부모 View, 우리가 클릭한 아이템 하나에 대한 view, 아이템이 리스트의 몇번쨰에 있는지
                //position = id

                //TODO : DetailActivity 를 생성할 수 있는 Intent를 생성,
                //position 정보를 intent에 설정, startActivity() 호출
                Intent intent = DetailActivity.newIntent(MainActivity.this, position);
                startActivity(intent);
            }
        });


    }
}
