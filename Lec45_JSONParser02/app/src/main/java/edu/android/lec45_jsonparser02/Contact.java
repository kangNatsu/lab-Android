package edu.android.lec45_jsonparser02;

import org.json.JSONException;
import org.json.JSONObject;

import static edu.android.lec45_jsonparser02.Contact.ContactJsonVariables.*;

/**
 * 내부 클래스
 */

public class Contact {
    public interface ContactJsonVariables {

        //JSON 객체에서 사용할 변수 이름들을 내부 인터페이스에서 상수로 정의
        String VAR_ID = "ID";
        String VAR_NAME = "NAME";
        String VAR_PHONE = "PHONE";
        //인터페이스에서 상수 정의 할 때는 public static final이 붙는데 생략 가능하다(클래스에서는 생략 불가)

    }

    //Contact 클래스의 멤버변수
    private int id;
    private String name;
    private String phone;


    //생성자
    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\t ID :: " + id + "\n"
                + "\t NAME :: " + name + "\n"
                +"\t PHONE :: " + phone + "\n";
    }

    //Contact 인스턴스를 JSONObject로 변환해서 리턴하는 메소드
    public JSONObject toJsonobj(){
        //어떤 클래스가 있으면 JSON으로 변환하겠다
        JSONObject jsonObj = new JSONObject();//{} >> 비어있는 Object

        try {
            jsonObj.put(VAR_ID, id);// {"id": id}
            jsonObj.put(VAR_NAME, name);//{"id": id, "name": name}
            jsonObj.put(VAR_PHONE,phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObj;

    }

}
