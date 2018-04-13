package edu.android.lec40_network02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);


    }

    public void downloadImage(View view) {

        // 이미지 파일의 URL 주소
        String url = "https://images-na.ssl-images-amazon.com/images/G/01/img16/pet-products/vertical-store/cat-hero/desktop/32445_petsproducts_march-pets-site-flip-vertical-store_8_vd-hero_1920x693._CB299747913_.jpg";

        //AsyncTask 를 생성, 실행
        DownloadTask task = new DownloadTask();
        task.execute(url);


    }

    class DownloadTask extends AsyncTask
                           <String/*실행시킬때 어떤 타입을 줬느냐*/, Void/*중간중간 업데이트 할거면 준다*/, Bitmap>{


        @Override
        protected Bitmap doInBackground(String... params) {
            //TODO : connection 맺고 요천 보내고 받았으면 Image로 바꿔주는게 중요

            Bitmap bitmap = null;
            URL url = null;
            HttpURLConnection conn = null;
            InputStream in = null;
            BufferedInputStream bis = null;
            try {
                url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();


                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5 * 1000);
                conn.setReadTimeout(5 * 1000);

                conn.connect();

                int resCode = conn.getResponseCode();

                if (resCode == HTTP_OK) {
                    in = conn.getInputStream();
                    bis = new BufferedInputStream(in);
                    bitmap = BitmapFactory.decodeStream(bis);//이미지를 만들어 내기 위한
                    //decodeStream ::  static 메소드
                }


            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    bis.close();
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return bitmap/*이미지를 리턴하겠다*/;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
          imageView.setImageBitmap(bitmap);
        }

        }// end class DownloadTask

}//end class MainActivity















