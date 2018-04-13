package edu.android.lec_mission0315;

import java.io.Serializable;

/**
 * Created by user on 2018-03-16.
 */

public class CoffeeOrder implements Serializable {

    //커피 주문에 관련된 클래스 - 음료 종류, 컵 사이즈, 수량 ...


    private Coffee coffee;//음료 종루
    private String size;//컵 사이즈
    private int quantitiy;//수량

    //생성자


    public CoffeeOrder(Coffee coffee, String size, int quantitiy) {
        this.coffee = coffee;
        this.size = size;
        this.quantitiy = quantitiy;
    }

    //메소드
    public Coffee getCoffee() {
        return coffee;
    }

    public String getSize() {
        return size;
    }

    public int getQuantitiy() {
        return quantitiy;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantitiy(int quantitiy) {
        this.quantitiy = quantitiy;
    }

    public void setCoffee(Coffee coffee) {

        this.coffee = coffee;
    }

    //멤버변수
    public int getOrderPrice() {
        //주문 비용을 리턴해주는 메소드
        // 1.기본 가격은 멤버변수 edu.android.lec_mission0315.Coffee 에서  getPrice하면 됨
        // 2.사이즈가 달라짐에 따라 추가금액을 더해준다
        // 3.주문수량을 곱해준다

        // 1. 커피 종류에 따른 기본 가격
        int total = coffee.getPrise();

        // 2. 사이즈별 기본 금액 (Tall을 기본 가격으로 설정해보자)
        switch (size) {
            case "Short":
                total -= 500;
                break;
            case "Grande":
                total += 500;
                break;
            case "Venti":
                total += 1000;
                break;
        }
        //총 결제 금액을 리턴
        return total * quantitiy;

    }

    //toString()을 Override한 이유 : 나중에 toString()을 호출했을때 출력을 편하게 하기 위해서
    @Override
    public String toString() {
        return String.format("음료 : %s - %d원\n 사이즈 : %s\n 주문 수량 : %s잔\n 최종 결제 금액 : %d원",
                coffee.getName(), coffee.getPrise(), size, quantitiy, getOrderPrice());
    }
}

