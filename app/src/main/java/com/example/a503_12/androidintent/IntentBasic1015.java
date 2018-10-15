package com.example.a503_12.androidintent;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class IntentBasic1015 extends AppCompatActivity {
    TextView viewfirst;
    Button btnMoveSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_basic1015);

        viewfirst = (TextView)findViewById(R.id.viewfisrt);
        btnMoveSub = (Button)findViewById(R.id.btnMoveSub);

        btnMoveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //출력할 Activity의 Intent 만들기
                //하위 Activity 인스턴스를 직접 생성하지 않음.****** 제어의 역전 ioc *****
                //Activity를 이용해서는 데이터를 전달할 수 없음.
                Intent intent = new Intent(IntentBasic1015.this, IntentBasicSub1015.class);

                //Map 형식은 HashMap만 가능
                HashMap<String,Object> map = new HashMap<>();
                map.put("이름", "김이름");
                map.put("나이", 32);

                intent.putExtra("data", map);

                //Activity 출력
                //startActivity(intent);

                //하위 액티비티에서 데이터를 리턴받을 수 있는 메소드를 호출하여 하위 액티비티 출력
                startActivityForResult(intent, 100);
            }
        });
        Log.e("IntentBasic1015","onCreate 호출");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode ==100){
            String subdata = data.getStringExtra("subdata");
            viewfirst.setText(subdata);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("IntentBasic1015", "onResume 호출");
    }

}
