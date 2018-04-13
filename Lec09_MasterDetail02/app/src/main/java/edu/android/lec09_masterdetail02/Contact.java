package edu.android.lec09_masterdetail02;

/**
 * Created by user on 2018-03-23.
 *
 *----
 * 데이터 클래스 (MODEL CLASS)
 *   >>> 아이템 하나에개해 어떤 데이터를 저장 할 것이냐
 *   >>>>>>멤버변수, 생성다, getter & setter
 */

public class Contact {
    private int id; // 나중에  DB사용하는걸 위한 PrimaryKey
    private String name;
    private String phone;
    private String email;
    private int photoId;


    public Contact(int id, String name, String phone, String email, int photoId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoId = photoId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
