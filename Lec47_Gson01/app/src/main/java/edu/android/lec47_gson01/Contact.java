package edu.android.lec47_gson01;

public class Contact {

    private int id;
    private String name;
    private String phone;

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ID :" + id + "\n"
                +"NAME : "+ name + "\n"
                +"PHONE : " + phone;
    }
}
