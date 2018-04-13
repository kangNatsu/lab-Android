package edu.android.lec30_masterdetail03;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDetail extends Fragment{
    /**
     * 인터페이스 만들*
     * Fragment Attach하고 Activity주소 저장*
     * 변수 선언*
     * 디폴트 생성자*
     * onattach - callBack을 넣어줌
     */

    interface ProductListDetailCallback{
        void onproductSelected(int position);
    }

    private ProductListDetailCallback callback;
    private RecyclerView recyclerView;
    private List<Product> dataset;


    public ListDetail() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {

        if(context instanceof ProductListDetailCallback){
            callback = (ProductListDetailCallback) context;
        }else{
            new RuntimeException("반드시ProductListDetailCallback을 구현해라 ");
        }

        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_list_detail, container, false );

        dataset = ProductLab.getInstance().getProductList();

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }

     class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

         @Override
         public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             LayoutInflater inflater = LayoutInflater.from(getContext());
             View itemView = inflater.inflate(R.layout.fragment_list_detail,parent,false);
             ViewHolder holder = new ViewHolder(itemView);
             return holder;
         }

         @Override
         public void onBindViewHolder(ViewHolder holder, final int position) {
             Product product = dataset.get(position);
             holder.DetailPhoto.setImageResource(product.getPhotoId());
             holder.DetailText.setText(product.getProductName());
             holder.itemView.setOnClickListener((v) -> {
                 callback.onproductSelected(position);
             });
         }

         @Override
         public int getItemCount() {
             return dataset.size();
         }

          class ViewHolder extends RecyclerView.ViewHolder {

             ImageView DetailPhoto;
             EditText DetailText;

              public ViewHolder(View itemView) {

                  super(itemView);
                  DetailPhoto = itemView.findViewById(R.id.photoDetail);
                  DetailText = itemView.findViewById(R.id.nameDetail);
              }
          }
     }



}
