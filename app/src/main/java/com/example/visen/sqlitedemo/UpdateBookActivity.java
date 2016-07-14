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
 * Created by www40 on 2016/6/2.
 */
public class UpdateBookActivity extends AppCompatActivity {
    /*
        控件定义
     */
    EditText searchBookName;
    EditText changeBookName;
    EditText changeAuthor;
    EditText changePages;
    EditText changePrice;
    Button updateVerifyButton;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        /*
            加载布局
         */
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_update_book);

         /*
            将活动放入活动收集器中
         */
        ActivityCollecter.addActivity(this);

        /*
            控件实例化
         */
        searchBookName = (EditText)findViewById(R.id.searchBookName);
        changeBookName = (EditText)findViewById(R.id.changeBookName);
        changeAuthor = (EditText)findViewById(R.id.changeAuthorName);
        changePages = (EditText)findViewById(R.id.changePages);
        changePrice = (EditText)findViewById(R.id.changePrice);
        updateVerifyButton = (Button)findViewById(R.id.updateVerifyButton);

        /*
            数据库
         */
        final MyDatabaseHelper myDatabaseHelper = MyDatabaseHelper.getInstance(this);
        final ContentValues contentValues = new ContentValues();
        final SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        /*
            设置监听事件
         */
        updateVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    开始封装数据
                 */
                if(!changeBookName.getText().toString().equals("")){
                    contentValues.put("name",changeBookName.getText().toString());
                    sqLiteDatabase.update("Book",contentValues,"name=?",new String[]{searchBookName.getText().toString()});
                }
                if(!changeAuthor.getText().toString().equals("")){
                    contentValues.put("author",changeAuthor.getText().toString());
                    sqLiteDatabase.update("Book",contentValues,"name=?",new String[]{searchBookName.getText().toString()});
                }
                if(!changePages.getText().toString().equals("")){
                    contentValues.put("pages",changePages.getText().toString());
                    sqLiteDatabase.update("Book",contentValues,"name=?",new String[]{searchBookName.getText().toString()});
                }
                if(!changePrice.getText().toString().equals("")){
                    contentValues.put("price",changePrice.getText().toString());
                    sqLiteDatabase.update("Book",contentValues,"name=?",new String[]{searchBookName.getText().toString()});
                }
                Toast.makeText(UpdateBookActivity.this, "更新成功", Toast.LENGTH_SHORT).show();

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
