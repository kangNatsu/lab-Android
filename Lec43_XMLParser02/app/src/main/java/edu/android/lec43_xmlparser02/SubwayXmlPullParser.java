package edu.android.lec43_xmlparser02;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SubwayXmlPullParser {

    //xml 파일에서 사용되는 TAG들의 이름을 상수로 정의
    //contact.xml 에서 필요한건 percon의 시작과 끝 사이에 무언가 있고 name의 시작과 끝 사이에 있는 값등을 정의
    private static final String TAG_LINE = "line";
    private static final String TAG_SUBWAY_STAR = "subwayBag";
    private static final String TAG_SUB_NAME = "name";
    private static final String TAG_SUB_ADRES = "adress";
    private static final String TAG_STA_ADRE = " staAdress";
    private static final String TAG_STATION_TEL = "phone";


    //xml 파일을 분석해서 데이터를 저장 할 ArrayList<> 를 선언
    private List<Subway> subways = new ArrayList<>();


    //xml 파이렝서 한사람의 연락처를 저장 할 수 있는 데이터 타입을 선언
    private Subway subway;


    //xml 파이렝서 시작 태그와 앤드 태그 사으이 문자열을 저장하는 변수 선언
    private String text;


    // List 리턴해야 해서 List인 클래스를 하나 만들기 위해서


    public List<Subway> getSubways(Reader reader)
            throws XmlPullParserException, IOException {
        List<Subway> subways = new ArrayList<>();
        //TODO : contact.xml 파일을 분석해서 ArrayList 만들고 리턴(ArrayList 에 add()형식으로 더해 contacts에 값을 주고 리턴하겠다)

        // 1>xmlPullParserFactory 인스턴스 생성
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();


        // 2> xmlPullParser 인스턴스를 생성
        XmlPullParser parser = factory.newPullParser();


        // 3> XmlPullParser 겍체에게 XML 파일을 읽을 수 있는 reader를 설정
        parser.setInput(reader);


        // 4>parser 객체를 가지고 xml파일을 분석 -> 기능을 주세요, 첫번째 element를 읽어 달라,
        // 이게 끝이 아니면 일을 다 하고 while문 끝나기 전에 다음거 읽어달라

        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            Log.i(TAG, "eventType : " + eventType);
            String tagName = parser.getName();
            Log.i(TAG, "Tag Type : " + tagName);

            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    Log.i(TAG,"START DOCUMENT");
                    break;
                case XmlPullParser.START_TAG:
                    Log.i(TAG, "START TAG");
                    if (tagName.equals(TAG_SUBWAY_STAR)){
                        subway =new Subway();
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    Log.i(TAG,"TEXT" + text);
                    break;
                case XmlPullParser.END_TAG:
                    Log.i(TAG,"END TAG");
                    if(tagName.equals(TAG_SUBWAY_STAR)){
                        subways.add(subway);
                    }else if(tagName.equals(TAG_LINE)){
                        subway.setLine(text);
                    }else if(tagName.equals(TAG_SUB_NAME)){
                        subway.setStaitionName(text);
                    }else  if(tagName.equals(TAG_STA_ADRE)){
                        subway.setAdres(text);
                    }else  if (tagName.equals(TAG_STA_ADRE)){
                        subway.setRdnmadr(text);
                    }else if (tagName.equals(TAG_STATION_TEL)){
                        subway.setTelNo(text);
                    }
                    break;

            }
            eventType = parser.next();
        }

        return subways;
    }
}