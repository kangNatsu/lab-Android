package edu.android.lec42_xmlparser01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "lec42";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

    }

    public void parseXml(View view) throws XmlPullParserException {
        //assets/contacts.xml 파일을 오픈
        //  >> asset 폴더에 있는 내용을 가지고 오는 거기 때문에 경로 필요 없이 폴더 밑의 파일 이름만 적어 주면 된다(open 메소드 사용)

        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br =null;
        try {
            // assets/contacts.xml 파일을 오픈
           in = getAssets().open("contact.xml");
           reader = new InputStreamReader(in, "UTF-8");
           br = new BufferedReader(reader);

           ContactXmlPullParser contactParser = new ContactXmlPullParser();
            List<Contact> list = contactParser.getContacts(br);
            //Exception

            //TODO :  list 의 내용을 TextView

            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < list.size(); i++){
                builder.append("Index : " + i + "\n")
                        .append(list.get(i).toString())
                        .append("\n");

            }
            textView.setText(builder.toString());

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());

        }finally {
            try {
                br.close();
            } catch (Exception e) {
                Log.e(TAG,e.getMessage());
            }
        }


    }
}
