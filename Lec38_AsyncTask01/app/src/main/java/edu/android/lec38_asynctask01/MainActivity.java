package edu.android.lec38_asynctask01;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.PriorityQueue;


//  AsyncTask :: Thread 와 Handler 의 기능을 갖고 있는 도우미 클래스 (HelperClass) - 추상 메소드
//    1) 내부적으로 작업 스레드(working thread)를 생성해서 실행함(UI 건드릴 수 없는 별도 스레드)
//       -> 호출되는 메소드 :: doInBackground() :: 작업 스레드에서 해야 할 일을 구현, override 필수

//    2) 필요할때 마다  UI 스레드에서 호출되는 callBack 메소드들을 가지고 있다
//       -> 호출되는 메소드 :: onProgressUpdate() :: UI 스레드에서 작업 스레드의 중간 결과를 업데이트할 떄
//                            onPostExecute()  :: UI 스레드에서 작업 스레드의 최종 결과를 업데이트할 떄

//  AsyncTask를 상속받는 클래스를 정의, 콜백 메소드들을 Override
//  AsyncTask<Params, Progress, Result>
//       1) Params : 메인 스레드에서 execute(...) 메소드를 호출할때 매개변수로 전달되는 타입
//                    >>>dioInBackground(...) 메소드의 파라미터로 전달됨
//       2) Progress : doInBackground(...) 메소드 내부에서 publishProgress(...) 메소드를 호출할 때 매개변수로 전달하는 변수 타입
//                    >>>> onProgressUpdate(...) 메소드의 파라미터로 전달됨
//                     작업 스레드의 중간 결과(진행상황)를 전달하기 위한 변수 타입
//       3) Result : 작업 스레드의 최종 결과를 리턴할 때 사용되는 변수 타입
//                    >>>> doInBackground() 메소드의 리턴 타입
//                    >>>> onPostExecute() 메소드의 파라미터 타입입
public class MainActivity extends AppCompatActivity {

    public static final String TAG = " edu.android";

    //AsyncTask 를 상속받는 내부 클래스를 정의
       // >> 작업 스레드를 실행시킬때
       //  >> 0부터 시작 vs 어떤 값을 주고서 시작하는지
    class ProgressTask extends AsyncTask<
                            Integer/*처음 시작*/,Integer/*Progress 업데이트 할때, 중간결과*/, Integer/*Result*/ >{

         //작업 스레드가 할 일을 구현
        // 메인 메소드에서 유일하게 사용 불가. 스레드에서만 사용 가능
        @Override
        protected Integer doInBackground(Integer... params) {
            Log.i(TAG, "doInBackground : param =" + params[0] +","+ params[1]);
            //원래는 param 의 length를 확인하고 출력하는게 맞다

          int start = params[0];
          int end = params[1];
          // 사실은 여기서 에러처리가 필요하다  >>

            progress = start;
            //end 값에 도달할 때 까지 0.1초마다 업데이트를 해 주겠다 >> Cancle버튼을 누르면 업데이트 되던게 멈춰야 한다
            while(!isCancelled()){
                //AsyncTask 가 취소되지 않은 상태에서 반복( = AstncTask가 취소되지 않은 동안에)
                //super 인 AsyncTask 가 가지고 있는 메소드
                publishProgress(progress);// 현재 진행상황 업데이트
                //  >> 0부터 시작했으니 0을 업데이트
                progress +=2;//프로그래스 값을 변경(진행)
                //2를 업데이트 -> 4 업데이트 -> 업데이트 후 6이 됨 -> 6 업데이트 후 8 -> .....
                //progress 가 종료되는 경우 -1) progress 가 end 값에 도달 2)end에 도달하지 않아도 cancle버튼이 눌린 경우
                if(progress >= end){
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            return progress;
            //메소드가 끝나면 작업 메소드도 종료
            //  doInBackgroun가  return 한 값은 onPostExecute로 리턴이 된다
        }

        //메인 스레드가 호출하는 CallBack 메소드
        @Override
        protected void onPreExecute/*작업스레드가 실행(Execute)되기 전(pre(ve))에*/() {
            //***할일이 거의 없다***
            Log.i(TAG, "onPreExecute");
            super.onPreExecute();
        }

        //doInBackgroun() 매소드 내부에서 publishProgress()를 호출하면
        //메인 스레드에서 호출하는 콜백 메소드드
        //  >>> UI Update 가능  <<<
        @Override
       protected void onProgressUpdate(Integer... values/**/) {
            //publishProgress를 호출 할 때 마다 publishProgress의 int값이 onProgressUpdate로 전달이 된다

            Log.i(TAG,"onProgressUpdate : values : " + values[0]);

            //중간 진행 상황을 업데이트
            progressBar.setProgress(values[0]);// 0.1.2....계속 진행해 주는 것
            textView.setText(values[0] + "%");// textView에 몇 퍼센트인지 적어주는 것


        }

        //doInBackgroun() 메소드가 종료되었을때 (작업 스레드가 정상 종료됬을 떄)
        // OR 작업 스레드가 비정상 종료가 됬을떄
        // 메인 스레드에서 호출하는 CallBack 메소드
        //  >>> UI Update 가능  <<<
        @Override
        protected void onPostExecute(Integer result) {
            Log.i(TAG,"onPostExecute : result : " + result);
            //***할일
            progressBar.setProgress(result);// 0.1.2....계속 진행해 주는 것
            textView.setText(result + "% 작업 종료");

            btnstart.setEnabled(true);
            btnCancle.setEnabled(false);
            progress = 0;
        }

        //AsyncTask 의 cancle()메소드를 호출하면 ( = 작업 스레드가 취소되면 ),
        // 메인 스레드에서 호출되는 CallBack 메소드
        @Override
        protected void onCancelled(Integer result) { // 중간 결과를 받으면서 취소가 되는 - 매개변수 있는걸 override
            Log.i(TAG, "onCancelled : result = " + result);

            progressBar.setProgress(result);
            textView.setText(result + "%에서 취소가 됨");

        }
    }
    //타입 파라미터에서 변수 타입은 기본 타입은 사용 불가 - int가 아니라 integer사용 해야 한다

    private int progress; // 프로그래스바의 진행 상황을 저장하는 변수

    private ProgressTask task;
    private  ProgressBar progressBar;
    private TextView textView;
    private Button btnstart, btnCancle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        btnstart = findViewById(R.id.btnStart);
        btnCancle = findViewById(R.id.btnCancle);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgressTask();
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancleProgressTask();
            }
        });

    }

    private void cancleProgressTask() {

        if(task != null){
            task.cancel(true/*실행되고 있으면 interrupt를 보낼거냐, boolean 값 넣기*/);

        }

        btnstart.setEnabled(true);
        btnCancle.setEnabled(false);


    }

    private void startProgressTask() {
        Log.i(TAG, "startProgressTask");
        //AstncTask 를 상속받는 ProgressTask 인스턴스를 생성하고, 실행
        //나중에 필요하기 때문에 멤버변수 정의

        task = new ProgressTask();
        task.execute(progress/*시작값*/, 100/*종료값*/); // doInBackground 에게 입력한 parameter들이 가게 된다
        //매개변수의 갯수와 숫자가 바뀌어도 상관이 없는 이유
        // doInBackground(Integer... params) 가 Integer 타입이기만 하면 어떤 매개변수가 와도 상관이 없기
        //때문. 갯수도 상관이 없다
        //
        //execute() 메소드는 가변길이 매개변수(variable Length parameter)를 갖는다
        // -> execute()메소드의 매개변수의 갯수는 변할 수 없다
        // 여기서 Integer뿐만 아니라 double 이라던가 모든 타입이 가능하게 만들고 싶을 경우 Object로 쓰면 된다


        btnstart.setEnabled(false);
        btnCancle.setEnabled(true);

    }
}




















