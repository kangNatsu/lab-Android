package edu.android.lec20_project;

;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
implements ListFragment.listSelectedListener{


    public static final String TAG = "edu.android";
    private  ListFragment listFragment;
    private ImageFragment imageFragment;
    private Spinner[] spinner = new Spinner[4];
    private ImageView cupcake, donut, eclair, froyo;

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        imageFragment = (ImageFragment) fm.findFragmentById(R.id.container);
//        Fragment fragment = fm.findFragmentById(R.id.container);
        if(imageFragment == null){
            FragmentTransaction transaction = fm.beginTransaction();
            imageFragment = new ImageFragment();

            transaction.replace(R.id.container, imageFragment);
            transaction.commit();
        }



    }

    @Override
    protected void onStart() {
        super.onStart();





     }


    @Override
    public void clickSpinnerSelected(int position) {
        Log.i(TAG,"position" + position);
        imageFragment.changeImage(position);
    }
}
