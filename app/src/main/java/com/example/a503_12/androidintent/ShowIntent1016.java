package com.example.a503_12.androidintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowIntent1016 extends AppCompatActivity {

    //여기에 변수를 선언하면 클래스 내의 모든 곳에서 변수를
    //사용할 수 있습니다.
    Button contact;
    Button camera;
    Button voice;
    Button map;
    Button browser;
    Button phone;

    TextView resultView;
    ImageView resultImageview;

    //이미지 출력 크기를 위한 변수
    int reqWidth;
    int reqHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_intent1016);
        //여기에 변수를 만들면 final을 붙이지 않는 이상
        //다른 클래스나 메소드 안에서 사용이 안됩니다.

        contact = (Button) findViewById(R.id.contact);
        camera = (Button) findViewById(R.id.camera);
        voice = (Button) findViewById(R.id.voice);
        map = (Button) findViewById(R.id.map);
        browser = (Button) findViewById(R.id.browser);
        phone = (Button) findViewById(R.id.phone);

        resultView = (TextView) findViewById(R.id.resultView);
        resultImageview = (ImageView) findViewById(R.id.resultImageview);


        reqWidth = getResources().getDimensionPixelOffset(R.dimen.request_image_width);
        reqHeight = getResources().getDimensionPixelOffset(R.dimen.request_image_height);

        //버튼들의 이벤트를 처리할 클래스의 객체
        View.OnClickListener eventHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //연락처 앱 연동
                    case R.id.contact:
                        //연락처 앱 실행
                        Intent contactIntent = new Intent(Intent.ACTION_PICK);
                        contactIntent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);

                        //실행
                        //sartActivity()는 결과를 못가져 옴
                        startActivityForResult(contactIntent, 10);
                        break;

                    //카메라 앱 연동
                    case R.id.camera:
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 20);
                        break;

                    //음성 인식 앱 연동
                    case R.id.voice:
                        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                        voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "음성인식 테스트");
                        startActivity(voiceIntent);
                        break;

                    //지도 앱 연동
                    case R.id.map:
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7661234, 125.3347654"));
                        startActivity(mapIntent);
                        break;

                    //브라우저 앱 연동
                    case R.id.browser:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.daum.net"));
                        startActivity(browserIntent);
                        break;

                    //전화 앱 연동
                    //전화 기능은 퍼미션을 확인하고 수행해야 함!!
                    case R.id.phone:
                        if(ContextCompat.checkSelfPermission(ShowIntent1016.this, Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED) {
                            Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-3042-4389"));
                            startActivity(phoneIntent);
                        }else{
                            ActivityCompat.requestPermissions(ShowIntent1016.this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                        }
                        break;
                }
            }
        };

        //버튼에 이벤트 핸들러를 연결
        contact.setOnClickListener(eventHandler);
        camera.setOnClickListener(eventHandler);
        voice.setOnClickListener(eventHandler);
        map.setOnClickListener(eventHandler);
        browser.setOnClickListener(eventHandler);
        phone.setOnClickListener(eventHandler);
    }

    //startActivityForResult로 인텐트를 실행한 후 인텐트의 액티비티가 종료 되었을 때 호출되는 메소드.
    //requestCode는 호출할 때 설정한 코드이고 (호출한 Activity 구분)
    //resultCode는 출력된 액티비티에서 전달해 준 코드 (응답 구분)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 10 && requestCode == RESULT_OK){
            String result = data.getDataString();
            resultView.setText(result);
        }
        //사진 찍은 후 확인 누르면 이전 화면으로 돌아와 ImageView에 썸네일로 보여줌
        //cameraIntent를 이용하면 putExtras 없이도 자동으로 저장되고, 저장되는 기본 이름은 이 api에서는 "data"임.
        else if(requestCode == 20 && resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            resultImageview.setImageBitmap(bitmap);
        }
    }
}
