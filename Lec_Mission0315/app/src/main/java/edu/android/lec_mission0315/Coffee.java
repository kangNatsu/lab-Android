package edu.android.lec_mission0315;

import java.io.Serializable;

/**
 * Created by user on 2018-03-16.
 */

public class Coffee implements Serializable {
    //커피의 이름, 기본 가격
    //MODEL-CLASS

    //멤버변수
    private String name;

    //생성자 Alt + Insert -> Construcret + name + enter
    public Coffee(String name) {
        this.name = name;
    }

    //getter메소드
    public String getName() {
        return name;
    }

    public int getPrise(){
        int price = 0;//아메, 라떼, 모카를 제외하면 0원으로 리턴
        switch(name){
            case "AMERICANO" :
                price = 4100;
                break;
            case "LATTE":
                price = 4600;
                break;
            case "MOCHA":
                price = 5100;
                break;
        }
        return price;
    }
}
