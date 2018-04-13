package edu.android.lec27_recyclerview02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";
    private int vhCreateCount = 0;
    private int vhBindCount = 0;


    //데이터
    private List<Contact> dataset;
    private RecyclerView recycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecycleView 에서 사용할 데이터 초기화
        dataset = ContactLab.getInstance().getContactList();

        //RecyclerView 찾기
        recycler = findViewById(R.id.recycler);

        //옵션 설정 >> 성능향상
        recycler.setHasFixedSize(true);
        //아이템에 대한 View들이 전부 같은 사이즈로 만들어지는냐? 에 대한 코드. true이면 고정된 크기가 되어 속도가 빨라짐

        //LayoutManager 설정
        recycler.setLayoutManager(new LinearLayoutManager(this));

        //RecyclerView.Adapter를 생성
        ContactAdapter adapter = new ContactAdapter();

        //Adapter를 RecyclerView에 설정 >> 이 과정을 해야 화면에 보이기 시작한다
        recycler.setAdapter(adapter);




    }

    class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(TAG,"onCreateViewHolder" + vhCreateCount);

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.recycler_item, parent, false);
            //View를 만든 이유 >>ViewHolder를 만들기 위해
            ViewHolder holder = new ViewHolder(view);
            //view에는 Imageview와 TextView를 가지고 있다

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //그릇과 아이템을 연결시켜줄때 ART가 호출
            Log.i(TAG,"onBindViewHolder" + vhBindCount);
            //ViewHolder안에는 만들어둔 Layout이 있는데 그걸 연결하려 하니 set을 이용해 만들어라

            vhBindCount++;
            Log.i(TAG, "onBindViewHolder"+vhBindCount);
            //배열이나 ArrayList가 dataset으로 사용이 되기 떄문에 position이 index의 기능을 하고

            // Contect 꺼내기
            final Contact contact = dataset.get(position);// =ViewHolder
            //지역변수는 final로 선언해야 익명클래스안에서 사용이 가능 하다
            //익명클래스의 메소드는 클릭 할때 호출이 되므로 언제 호출이 될 지 모르므로
            //지역변수는 메소드가 끝나면 사용을 못하기 때문에 익명클래스에서는 사용을 못하는게 맞는데 지역변수를 final로 선언을 하면
            //함꼐 포함되어 있는 익명클래스에서도 쓸 수 있도록 따로 관리 하기 때문
            holder.imageView.setImageResource(contact.getPhotoId());
            holder.textView.setText(contact.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = String.format("%s, %s, %s",contact.getName(),contact.getPhone(),contact.getEmail());
                    Toast.makeText(MainActivity.this, msg,Toast.LENGTH_SHORT).show();

                }
            });

        }

        @Override
        public int getItemCount() {
            //데이터로 사용할 아이템이 몇개인지 리턴
            return dataset.size();
        }

        //ViewHolder 상속 받아야 한다 << RecyclerViewHolder가 가지고 있는
        class ViewHolder extends RecyclerView.ViewHolder{//ViewHolder는 ContactHolder의 ViewHolder이다

            View itemView;
            ImageView imageView;
            TextView textView;
            //  >>미리 만들어 두는 이유 : Bind 과정을 할때 findViewById()를 할 수 있기때문에 미리 해 둔다

            public ViewHolder(View itemView) {
                super(itemView);

                this.itemView = itemView;
                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);

                ///Click Event도 추가 가능하다
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,textView.getText(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }//end class ViewHolder


    }//end class ContactAdapter





}//end MainActiviry
