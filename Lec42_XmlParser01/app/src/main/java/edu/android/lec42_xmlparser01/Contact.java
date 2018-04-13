package edu.android.lec42_xmlparser01;


/**
 * 한 사람에 대한 연락처를 저장하는 클래스
 *  MODEL Class
 *
 *  xmlfullparser를 사용하고 contact의 내용들을 사용할 수 있는 클래스 만들어야 한다
 */

public class Contact {

    private String name;
    private String phone;

    public Contact(){}
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
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
        return "  NAME :: " + name + "\n" + "  PHONE :: " + phone;
    }
}
