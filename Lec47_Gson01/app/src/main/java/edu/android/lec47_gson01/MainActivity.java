package edu.android.lec47_gson01;

import android.arch.lifecycle.LifecycleOwner;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String OBJECT_FILE = "contact.json";//contact 1개 저장
    private static final String ARRAY_FILE = "contacts.json";//contact 여러개 저장
    private static final String TAG = "lec47";

    private TextView textView, textView2;

    //Java 객체체 <---> JSON 기능을 갖는 Gson 변수를 선언
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void onClickObjectWrite(View view) {
        //Contact 인스턴스 생성(Java 객체 생성)
        Contact contact = new Contact(1, "DAZAI", "010.0429.0616");

        //Java 객체(contact )를 JSON 형태의 문자열로 변환
        String jsonString = gson.toJson(contact);

        //변환된 문자열을 textView에 쓰기
        textView.setText(jsonString);

        //JSON 문자열을 파일에 쓰기 - 메소드 만들어 사용
        writeToFile(OBJECT_FILE,jsonString);

    }

    private void writeToFile(String fileName, String jsonString) {


        OutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;

        try {
            out = openFileOutput(fileName, MODE_PRIVATE);
            writer = new OutputStreamWriter(out,"UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(jsonString);// 버튼 눌렀을때 이미 만들어진 문자열 (jsonString)이 넘어 왔기 때문에 그걸 써주기만 하면 됨

            Toast.makeText(this, "JSON 객체 파일 생성 성공", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());

        } finally {

            try {
                bw.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }

    }

    public void onClickObjectRead(View view) {
        //파일에 저장된 JSON 포멧으로 되어있는 문자열 읽음
        String jsonString = readFromFile(OBJECT_FILE);

        // JSON 포멧의 문자열을 Java 객체(contact)로 변환
        //    >>JSON --->> Java ()
        Contact contact = gson.fromJson(jsonString, Contact.class);
        //파일에서 읽은 내용이 있으면 jsonString형태에서  Conatact 클래스 타입으로 변환해 주세요

        textView2.setText(contact.toString());
    }

    private String readFromFile(String fileName) {

        StringBuilder builder = new StringBuilder();
        //builder에 문자열들을 계속해서 붙여주겠다

        //파일에서 읽는다
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;

        try {
            in = openFileInput(fileName);
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);

            String line = br.readLine();
            while (line != null){
                builder.append(line);
                line = br.readLine();

            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }finally {
            try {
                br.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return builder.toString();
    }

    public void onClickWriteArray(View view) {
        //Contact를 저장하는 ArrayList를 생성, 10개 저장
        List<Contact> list = new ArrayList<>();
        for (int i = 0; i<= 10; i++){
            Contact contact = new Contact(i,"NAME" + i, "PHONE" + i);
            list.add(contact);
        }

        //ArrayList<Conatact> 객체 ----> JSON Array 포멧의 문자열로 변환
        String jsonString = gson.toJson(list);
        textView.setText(jsonString);

        // JSON 포멧 문자열을  파일에 씀
        writeToFile(ARRAY_FILE,jsonString);

    }

    public void onClickReadArray(View view) {
        //JSON Array 포멧으로 파일에 저장된 문자열을 읽음
        String jsonString = readFromFile(ARRAY_FILE);

        //JSON Array 포멧 ---> ArrayList<Conatact> 으로 변환환
        TypeToken<ArrayList<Contact>> token = new TypeToken<ArrayList<Contact>>(){};
         //                                   >> {} : 안에 아무것도 없는 익명클래스
        //TypeToken<>() 생성자는 protected 로 선언되어 있기 때문에,
        // 다른 패키지에서는 상속받는 클래스만 호출할 수 있음
        //     >>> 익명 클래스 선언하고, 생성자를 호출 해야 함!
        
        Type type = token.getType();

        // >> 클래스를 전부 펼쳐서 어떻게 생겼는지 확인하겠다 >> reflect 기능: reflect 기능을 가진 게 [TypeToken<>]
        //이 TypeToken 안에 들어가는 변수는 있는게 전부 분해가 된다

        ArrayList<Contact> list = gson.fromJson(jsonString,type);

        //Java 객체로 변환된 내용을 textView2에 씀
        textView2.setText("");
        for (Contact c : list){
            textView2.append(c.toString());
            textView2.append("\n- - - - - - - - - - - - - - -\n");
        }

    }}





























