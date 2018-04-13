package edu.android.lec09_masterdetail02;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {

    private static final String ARG_CONTACT_INDEX = "selected_contact_index";
    //데이터 저장하고 데이터 읽어올 KEY값

    private int index;
    private ImageView contactImage;
    private EditText contactName, contactPhone, contactEmail;
    private Button btn;

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    //Factory메소드 작성(생성자 대신에 인스턴스를 생성하는 public static 메소드 ) 작성
     //  >> 초기화를 해야 한다면 초기화까지 Factory메소드가 담당

    public  static ContactDetailFragment newInstance(int index){
        ContactDetailFragment fragment = new ContactDetailFragment();
//        fragment.setIndex(index);//Fragment에 index값으로 초기화

        //Fragment의 Argument 설정

        //1, Bundle객체 필요
        Bundle bundle = new Bundle();

        //Bundle값에 key값과 인덱스 넣어줌
        bundle.putInt(ARG_CONTACT_INDEX, index);
        fragment.setArguments(bundle);//<<fragment에 bundle값을 넣어주는 메소드
        //안드로에드에서 데이터를 저장하고 저장한 값을 읽는, LAB클래스를 대신하는게 Bundle이다

        return fragment;
    }



    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 만들이 전에 onCreate에서 설정되어있는 Arguments 데이터(index)를 읽음
        Bundle args = getArguments();
        //메인이 아니라 DetailFragment가 Argument를 호출 할 경운 안될것이다
        //getArgumrnt만 하게 되면 set 한 것이 없어 불러 올 수 없을 것이기 때무넹 반드시 Null체크 해야 한다

        if(args != null){
            index = args.getInt(ARG_CONTACT_INDEX);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail_fregment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        contactImage = view.findViewById(R.id.detailPhoto);
        contactName = view.findViewById(R.id.detailName);
        contactPhone = view.findViewById(R.id.detailPhone);
        contactEmail = view.findViewById(R.id.detailEmail);
        btn = view.findViewById(R.id.btnDetail);

        //연락처 정보를 가지고 있는 ArrayList
        List<Contact> list = ContactLab.getInstance().getContactList();
        Contact contact = list.get(index);

        //모든 View의 내용을 설정
        contactImage.setImageResource(contact.getPhotoId());
        contactName.setText(contact.getName());
        contactPhone.setText(contact.getPhone());
        contactEmail.setText(contact.getEmail());



    }
}
