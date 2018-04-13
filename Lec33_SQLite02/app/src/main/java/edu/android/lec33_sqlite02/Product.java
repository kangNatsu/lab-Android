package edu.android.lec33_sqlite02;

import android.provider.BaseColumns;

/**
 * Created by user on 2018-03-27.
 *
 * MODEL CLASS
 * 데이터 클래스 - 테이터 베이스의 테이블
 *
 */

public class Product {

    //테이블의 이름, 테이블의 컬럼 이름들을 상수로 정의
    public static abstract class ProductEnity/*테이블의 이름과 컬럼 이름들을 상수로 가지고 있는*/
           implements BaseColumns/*BaseColumns 안에는 _ID가 이미 정의되어 있어 이걸 사용 한다*/{
        //TODO
        public static final String TABLE_NAME = "products";
        public static final String COL_NAME = "pname";
        public static final String COL_PRICE = "price";
        public static final String COL_DESC = "description";
           }

    private int productId;//primary key, BaseColumns을 상속하면 따로 상수로 만들 필요x
    private String productname;
    private int price;
    private String  description;

    //TODO


    public Product(int productId, String productname, int price, String description) {
        this.productId = productId;
        this.productname = productname;
        this.price = price;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    @Override
    public String toString() {
        return String.format("Products[ ID : %d || NAME : %s || PRICE : %s || DESC : $s ]",
                              productId, productname, price, description);
    }
}
































