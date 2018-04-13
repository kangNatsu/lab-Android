package edu.android.lec46_jsonparser03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import edu.android.lec46_jsonparser03.Contact.ContactJsonVariables.*;

import static edu.android.lec46_jsonparser03.Contact.ContactJsonVariables.*;


//

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "lec46";
    private static final String FILE_NAME = "contact.json";

    private TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void onClickWrite(View view) {

        // Contact를 생성하기 위해서 Phone을 원소로 갖는 ArrayList를 생성
        List<Contact.Phone> phones = new ArrayList<>();
        Contact.Phone p1 = new Contact.Phone("모바일", "010-1234-5678");
        phones.add(p1);
        Contact.Phone p2 = new Contact.Phone("집", "02-1111-5678");
        phones.add(p2);

        Contact c = new Contact(1, "오쌤", phones);

        // Contact 객체를 JSON 객체로 변환(어떻게 JSONObject로 바꿀 수 있겠는가)
        JSONObject jsonObject = c.toJsonObj();

        // JSON 객체의 내용을 TextView에 씀
        textView.setText(jsonObject.toString());

        OutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            out = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(jsonObject.toString());
            Toast.makeText(this, "파일 생성 성공", Toast.LENGTH_SHORT).show();



        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

    }
    }//end onClickWrite




    public void onClickRead(View view) {

        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();
        try {
            in = openFileInput(FILE_NAME);
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                builder.append(line);
                line = br.readLine();
            }

            // 파일에서 읽은 전체 문자열을 JSONObject로 변환
            JSONObject jsonObj = new JSONObject(builder.toString());
            int id = jsonObj.getInt(VAR_ID);
            String name = jsonObj.getString(VAR_NAME);
            JSONArray jsonArray = jsonObj.getJSONArray(VAR_PHONES);

            List<Contact.Phone> phones = new ArrayList<>();
            for (int i = 0 ; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String type = obj.getString(VAR_TYPE);
                String phoneNo = obj.getString(VAR_PHONE_NO);
                Contact.Phone p = new Contact.Phone(type, phoneNo);
                phones.add(p);
            }

            Contact contact = new Contact(id, name, phones);
            textView2.setText(contact.toString());

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    }//end onClickRead

