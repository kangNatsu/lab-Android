package edu.android.lec25_listview04;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-21.
 *
 * Dao 클래스 - 상품 목록을 관라
 * DA) 클래스는 Singleton디자인패턴으로 하는게 좋다
  */

public class ProductDao {

    private static final int[] PRODUCT_LIST = {
            R.drawable.android1, R.drawable.android2, R.drawable.android3,
            R.drawable.android4, R.drawable.android5, R.drawable.android_4_0_icecreamsandwich,
            R.drawable.android_4_1_jellybean,R.drawable.android_4_4_kitkat,
            R.drawable.android_5_0_lollipop, R.drawable.android_6_0_marshmallow,
            R.drawable.android_7_0_nougat,R.drawable.android_8_0_oreo,R.drawable.android_1_5_cupcake,
            R.drawable.android_1_6_donut,R.drawable.android_2_0_eclair, R.drawable.android_2_2_froyo,
            R.drawable.android_2_3_ginerbread, R.drawable.android_3_0_honeycomb

    };

    private List<Product> productList;


    //더미 데이터
    private static ProductDao instance = null;

    private ProductDao(){
        productList = new ArrayList<>();
        makeDummyFile();

    }

    private void makeDummyFile(){
        for (int i = 0; i < 100 ;i++){
            Product p = new Product("Product's Name ||" + i,

                                     i, "About thids products" + i,
                                    PRODUCT_LIST[i%PRODUCT_LIST.length]);
            productList.add(p);
        }


    }

    public static ProductDao getInstence(){
        if(instance == null){
            instance = new ProductDao();

        }
        return instance;
    }

    public List<Product> getProductLists(){
      return productList;
    }

}
