package edu.android.lec26_recyclerview01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";

    private RecyclerView recycler;
    //데이커로 사용할 메소드
    private String[] dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //소스 코드로 문자열에 있는 문자열 배열등을 가져 올 수 있다 >> 문자열 배열
        //string.xml가 가지고 읶는 이불템은
        dataset = getResources().getStringArray(R.array.items);


        recycler = findViewById(R.id.recycler);//String.xml안음



        //RecyclerView의 성능을 향상시키기 위한 옵션 설정
        //  >>RecyclerView가 가지고 있는 각 아이템 뷰들의 크기가 일정
        recycler.setHasFixedSize(true); //모든 레이아웃들이 똑같다 생각을 하고 들어가기 때문에 성능은 조금 더 업
        //하지만 뷰마다 사이즈가 다르다면 이 코드를 사용해서는 안된다

        //RecyclerView는 LayoutManager를 반드시 설정해야 한다다
        recycler.setLayoutManager(new LinearLayoutManager(this));//LinearLayoutManager하나이상의 매개변수를 갖는다
        //MainActivity 의 주소만 주면 된다

        //RecyclerView 가 사용할 AdapterView 설정  >>>  화면에 보이기 시작한다
        ItemAdapter adapter = new ItemAdapter();//생성만 하면 ItemAdapter의 instance만 생성
        //화면 안에 아이템이 몇개 들어갈 수 있는지 계산- (아이템 하나가 들어가는 공간 = ViewHplder)
        //설정한  메소드의 onCreateViewHolder 호출 ->> TextView만들어냄 ->> itemViewHolder에 줌
//           ->>ItemViewHolder 는 받은 textView를 멤버변수로 저장 :: 여기까지가 그릇을 만듬
//        >>> OnBindViewHolder가 만들어진 그릇에 내용을 채워라(호출은 ART이 할테니 정보는 개발자가 넣어라,
//            단, 그 그릇에 들어갈 내용의 주소는 a, 그 그릇은 몇버째로 가야한다는 등의 주소는 ART가 줌)
        recycler.setAdapter(adapter);

    }

    //Recycler.Adapter<h>를 상속받는 내부 클래스을 정의
    // RecyclerView.Adapter<VH>를 상속받는 내부 클래스를 정의
    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
        //데이터 하나를 보여주는 때 사용되는 뷰 에이아웃 정보를 갖고 읶는 ViewHoider를 괴롭


        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(TAG,"onCreatrViewHolder 출력");

            LayoutInflater   inflater = LayoutInflater.from(MainActivity.this);//매개변수로 메인 주소 주면 된다
            View itemView =  inflater.inflate(android.R.layout.simple_list_item_1,
                                               parent, false);

            ItemViewHolder holder = new ItemViewHolder(itemView);
            //아이템 부를 만들고 아이템 뷰 홀더의 매개변수 안에 넣어줘야 한다
            return holder;
        }

        //ViewHoldet에 View들을 세팅해야 할 시점에 ART이 호출하는 메소드
        // 화면에 보여줄 아에템 정보를 ViewHolder 객체에 설정
        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Log.i(TAG,"onBindViewHolder 출력");
            holder.textView.setText(dataset[position]);


        }

        //RecyclerView에 들어갈 아이템의 숫자 리턴
        @Override
        public int getItemCount() {
            Log.i(TAG,"getItemCount 출력");
            //내부 클래스는 외부 클래스(MainActivity)의 멤버변수 응 사용 할 수 있음
            return dataset.length;
        }

        // ItemAdapter가 사용할 ViewHolder 클래스를 정의
        class ItemViewHolder extends RecyclerView.ViewHolder {


            TextView textView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                textView = (TextView)itemView;
                //itemView 를 TextView로 전환
                //  >>가능한 이유 : itemViewHolder 를 생성할때 매소드를 simple_list_item_1으로 줬기 때문
                //   >>>만약 simple_list_item_1으로 하지 않았다면 findViewById 이용해 찾은 뒤 부렀어야 함


                //클릭했을때 어떤 동작을 하도록 설정하고 싶으면 이 클래스에서 해야 한다
                //왜냐하면 아이템을 클릭했을때의 정보가 여기 있기 떄문에
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "눌렀니?"+textView.getText().toString(),
                                          Toast.LENGTH_SHORT).show();
                    }
                });

            }
        } // end class ItemViewHolder

    } // end class ItemAdapter

}
