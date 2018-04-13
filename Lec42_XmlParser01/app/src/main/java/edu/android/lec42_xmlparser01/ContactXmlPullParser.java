package edu.android.lec42_xmlparser01;


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
import java.util.List;

import static edu.android.lec42_xmlparser01.MainActivity.TAG;


/**
 *
 */


public class ContactXmlPullParser {

    //상수 정의
    //xml 파일에서 사용되는 TAG들의 이름을 상수로 정의
    //contact.xml 에서 필요한건 percon의 시작과 끝 사이에 무언가 있고 name의 시작과 끝 사이에 있는 값등을 정의
    private static final String TAG_PERSON = "person";
    private static final String TAG_NAME= "name";
    private static final String TAG_PHONE = "phone";

    //xml 파일을 분석해서 데이터를 저장 할 ArrayList<> 를 선언
    private List<Contact> contacts = new ArrayList<>();

    //xml 파이렝서 한사람의 연락처를 저장 할 수 있는 데이터 타입을 선언
    private Contact contact;

    //xml 파이렝서 시작 태그와 앤드 태그 사으이 문자열을 저장하는 변수 선언
    private String text;

    //이 클래스를 만드는 이유는 MainActivity에서 사용해야 해서 public,
     // List 리턴해야 해서 List인 클래스를 하나 만들기 위해서
    public List<Contact>  getContacts (Reader reader) throws XmlPullParserException, IOException {
        //TODO : contact.xml 파일을 분석해서 ArrayList 만들고 리턴(ArrayList 에 add()형식으로 더해 contacts에 값을 주고 리턴하겠다)

        // 1>xmlPullParserFactory 인스턴스 생성
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        // 2> xmlPullParser 인스턴스를 생성
        XmlPullParser parser = factory.newPullParser();

        // 3> XmlPullParser 겍체에게 XML 파일을 읽을 수 있는 reader를 설정
        parser.setInput(reader);

        // 4>parser 객체를 가지고 xml파일을 분석 -> 기능을 주세요, 첫번째 element를 읽어 달라,
        // 이게 끝이 아니면 일을 다 하고 while문 끝나기 전에 다음거 읽어달라 >> 반복
         int eventType = parser.getEventType();

        while(eventType != XmlPullParser.END_DOCUMENT){
            //TODO :
            Log.i(TAG, "event Type " + eventType);
            //XML 의 태그 이름을 리턴
            String tagName = parser.getName();
            Log.i(TAG, "Tag Name : " + tagName);

            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    Log.i(TAG,"START DOCUMENT");
                    break;
                case XmlPullParser.START_TAG:
                    Log.i(TAG, "START TAG");
                    if (tagName.equals(TAG_PERSON)) {
                        // person 시작 태그를 만났을 때 Contact 인스턴스를 생성
                        contact = new Contact();
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    Log.i(TAG,"TEXT ::"+ text);
                    break;
                case XmlPullParser.END_TAG:
                    //contact.xml에서 person의 endtag를 읽으면 이미 그 위의 이름과 전화번호를 읽었을 것이므로 그 endtag를 찾아 arraylist에 넣어준다
                    Log.i(TAG, "END TAG");
                    if(tagName.equals(TAG_PERSON)){
                        contacts.add(contact);
                    }else if(tagName.equals(TAG_NAME)){
                        contact.setName(text);
                    }else if(tagName.equals(TAG_PHONE)){
                        contact.setPhone(text);
                    }
                    break;
                    //END_DOCUMENT도 있으나 이미 while문에서 END_DOCUMENT가 아닌 경우만 이라 했기 때문에
                      //switch-case 구문에는 END_DOCUMENT가 오지 않는다
            }

            eventType = parser.next();
        }


        return contacts;
    }


}
