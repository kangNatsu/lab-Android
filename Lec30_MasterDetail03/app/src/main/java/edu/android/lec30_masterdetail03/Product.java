package edu.android.lec30_masterdetail03;

/**
 * Created by user on 2018-03-26.
 *
 * 데이터 (MODEL) 클래스
 */

public class Product {

    private int productId;// ptimary Key
    private  String  productName;
    private int price;
    private String description;
    private int photoId;
    //그 외 카테고리, 등등을 넣어 줄 수 있다
    //어떤 기능이 필요하냐에 따라 MODEL Class의 구성은 달라진다

    //TODO : 생성자, getter & setter,
    //TODO : 이걸 가지고 DAO Class하나 생성
    //TODO ::

    //생성자

    public Product(int productId, String productName, int price, String description, int photoId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.photoId = photoId;
    }

    //getter & setter


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
