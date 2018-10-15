package com.example.a503_12.androidintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //생성자
    public DBHelper(Context context){
        //첫번째 매개변수는 Context,
        //두번쨰 매개변수는 저장될 파일명,
        //세번째 매개변수는 Cursor를 직접 만든 경우 지정,
        //네번째 매개변수는 버전
        super(context, "database.sqlite3", null, 1);
    }

    //데이터베이스를 처음 사용할때 한 번 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tb_data(_id integer primary key autoincrement, category text, location text)";
        db.execSQL(sql);

        db.execSQL("insert into tb_data(category, location) values('서울특별시', '종로구')");
        db.execSQL("insert into tb_data(category, location) values('서울특별시', '노원구')");
        db.execSQL("insert into tb_data(category, location) values('서울특별시', '마포구')");
        db.execSQL("insert into tb_data(category, location) values('경기도', '용인시')");
        db.execSQL("insert into tb_data(category, location) values('경기도', '수원시')");
        db.execSQL("insert into tb_data(category, location) values('경기도', '남양주시')");
        db.execSQL("insert into tb_data(category, location) values('강원도', '속초시')");
        db.execSQL("insert into tb_data(category, location) values('강원도', '태백시')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //테이블을 삭제하고 다시 생성
        db.execSQL("drop table tb_data");
        onCreate(db);
    }
}
