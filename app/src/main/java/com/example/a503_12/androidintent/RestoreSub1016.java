package com.example.a503_12.androidintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestoreSub1016 extends AppCompatActivity {

    int value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_sub1016);

        //앞에 있는 Activity 에서 title 이라는 이름으로
        //넘겨준 문자열을 받아서 출력
        TextView title =
                (TextView)findViewById(R.id.title);
        title.setText(getIntent().getStringExtra("title"));


        final TextView textView = (TextView)findViewById(R.id.textCount);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value +1;
                textView.setText(value+"");

            }
        });
    }

    //액티비티가 종료되기 직전에 호출되는 메소드
    //복원할 데이터를 저장
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //데이터 저장
        outState.putInt("value", value);
    }

    //액티비티가 시작할 때 호출되는 메소드
    //데이터를 복원함.


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int value = savedInstanceState.getInt("value");
        this.value=value;
        TextView textView = (TextView)findViewById(R.id.textCount);
        textView.setText(value + "");
    }
}
