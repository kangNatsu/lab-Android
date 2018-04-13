package edu.android.lec48_gson02;

import java.util.List;

/**
 * MODEL 클래스
 * 데이터
 */

public class Contact {
    private int id;
    private String name;
    private List<Phone> phones;//내부 클래스를 변수로 가지고 있는 List 타입
    private String email;//이메일도 개인, 직장등 다를 수 있으므로 내부 클래스 만들어 사용 할 수 있다.

    public Contact(int id, String name, List<Phone> phones, String email) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID : ").append(id).append("\n")
                .append("NAME : ").append(name).append("\n");
        builder.append("PHONE NUM LIST :\n" );
        for(Phone p : phones){
            builder.append("\tTYPE : ").append(p.phoneType).append("\n")
                    .append("\tPHONRNO. : ").append(p.phoneNo).append("\n");
        }
        builder.append("E-MAIL : ").append(email);


        return builder.toString();
    }

    public static class Phone{//내부 클래스
        private String phoneType;
        private String phoneNo;

        public Phone(String phoneType, String phoneNo) {
            this.phoneType = phoneType;
            this.phoneNo = phoneNo;
        }



    }//end Phone


    //생성자, getter&setter, toString() 필요하면 만들면 된다

}//end Contact
