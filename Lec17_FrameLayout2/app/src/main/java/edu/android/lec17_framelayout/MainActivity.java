package edu.android.lec17_framelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_INDEX = "stat_currentIndex";
    //위의 상수를 가지고 데이터를 저장/호츨 할 것이다

    private ImageView[] imageViews = new ImageView[3];
    //배열인 경우 몇개짜리 이미지를 사용할건지 선언을 하고 가야 한다

    int currentIndex;
    //이미지View 를 바꿔줄때 몇번째 이미지를 보고있는지 저장-> 다음 이미지를 봅게 해 주고,
    // 가고/세로 바꿨을때 그 이미지 그대로 볼 수 있도록 할 때 사용

//    ImageView iv1, iv2, iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViews[0] = findViewById(R.id.andkiket);
        imageViews[1] = findViewById(R.id.andLoli);
        imageViews[2] = findViewById(R.id.Logetaa);

        // 순서 : 0->1->2->0->1->. . .

//        iv1 = findViewById(R.id.andkiket);
//        iv2 = findViewById(R.id.andLoli);
//        iv3 = findViewById(R.id.Logetaa);

//        iv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iv1.setVisibility(View.INVISIBLE);
//                iv2.setVisibility(View.VISIBLE);
//            }
//        });
//
//        iv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iv2.setVisibility(View.INVISIBLE);
//                iv3.setVisibility(View.VISIBLE);
//            }
//        });
//
//        iv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iv3.setVisibility(View.INVISIBLE);
//                iv1.setVisibility(View.VISIBLE);
//            }
//        });


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }

    public void changeImage(View view) {
        //모든 onClickListener 매개변수는  View가 와야 한다
        //TODO
//        for(currentIndex = 0;currentIndex<=imageViews.length;currentIndex++){
//
//        }

//       currentIndex++;
//       if(currentIndex == imageViews.length){
//           currentIndex = 0;
//       }

        if(currentIndex < imageViews.length -1){
          currentIndex++;
        }else{
            currentIndex = 0;
        }

        //메소드 하나 호출-changeImage안쪽에서
        showImageView();

    }

    private void showImageView(){
        //반복문 사용
        for(int i = 0; i < imageViews.length ; i++){
            if( i == currentIndex ){
                imageViews[i].setVisibility(View.VISIBLE);
            }else{
                //i 가 currentIndex 와 다를경우 사진을 보여주지 않는다
                imageViews[i].setVisibility(View.INVISIBLE);
            }
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_INDEX, currentIndex);
//        STATE_INDEX 는 currentIndex를 꺼낼떄 사용
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        savedInstanceState 에서 값을 꺼내서 저장 해 주겠다
        currentIndex = savedInstanceState.getInt(STATE_INDEX);

        //값을 꺼냈으면 화면을 갱신해야 한다
        showImageView();

    }
}

