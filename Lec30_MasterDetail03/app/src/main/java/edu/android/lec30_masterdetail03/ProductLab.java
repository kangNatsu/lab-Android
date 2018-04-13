package edu.android.lec30_masterdetail03;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-26.
 *
 * ------
 * DAO Class
 */

public class ProductLab {

    public static final int[] PRODUCT_PHOTOS = {
            R.drawable.pr1, R.drawable.pr2, R.drawable.pr3, R.drawable.pr4,
            R.drawable.pr5, R.drawable.pr6, R.drawable.pr7, R.drawable.pr8,
            R.drawable.pr9, R.drawable.pr10, R.drawable.baseball,
            R.drawable.volleyball,R.drawable.basketball
             };

    public List<Product> productList = new ArrayList<>();

    public static ProductLab instance = null;

    private ProductLab() {
        makeDummyData();
    }

    private void makeDummyData() {

        for (int i = 0; i<100 ; i++){
            Product product = new Product(i, "Name :: " + i,
                                                   i ,
                                               "Decription ::" + i,
                                                PRODUCT_PHOTOS[i % PRODUCT_PHOTOS.length]);

            productList.add(product);
        }

    }

    public List<Product> getProductList(){

        return productList;
    };

    protected static ProductLab getInstance(){
        if(instance != null){
            instance = new ProductLab();
        }
        return instance;
    }

}
