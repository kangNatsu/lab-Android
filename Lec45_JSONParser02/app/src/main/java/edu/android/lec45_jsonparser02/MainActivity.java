package edu.android.lec45_jsonparser02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static edu.android.lec45_jsonparser02.Contact.ContactJsonVariables.*;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "contacts.json";

    private TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

    }

    public void onClickWrite(View view) {
       JSONArray jsonArray=  makeJsonArray();

       textView.setText(jsonArray.toString());

       writeJsonArray(jsonArray);
    }//end onClickWrite

    private void writeJsonArray(JSONArray jsonArray) {

        OutputStream out = null;
        OutputStreamWriter writer = null;
        //Bufferd Writer까지  쓸 필요는 없다 > 데이터가 많이 크지 않기 때문에
        BufferedWriter bw = null;

        try {
            out = openFileOutput(FILE_NAME,MODE_PRIVATE);
            writer = new OutputStreamWriter(out,"UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(jsonArray.toString());//**********
            //toString >> 문자열로 변환시 사용

            Toast.makeText(this, "파일 생성 성공",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void onClickRead(View view) {

        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null; //없어도 되지만 데이터가 커질 경우를 생각해서
        StringBuilder  builder = new StringBuilder();

        try {
            in = openFileInput(FILE_NAME);
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);
            String line = br.readLine();// 파일 읽을 준비

            while(line != null){// 읽을 내용이 있으면
                builder.append(line);// 한줄이 있으면  builder에 붙여주겠다
                line = br.readLine();// 파일 읽음 >> 다시 올라가서 읽을 문장이 있는지 없는지 확인인
           }
          /*  -------------여이까지가 파일에서 데이터를  읽어들이는 업무----------*/
          //파일에서 읽은 문자열을 JSONArray 로 변환
            JSONArray jsonArray = new JSONArray(builder.toString());//builder.toString() > File(contacts.json)에 있는 문자열 전체(현재 원소 4개)
            //JSONArray를 새성하는데 builder에 문자열이 다 들어가 있으므로 그걸 생성자에 넣어버리면 JSONArray가 됨
            //JSON 배열의 원소 갯수만큼 반복하면서 JSON 객체를 꺼냄 - 반복
            for (int i = 0; i < jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                // >> JSONArray는 이름 번호가 들어가있는 배열(파일 열었을때 내용)
                //Object 안에서 꺼냄
                int id = obj.getInt(VAR_ID);
                String name = obj.getString(VAR_NAME);
                String phone = obj.getString(VAR_PHONE);

                //생성자
                Contact contact= new Contact(id, name, phone);
                textView2.append(contact.toString());
                textView2.append("\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }//end onClickRead


    private JSONArray makeJsonArray() {

        //JSON 배열 (Array)을 생성
        JSONArray jsonArray = new JSONArray();// [] >> 비어있는 JSON 배열

        //Contact 인스턴스를 생성
        Contact c1 = new Contact(1, "Vagabond", " 010.0616.0429");
        Contact c2 = new Contact(2, "Subalblang", " 010.1234.5678");
        Contact c3 = new Contact(3, "Elice", " 010.1111.2222");
        Contact c4 = new Contact(4, "Suscide", " 010.3215.8546");

        //Contact를 JSONObject호 변환한 객체를 JSON 배열에 저장
        jsonArray.put(c1.toJsonobj());// [{}]
        jsonArray.put(c2.toJsonobj());// [{},{}]
        jsonArray.put(c3.toJsonobj());
        jsonArray.put(c4.toJsonobj());

        return jsonArray;
    }
}
