package edu.android.lec20_project;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private ImageView imageView;

    private int[] IMAGE_IDS = {R.id.cupcake,
            R.id.donut,
            R.id.eclair,
            R.id.froyo
             };

    public ImageFragment() {
        // Required empty public constructor
    }

    //팩xh리 메소드
    public static ImageFragment newInstance() {
        ImageFragment fragment = new ImageFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
//        View view = getView();
//        cupcake = view.findViewById(R.id.cupcake);
//        donut = view.findViewById(R.id.donut);
//        eclair= view.findViewById(R.id.eclair);
//        froyo = view.findViewById(R.id.froyo);


    }


    public void changeImage(int position) {
        View view = getView();
        for (int i = 0; i < IMAGE_IDS.length; i++) {
            if (i == position) {
                view.findViewById(IMAGE_IDS[i]).setVisibility(View.VISIBLE);
            } else {
                view.findViewById(IMAGE_IDS[i]).setVisibility(View.INVISIBLE);
            }
        }
    }
}
