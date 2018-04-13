package edu.android.lec09_masterdetail02;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFregment extends Fragment {

    interface ContactSelectedCallback{//약속, MainActivity야 이것좀 가지고 있어라! 란 의미
        void onContactSelected(int position);  //MainActivity가 이런 메소드를 가지고 있으면 밑의 TODO 부분에 부르고 거기에 position 정보를 주겠다
    }

    //Fregment를 Attach하고있는 Activity의 주소를 저장
    private ContactSelectedCallback callBack;//CallBack이라는 멤버변수는 인터페이스 속성인다

    private RecyclerView recycler;
    private List<Contact> dataset;


    public ContactListFregment() {
        // Required empty public constructor
        //반드시 디폴트 생성자가 있어야 한다

    }

    @Override
    //Fragment가 가장 먼저 MainActivity에 붙을떄 시작되는 단계
    //Fragment가 어느 Activity에 붙는지 그 Activity주소를 주는게 멤버변수 context
    public void onAttach(Context context) {// <-> onDetach(떨어질때)
        //fregment가 mainActivity에 Attach될때 callBack을 넣어준다
        if (context instanceof ContactSelectedCallback){
            callBack = (ContactSelectedCallback) context;
            //Frament를 Attach해서 가지고 있는 멤버변수는 반드시 인터페이스 타입으로 형변환을 해서 callBack을 해야 한다
            //   = 반드시 인터페이스를 구현해야 한다
        }else{
            new RuntimeException("반드시 ContactSelectedCallback을 구현해라");
        }

        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack = null;
    }

    @Override
    //onCreateView는  fragment에 대한 layout을 만들어주는 메소드가
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_contact_list_fregment,
                                      container/*framrlayout*/, false);
        //fragment_contact_list_fregment << 안에 들어가 있는게 RecycleView가 들어가 있어 실행하면 보인다

        //분리한 이유 - findViewById하려고, 즉 찾기 위해

        //  1) 데이터 초기화
        dataset = ContactLab.getInstance().getContactList();

        //프래그먼트의 레이아웃리 가지고 있는 RecyclerView를 찾음
        recycler = view.findViewById(R.id.contactList);
        recycler.setHasFixedSize(true);

        //recyclerView에 가장 중요한것은 Latout 매니저 설정
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //fregment의 매개변수 자리에는 this를 넣어서는 안된다.
        //getContext() >> Activityd의 주소를 준다

        //RectclerVIew에 Adapter 설정
        ContactAdapter adapter = new ContactAdapter();
        recycler.setAdapter(adapter);


        return view;

    }
     class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
        //Adapter가 추상 클래스이기 때문에
         @Override
         public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             //ViewHolder가 어떤 아이템을 가지고 있는지(ART업무), 아이템 하나하나를 어떻게 꾸며 줄 건지 (개발자 업무)
             //ViewHolder를 생성 해서 ViewHolder를 리턴 해 주면 onCreateViewHolder의 업무는 끝
             //아이템 하나의 ViewHolder가 어떻게 될거냐
             LayoutInflater inflater= LayoutInflater.from(getContext());
             View itemView = inflater.inflate(R.layout.contact_item, parent, false);
              ViewHolder holder = new ViewHolder(itemView);//ItemView는 ViewHolder의 매개변수
             return holder;//
         }

         @Override
         public void onBindViewHolder(ViewHolder holder, final int position) {

           Contact contact =dataset.get(position);
           holder.contactPhoto.setImageResource(contact.getPhotoId());
           holder.contactName.setText(contact.getName());
           //ㄱ가 아이템을 클릭했을때 이벤트 처리
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(getContext(),"position ::" + position,Toast.LENGTH_SHORT).show();
                     //TODO : MainActivity에게 position 정보를 전달
                     callBack.onContactSelected(position);
                 }
             });
             //StartActivity 메소드는 fregment가 Attach되어있는 Avtivity가 생성시켜줘야 한다
             //fregment는 Activity에게 position정보를 주고 MainActivity가 정보를 받아 실행시키게 한다
         }

         @Override
         public int getItemCount() {
             //데이터의 사이즈(크기)만 리턴
             return dataset.size();
         }
        //recyckerView에 adapter를 상속받는 클래스가 있어야 하고 또 viewHolder를 가지는 클래스가 있어야 한다

         //내부클래스 정의
         class ViewHolder extends RecyclerView.ViewHolder{

             ImageView contactPhoto;
             TextView contactName;

             public ViewHolder(View itemView) {
                 // itemView 는 cardLayout과 등등 가지고 있음

                 super(itemView);
                 contactPhoto = itemView.findViewById(R.id.imageView);
                 contactName = itemView.findViewById(R.id.textView);
             }
         }


     }//end contactAfapter
}
