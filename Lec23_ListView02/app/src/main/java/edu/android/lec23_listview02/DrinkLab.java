package edu.android.lec23_listview02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-21.
 *
 * Dtink의 리스트를 관리하는 DAO와 유사한 클래스
 *
 *      - DB에서 정보를 읽어오거나 그걸 저장하는 역할을 나중에 하게 될 Class
 */

public class DrinkLab {
    //실제 DrinkList가 할 일은 Drink 클래스의 List를 관리 해 줘야 한다
    private List<Drink> drinkList;

    //Singleton 디자인 패턴 적용
    private static DrinkLab inatance = null;

    private DrinkLab(){
        drinkList = new ArrayList<>();
        // DB를 실제 만들지 않았으므로 dummy데이터를 넣겠다
        makeDummyData();
    }

    //ArrayList에 데이터를 만듬
    private void makeDummyData() {
        Drink d1 = new Drink(1, "아메리카노",null, 4100, -1);
        //메소드나 생성자의 매개변수가 어떤 의미인지를 보여주는데 이는 ART가 자동으로 보여준다
        drinkList.add(d1);

        Drink d2 = new Drink(2, "카페라떼",null, 4600, -1);
        drinkList.add(d2);

        Drink d3 = new Drink(3, "카페모카",null, 5100, -1);
        drinkList.add(d3);

        Drink d4 = new Drink(4, "돌체라떼",null, 5600, -1);
        drinkList.add(d4);

        Drink d5= new Drink(5, "카라멜 마끼야토",null, 5100, -1);
        drinkList.add(d5);

        Drink d6 = new Drink(6, "비엔나 커피",null, 4700, -1);
        drinkList.add(d6);

        Drink d7 = new Drink(7, "티 라떼(블랙, 얼그레이, 차이)",null, 5700, -1);
        drinkList.add(d7);

        Drink d8 = new Drink(8, "레모네이드(패션, 블랙, 그린)",null, 5700, -1);
        drinkList.add(d8);

        Drink d9 = new Drink(9, "에스프레소 프라푸치노",null, 5800, -1);
        drinkList.add(d9);

        Drink d10 = new Drink(10, "그린티 프라푸치노",null, 6800, -1);
        drinkList.add(d10);

        Drink d11 = new Drink(11, "딸기 요거트 블랜디드",null, 5300, -1);
        drinkList.add(d11);

        Drink d12 = new Drink(12, "망고 바나나 블랜디드",null, 6300, -1);
        drinkList.add(d12);

        Drink d13 = new Drink(13, "얼그레이 에이드",null, 6300, -1);
        drinkList.add(d13);





    }

    public static DrinkLab getInatance(){
        if (inatance == null){
            inatance = new DrinkLab();

        }
        return  inatance;
    }

    public List<Drink> getDrinkList() {
        //getter의 변수 타입은 변수의 타입이 되어야 하고 매개변수는 필요 없다
        return drinkList;
    }
}
