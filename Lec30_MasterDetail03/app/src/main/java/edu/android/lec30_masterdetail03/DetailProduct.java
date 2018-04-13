package edu.android.lec30_masterdetail03;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class DetailProduct extends AppCompatActivity {

    private static final String EXTRA_PRODUCT_INDEX = "selected_pro";

    public static Intent newIntent(Context context, int index){
        Intent intent = new Intent(context, DetailProduct.class);
        intent.putExtra(EXTRA_PRODUCT_INDEX, index);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Intent intent = getIntent();
        int index = intent.getIntExtra(EXTRA_PRODUCT_INDEX, 0);
        FragmentManager manager = getSupportFragmentManager();
        ProductDetail fragment = (ProductDetail) manager.findFragmentById(R.id.fragmentDetail);
        fragment.setIndex(index);


    }
}
