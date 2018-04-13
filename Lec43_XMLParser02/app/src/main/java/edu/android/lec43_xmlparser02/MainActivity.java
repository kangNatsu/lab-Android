package edu.android.lec43_xmlparser02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="lec43" ;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void clickPerser(View view) throws XmlPullParserException {

        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;

        try {
            in = getAssets().open("subway.xml");
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);

            SubwayXmlPullParser subwayParser = new SubwayXmlPullParser();
            List<Subway> list = subwayParser.getSubways(br);

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < list.size(); i++){
                builder.append("Index : " + i)
                        .append(list.get(i).toString())
                        .append("\n");
            }
            textView.setText(builder.toString());

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }finally {
            try {
                br.close();
            } catch (IOException e) {
               Log.e(TAG,e.getMessage());
            }
        }


    }
}
