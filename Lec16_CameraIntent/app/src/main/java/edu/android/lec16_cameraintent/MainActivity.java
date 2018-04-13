package edu.android.lec16_cameraintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE = 629;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //카메라 버튼
    public void startCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //MediaStore.ACTION_VIDEO_CAPTURE : 동영상 촬영 가은
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, REQ_CODE);
            //REQ_CODE는 우리가 맘대로 정하는게 가능하다
            //코드를 정하면 ART가 그걸 사용하고 리턴할때 그 숫자 그대로 돌려준다
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode/*Result코드를 줌*/,
                                    Intent data/*result코드가 잘 되었다면 이미지가 들어와 있을 것임*/) {

        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {

            Bundle bundle = data.getExtras();
            //코드가 없어 일단 Bundle을 사용해 getExtra() 를 꺼내주고 사용한다

            if(bundle !=null){
                bundle.get("data");//Object를 리턴 하는데 이 Object안에 비트맵이 있다

                //비트맵에 저장
                Bitmap bitmap = (Bitmap)bundle.get("data");//형변환 해야 한다 - Bitmap으로
                //카메라가 보내주는 이미지이므로 bitmap이미지를 보내 줄 것이기 때문에 형변환 필요

                ImageView iv = findViewById(R.id.imageView);
                iv.setImageBitmap(bitmap);
            }
        } else {
            Toast.makeText(MainActivity.this, "취소됨", Toast.LENGTH_SHORT).show();
        }
    }
}
