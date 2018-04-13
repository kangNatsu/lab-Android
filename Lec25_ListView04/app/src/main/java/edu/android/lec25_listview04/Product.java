package edu.android.lec25_listview04;

/**
 * Created by user on 2018-03-21.
 *
 * 아이템 하나의 정보를 저장하는 데이터 클래스(MODEL CLASS)
 */

public class Product {

    //멤버변수
    private String ptoductName;
    private int price;
    private String description;
    private int photoId;

    //TODO : 생성자, getter& setter


    public Product(String ptoductName, int price, String description, int photoId) {
        this.ptoductName = ptoductName;
        this.price = price;
        this.description = description;
        this.photoId = photoId;
    }

    public String getPtoductName() {
        return ptoductName;
    }

    public void setPtoductName(String ptoductName) {
        this.ptoductName = ptoductName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
