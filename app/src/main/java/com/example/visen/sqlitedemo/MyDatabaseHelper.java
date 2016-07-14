package com.example.visen.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by www40 on 2016/5/30.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper implements Serializable{

    private static final String NAME = "BookStore.db";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;
    private static MyDatabaseHelper dbHelper;

    /*
        创建一个BOOK表,设为常量.
            integer: 整形
            text:文本类型
            real:浮点数
            blob:二进制类型
            primary key :将id设为主键
            autoincrement:数据自动增长
     */
    private static final String CREATE_BOOK = "create table Book("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price text,"
            +"pages text,"
            +"name text)";

    /*
        创建一个category表，设为常量
     */
    private static final String CATEGORY="create table Category("
            +"id integer primary key autoincrement,"
            +"category_name text,"
            +"category_code integer)";


    /*
        重写构造方法
            第一个参数：上下文
            第二个参数：数据库的名称
            第三个参数：允许查询时返回一个自定义的cursor
            第四个参数：当前数据库版本
     */
    public MyDatabaseHelper(Context context){
        super(context,NAME,null,DATABASE_VERSION);
        mContext = context;
    }

    /*
        创建数据库方法
        execSQL方法：执行建表语句
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CATEGORY);
        Toast.makeText(mContext, "已创建表", Toast.LENGTH_SHORT).show();
    }

    /*
        更新数据库方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");   //如果表已经存在，则删除
        db.execSQL("drop table if exists Category");
        onCreate(db);//重新创建表
    }

    public static synchronized MyDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (dbHelper == null) {
            dbHelper = new MyDatabaseHelper(context.getApplicationContext());
        }
        return dbHelper;
    }
}
