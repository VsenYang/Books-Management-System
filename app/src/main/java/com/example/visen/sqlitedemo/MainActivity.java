package com.example.visen.sqlitedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by www40 on 2016/5/30.
 */
public class MainActivity extends AppCompatActivity{

    /*
        控件定义
     */
    Button addData;
    Button updateButton;
    Button deleteButton;
    Button queryButton;

    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        /*
            将活动放入活动收集器中
         */
        ActivityCollecter.addActivity(this);


        /**************实例化控件****************/
        addData = (Button)findViewById(R.id.addButton);
        updateButton = (Button)findViewById(R.id.updateButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);
        queryButton = (Button)findViewById(R.id.queryButton);

        /**************设置监听事件****************/
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "你按下了添加按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,AddBookActivity.class);
                startActivity(intent);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UpdateBookActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DeleteBookActivity.class);
                startActivity(intent);
            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QueryBookActivity.class);
                startActivity(intent);
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
