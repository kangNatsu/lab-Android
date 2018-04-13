package edu.android.lec50_permission02;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_PERMISSION = 429;
    private EditText editTelNo, editSmsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTelNo = findViewById(R.id.editTelNo);
        editSmsText = findViewById(R.id.editSmsText);

    }

    public void onClickButton(View view) {

        //AndroidManifest.xml 파일에서 요청된 권한(permission)이 허용되어 있는지 체크
        //   1) 허용되어 있으면 sendSms()를 호출
        //   2) 허용되어 있지 않으면 사용자에게 권한 허용 요청을 보냄

        String[] permissions = {
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_PHONE_STATE
        };
        if (hasPermissions(permissions)) {
            //필요한 권한들이 허용된 경우
            sendSms();
        } else {
            //필요한 권한들이 허용되어 있지 않은 경우
            //사용자에게 권한 요청 다이얼로그 보여줌
            //  -> 사용자가 거부/허용을 선택
            //  -> 사용자의 선택 결과는 onRequestPermission() 메소드로 전달됨(Override 필요)

            if (ActivityCompat.
                    shouldShowRequestPermissionRationale(this,permissions[0])){
                Toast.makeText(this,"SMS 권한이 필요", Toast.LENGTH_LONG).show();

            }else if(ActivityCompat.
                    shouldShowRequestPermissionRationale(this,permissions[1])){
                Toast.
                   makeText(this,"안드로이드 8.0버그 떄문에 전화 사용 권한도 필요함다..(,,)",
                           Toast.LENGTH_LONG).show();

            }

            ActivityCompat.requestPermissions(this, permissions, REQ_CODE_PERMISSION);

        }

    }
        @Override
        public void onRequestPermissionsResult(int requestCode,
                                                  @NonNull String[] permissions,
                                                  @NonNull int[] grantResults){
            if (requestCode == REQ_CODE_PERMISSION) {
             // 사용자가 SEND_SMS와 READ_PHONE_STATE 권한을 모두 허용한 경우에만 sendSms()호출
                if (grantResults.length ==2 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED);
                sendSms();
            }else{
                Toast.makeText(this,"권한을 허용해 주세요",
                        Toast.LENGTH_SHORT).show();

        }

    }



    private boolean hasPermissions(String[] permissions) {
        boolean result = true;
        for (String p : permissions){
            if(ActivityCompat.checkSelfPermission(this, p/*int 리턴*/)  != PackageManager.PERMISSION_GRANTED){
                result = false;
                break;
            }
        }

        return result;

    }

    private void sendSms() {
        String telNo = editTelNo.getText().toString();
        String smsText = editSmsText.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(telNo,null,smsText,null,null);

        Toast.makeText(this,"SMS 보내기 SUCCESE", Toast.LENGTH_SHORT).show();

    }
}
