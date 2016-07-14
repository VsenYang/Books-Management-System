package com.example.visen.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by www40 on 2016/6/10.
 */
public class QueryBookActivity extends AppCompatActivity {

    private List<Book> bookList = new ArrayList<>();
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_query_book);

         /*
            将活动放入活动收集器中
         */
        ActivityCollecter.addActivity(this);

        //构造自定义的adapter
        BookAdapter bookAdapter = new BookAdapter(QueryBookActivity.this,R.layout.booklist_activity,bookList);
        //初始化book
        initBook();
        //设置ListView
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(bookAdapter);
    }

    public void initBook(){

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Book",null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                String pages = cursor.getString(cursor.getColumnIndex("pages"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                Book book = new Book(name,author,pages,price);
                bookList.add(book);
            }while (cursor.moveToNext());
        }

        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.exit:
                ActivityCollecter.finishAll();
        }
        return true;
    }

}
