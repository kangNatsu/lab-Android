package edu.android.lec32_sqlite01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static final String TAG = "edu.android";

    //멤버변수
    private ContactDao dao;//어플리케이션이 시작될때 dao를 초기화
    private EditText editId, editName, editPhone, editEmail;
//    private Button btnInsert, btnUpdate, btnDelete, btnSelectAll, btnSelectId;
    private TextView textResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "MainActivity onCreate");
//        dao = ContactDao.getInstance(this);
        dao = ContactDao.getInstance(getApplicationContext());
        // MainAvtivity이므로 this로 줘도 되지만 Activity가 여러개일 경우 해당 클래스의 이름을 줘도 된다

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editPhone =findViewById( R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        textResult = findViewById(R.id.textResult);


    }

    //버튼의 onClick Event Listener Method
    public void insertContect(View view) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();
        Contact contact = new Contact(0, name, phone, email);
        long result = dao.insert(contact);

        textResult.setText("insert 결과 ||" + result);


    }

    public void selectAllContact(View view) {
        List<Contact> list = dao.select();
        StringBuffer buffer = new StringBuffer();
        //향상된for문 사용
        for (Contact c : list){
            buffer.append(c.toString()).append("\n");
        }
        textResult.setText(buffer.toString());

    }

    public void selectIdContact(View view) {
        int id = Integer.parseInt(editId.getText().toString());
        Contact c = dao.select(id);
        if(c!=null){
            editName.setText(c.getCname());
            editPhone.setText(c.getPhone());
            editEmail.setText(c.getEmail());
        }else{
            textResult.setText("해당 아이디에 대한 정보가 없습니다");
        }

    }
}
