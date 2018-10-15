package com.example.a503_12.androidintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class IntentBasicSub1015 extends AppCompatActivity {

    //인스턴스 선언
    TextView viewsecond;
    Button btnMoveMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_basic_sub1015);

        viewsecond = (TextView)findViewById(R.id.viewsecond);
        btnMoveMain = (Button)findViewById(R.id.btnMoveMain);

        //메인에서 전달해준 데이터를 읽기
        Intent intent = getIntent();
        //String data = intent.getStringExtra("data");
        //viewsecond.setText(data);
        HashMap<String,Object> map = (HashMap<String, Object>)intent.getSerializableExtra("data");
        viewsecond.setText(map.toString());

        btnMoveMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //상위 액티비티에게 전달할 데이터 생성
                Intent intent = new Intent();
                intent.putExtra("subdata", "하위 액티비티의 데이터를 상위 액티비티로 넘겨줍니다.");

                //응답 코드 생성한 후 데이터 전달
                setResult(100, intent);

                finish();
            }
        });
        Log.e("SubActivity", "Sub의 onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SubActivity", "Sub의 onResume");
    }
}
