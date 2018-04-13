package edu.android.lec24_listview03;

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

// public class contactAdapter{} 대신에 같은 패키지 안에서만 쓰도록 선언도 가능
//class MyClass{}



public class MainActivity extends AppCompatActivity {

    //자바 컴파일러가 자동으로 만들어주는 생성자
//    public MainActivity(){
//         super();
//    }
//
    //안드로이드에서 기본 제공하는 ArrayAdapter 클래스를 상속받는 클래스를 정의
    //ArrayAdapter 클래스는 기본 갱성자가 없고, 매개변수가 있는 생성자들만있다
    public class ContactAdapter extends ArrayAdapter<Contact>{
        //오류 이유 : 디폴트 생성자가 없기 때문에
        //따라서 생성자를 만들어 주지 않으면 부를 수없다

        private Context context;
        private List<Contact> data;


        public ContactAdapter(Context context, List<Contact> data){
            //부모 클래스긔 매개변수가 있는 생성자를 명시적으로 호출해야함!
            super(context, -1, data);
            //-1로 준 이유 : 이미지뷰와 텍스트뷰를 직접 만들것이기 때문에( resource 값이 없어야 함다 - 직접 만들려고)
            // -1이 아닌 다른 값을 주면 그 걸 찾느라 에러난다
            this.context = context;
            this.data = data;
        }

        //ListView 아이템 하나에 대한 레이아웃(View)을 생성해서 리턴하는 메소드


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //아이템의 갯수만큼 getView를 호출하는데 그때 호출뒤는 View가 우리가 만드는 Image,Text View 부분

            // 1)Layout xml 파일의 view들을 생성할 수 있는 LayoutInflator 객체 생성

            //레이아웃 xml 파일의 view들을 생성 할 수 있는
            LayoutInflater inflater = LayoutInflater.from(context);
            //아이템 뷰에 있는 이미지와 텓스트뷰의 내용을 작성
            View view = inflater.inflate(R.layout.list_item,parent,false);
            //inflate : ListView 의 이미지와 텍스트 만들어주는 것
            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(data.get(position).getPhotoId());
            TextView textView = view.findViewById(R.id.textName);
            textView.setText(data.get(position).getName());



            return view;
        }
    }


    private List<Contact> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ListView에서 사용할 데이터들을 초기화
        data = ContactLab.getInstance().getContactList();// ContactLab 타입의 싱글촌 Instance를 리턴

        //LIstView 찾김
        ListView listView = findViewById(R.id.listView);

        //LIstView에 설정할 Adapter class의 인스턴스를 생성
//        ArrayAdapter<Contact> adapter =
//                new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1,
//                                           data );

        //ListView 에 설정할 Adapter 클래스의 인스턴스를 생설
        ContactAdapter adapter = new ContactAdapter(this, data );

        //Adapter를 ListView에 설정
        listView.setAdapter(adapter);

        //어플에서만 사용할 adapter를 상속받는 class를 하나 만들어준다

        //ListView에 이벤트 리스너 등록
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent 만들고 정보 넣어주고 스타트
                //DetailActivity를 시작시키기 위해서 Intent를 생성
                Intent intent = DetailActivity.newIntent(MainActivity.this, position);
                // 시작 시키려는 클래스의(Activity의) 이름을 알고 있다

                startActivity(intent);

            }
        });


    }
}
