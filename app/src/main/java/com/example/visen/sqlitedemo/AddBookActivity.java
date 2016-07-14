package com.example.visen.sqlitedemo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by www40 on 2016/5/31.
 */
public class AddBookActivity extends AppCompatActivity {

    /*
        控件定义
     */
    EditText bookName = null;
    EditText author = null;
    EditText pages = null;
    EditText price = null;
    Button addBookVerify = null;
    Button resetBook = null;

    @Override
    protected void onCreate(Bundle saveInstanceState){

        /*
            加载布局
         */
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_data);

        ActivityCollecter.addActivity(this);

        /*
            控件实例化
         */
        addBookVerify = (Button)findViewById(R.id.addBookVerifyButton);
        resetBook = (Button)findViewById(R.id.resetBookButton);
        bookName = (EditText)findViewById(R.id.addBookName);
        author = (EditText)findViewById(R.id.addAuthorName);
        pages = (EditText)findViewById(R.id.addPages);
        price = (EditText)findViewById(R.id.addPrice);

        /*
            数据库
         */

        final MyDatabaseHelper myDatabaseHelper = MyDatabaseHelper.getInstance(this);

        /*
            向表中添加数据
                用MyDatabaseHelper的getWritableDatabase();方法得到SQLiteDatabase对象
                得到ContentValues对象
                用contentValues的put方法封装数据
         */
        final SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();//得到一个可读写的数据库
        final ContentValues contentValues = new ContentValues();

        /*
            按键监听事件
         */
        addBookVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**********开始封装数据**********/
                contentValues.put("name",bookName.getText().toString());
                contentValues.put("author",author.getText().toString());
                contentValues.put("pages",pages.getText().toString());
                contentValues.put("price",price.getText().toString());
                sqLiteDatabase.insert("Book",null,contentValues);
                contentValues.clear();
                Toast.makeText(AddBookActivity.this, "已添加书籍", Toast.LENGTH_SHORT).show();
                bookName.setText("");
                author.setText("");
                pages.setText("");
                price.setText("");

            }
        });

        resetBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddBookActivity.this, "Reset Book", Toast.LENGTH_SHORT).show();
                bookName.setText("");
                author.setText("");
                pages.setText("");
                price.setText("");
            }
        });

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
