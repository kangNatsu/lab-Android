package edu.android.lec46_jsonparser03;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static edu.android.lec46_jsonparser03.Contact.ContactJsonVariables.*;
import static edu.android.lec46_jsonparser03.MainActivity.TAG;

public class Contact {


    interface ContactJsonVariables{
        //Contact의 멤버 변수 3개를 가지고 생가하면 된다
        String VAR_ID = "ID";
        String VAR_NAME = "NAME";
        String VAR_PHONES = "PHONES"; // JSON 배열을 저장하기 위한 변수 이름
        String VAR_TYPE = "TYPE";
        String VAR_PHONE_NO = "PHONE NO.";
    }

    //내부 클래스 정의 -  전화번호는 개인 전화번호, 집 회사 등등 다양한 종류가 있으므로
    static class Phone{
        private String type;
        private String phoneNo;

        public JSONObject toJsonObj() {
            JSONObject obj = new JSONObject();
            try {
                obj.put(VAR_TYPE, type);
                obj.put(VAR_PHONE_NO, phoneNo);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }

            return obj;
        }

        //생성자

        public Phone(String type, String phoneNo) {
            this.type = type;
            this.phoneNo = phoneNo;
        }

        public String getType() {
            return type;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

    }// end class phone

    //멤버변수
    private int id;
    private String name;
    private List<Phone> phones;//베열은 선언시 갯수를 정해두고 시작해야 하는데 ArrayList는 아니므로 ArrayList 사용

    //생성자
    public Contact(int id, String name, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Phone> getPhones() {
        return phones;
    }


    @Override
    public String toString() {
        return "\t ID :: " + id +"\n"
                +"\t NAME :: " + name + "\n"
                +"\t PHONE NO. ::" + phones;
        //무조건 만들 수 없고 ArrayList의 갯수를 알아야지 반복문에 쓸 수 있다
    }

    public JSONObject toJsonObj(){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(VAR_ID,id);// {"id" : id}
            jsonObj.put(VAR_NAME, name); // {"id":id, "name":name}

            //{"id":id ,"name":name,"phones":[.....(전화 번호는 여러개 일 수 있으므로 :(콜론)뒤에 배열 -> 즉 배열부터 생성해야 한다)] }
            JSONArray jsonArray = new JSONArray();
            for (Phone p : phones){//ArrayList에 들어가있는 phones의 갯수만큼 Objec에 넣는다
                jsonArray.put(p.toJsonObj());//[{},{},{},.....]
            }
            jsonObj.put(VAR_PHONES,jsonArray);

        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }

        return jsonObj;
    }

}// end class Contact























