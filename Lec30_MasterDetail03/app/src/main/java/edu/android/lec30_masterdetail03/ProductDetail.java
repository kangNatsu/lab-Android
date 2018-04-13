package edu.android.lec30_masterdetail03;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetail extends Fragment {

    /**
     * 데이터 저장하고 읽어올 키값*
     * 변수 선언*
     * Factory메소드 작성*
     * 번들객체*
     * 번들값에 키값과 인덱스 넣어줌*
     * setIndex*
     * onStar - find, ArrayLIst, View설정*
     *
     */

    private static final String ARG_PRODUCT_INFO = "selected_product_item";

    private int index;
    private EditText textName, textPrice, textDescrip;
    private ImageView photoImage;
    private Button button;



    public ProductDetail() {
        // Required empty public constructor
    }
    public static ProductDetail newInstance (int index){

        ProductDetail fragment = new ProductDetail();

        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PRODUCT_INFO, index);
        fragment.setArguments(bundle);


        return fragment;

    }

    public void setIndex(int index){
        this.index = this.index;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     Bundle args = getArguments();
     if(args != null){
        index = args.getInt(ARG_PRODUCT_INFO);
     }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        textName = view.findViewById(R.id.nameDetail);
        textPrice = view.findViewById(R.id.priceDetail);
        textDescrip = view.findViewById(R.id.descripDetail);
        button = view.findViewById(R.id.btnExit);
        photoImage = view.findViewById(R.id.photoDetail);

        List<Product> productList = ProductLab.getInstance().getProductList();
        Product product = productList.get(index);

        photoImage.setImageResource(product.getPhotoId());
        textName.setText(product.getProductName());
        textPrice.setText(product.getPrice());
        textDescrip.setText(product.getDescription());







    }
}
