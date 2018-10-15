package com.example.a503_12.androidintent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DataDetail1015 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_detail1015);

        Button btnback = (Button)findViewById(R.id.btnback);
        TextView tvtitle = (TextView)findViewById(R.id.tvtitle);
        ListView listDtail = (ListView)findViewById(R.id.listDetail);

        //앞에서 넘겨준 데이터 가져오기
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        tvtitle.setText(category);

        //category에 해당하는 location 찾아오기
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select location from tb_data where category=?", new String[]{category});

        //ArrayList에 읽은 데이터 추가
        ArrayList<String> list = new ArrayList<>();
        while(cursor.moveToNext()){
            list.add(cursor.getString(0));
        }

        //ListView에 데이터 출력
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listDtail.setAdapter(adapter);

        //뒤로가기 버튼
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //어떤 아이템 선택했는지 토스트하고 뒤로가기
        listDtail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)view;
                String location = textView.getText().toString();
                Toast.makeText(DataDetail1015.this,location,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}