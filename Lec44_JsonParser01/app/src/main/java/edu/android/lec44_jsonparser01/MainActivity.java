package edu.android.lec44_jsonparser01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //JSON 객체의 변수 이름으로 사용할 상수들을 정의
    private static final String JSON_VAR_ID ="ID";
    private static final String JSON_VAR_NAME = "NAME";
    private static final String JSON_VAR_PHONE = "PHONE";
    private static final String JSON_VAR_EMAIL = "E-MAIL";

    private TextView textView, textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void writeJsonObject(View view) {

        try {
            //Json Object 인스턴스 생성
            JSONObject jsonObj = new JSONObject();

            //JSON 객체는 name/value 쌍으로 데이터를 저장한다
            jsonObj.put(JSON_VAR_ID, 1000);
            jsonObj.put(JSON_VAR_NAME,"DAZAI");
            jsonObj.put(JSON_VAR_PHONE,"010-0429-0616");
            jsonObj.put(JSON_VAR_EMAIL,"boomghoSD@yoko.com");

            //생성된 JSON객체의 내용을 TextView에서 확인 >>
            textView.setText(jsonObj.toString());

            //JSON 객체를 파일에 쓰기 >>  코드가 길어져 따로 메소드로 뺀다
            writeToFile(jsonObj);




        } catch (JSONException e) {
            e.printStackTrace();


        }//end writeJsonObject try-catch


    }//end writeJsonObject



    public void readJsonObject(View view) {
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();

        try {
            // 파일에 저장된 문자열을 읽음
            in = openFileInput("contact.json");
            //파일 이름이 아니라 https:// 주소에서 읽어 올 수도 있다
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null){
                builder.append(line);
                line = br.readLine();
            }

            //파일에서 읽은 문자열을 JSONObject로 변환
            JSONObject jsonObj = new JSONObject(builder.toString());
            int id = jsonObj.getInt(JSON_VAR_ID);
            String name = jsonObj.getString(JSON_VAR_NAME);
            String phone = jsonObj.getString(JSON_VAR_PHONE);
            String email = jsonObj.getString(JSON_VAR_EMAIL);

            //JSON 객체(Object) 에서 읽은 값(value)들을 TextView에 씀
            textView2.setText("");
            textView2.append("ID ::" + id + "\n");
            textView2.append("NAME :: " + name + "\n");
            textView2.append("PHONE :: "+ phone +"\n");
            textView2.append("E-MAIL :: " + email + "\n" );


        } catch (Exception e) {
            e.printStackTrace();
        }


    }//end readJsonObject

    private void writeToFile(JSONObject jsonObj) {
        OutputStream out = null;
        OutputStreamWriter writer = null;
        // 메모리에서 읽고 쓰겠다 >>Buffered 붙은 것 쓰면 됨
        BufferedWriter bw = null;

        try {
            //openFileOutput (fileName, file open Mode)
            //  >> 경로 ;  data/data/앱패키지/files 폴더 아래 -> write 할 수 있는 FileOutputStream 을 생성성
            out = /*생성자 아니라 메소드 호출*/openFileOutput("contact.json", MODE_PRIVATE);
            writer = new OutputStreamWriter(out,"UTF-8"/*인코딩 타입*/);
            bw = new BufferedWriter(writer);
            bw.write(jsonObj.toString());

            Toast.makeText(this,"파일 생성 성공",Toast.LENGTH_SHORT).show();

        } catch ( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//end writeToFile

    // 프로그램 ==> OutputStream ==> 출력 장치(콘솔, 파일, 프린터, ..)
    //프로그램 ==> FileOutputStream(데이터를 보내는 통로) ==> 파일
    //프로그램 ==> OutputStreamWriter (인코딩 된 어떤 데이터를 써 주면)
    //     ==> FileOutputStream(OutputStreamWriter보다 먼저 생성 된 기본 통로) ==> 인코딩된 문자열을 파일에 써줌
    // 인코딩 되어 있는 정보를 보내고 싶을 경우 reader / writer붙여씀
    //     >> 기본 통로 생성 후 붙여 사용


}
