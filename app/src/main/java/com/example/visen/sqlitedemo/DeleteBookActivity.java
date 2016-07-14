package com.example.visen.sqlitedemo;

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
public class DeleteBookActivity extends AppCompatActivity {

    Button deleteButton;
    EditText deleteBookName;
    @Override
    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_delete_book);

         /*
            将活动放入活动收集器中
         */
        ActivityCollecter.addActivity(this);

        deleteButton = (Button)findViewById(R.id.deleteButtonVerify);
        deleteBookName = (EditText)findViewById(R.id.deleteBookName);

        final MyDatabaseHelper myDatabaseHelper = MyDatabaseHelper.getInstance(this);
        final SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase.delete("Book","name=?",new String[]{deleteBookName.getText().toString()});
                Toast.makeText(DeleteBookActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                deleteBookName.setText("");
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
