package edu.android.lec30_masterdetail03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
implements ListDetail.ProductListDetailCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.recycler);
        if(view != null){

        }else {
            
        }
    }

    @Override
    public void onproductSelected(int position) {

    }
}
