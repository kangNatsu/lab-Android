package edu.android.lec23_listview02;

/**
 * Created by user on 2018-03-21.
 * 아이템 하나에 대한 정보를 저장하는 MODEL 클래스 (순수하게 데이터만 저장하는)
 */

public class Drink {

    //음료수들이 DB에 저장되어 있다면?
    //  ->> 음료 이름, 정보, 가격, 사진, 위치 등등이 있다.
    // PrimaryKey가 있어야 한다(음료 이름이 하나씩 증가하는 걸로 있으면 좋다 - 겹치는 음료이름이 존대)

    private int id;// DB에서 primaryKey
    private String drinkName;//음료수 이름
    private String description;// 음료수 설명
    private int price;// 음료 가격
    private int imageId;// 임료수 이미지 리소스 이미지

    //생성자 - Alt+Insert -> Generation ->constructer
    public Drink(int id, String drinkName, String description, int price, int imageId) {
        this.id = id;
        this.drinkName = drinkName;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
    }

    //getter&setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return drinkName +" || " + price + "원";
    }
}
