package com.example.visen.sqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by www40 on 2016/6/11.
 */
public class LoginActivity extends AppCompatActivity {

    Button login;
    CheckBox saveData;
    EditText account;
    EditText password;
    private SharedPreferences spf;
    private SharedPreferences.Editor spfe;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

         /*
            将活动放入活动收集器中
         */
        ActivityCollecter.addActivity(this);

        login = (Button)findViewById(R.id.loginButton);
        saveData = (CheckBox)findViewById(R.id.checkBox1);
        account = (EditText)findViewById(R.id.editAccount);
        password = (EditText)findViewById(R.id.editPassword);

        spf = getSharedPreferences("data", Context.MODE_PRIVATE);
        spfe = spf.edit();

        //复选框是否被选中,若为选中状态，则保存过账户，要恢复数据
        if(spf.getBoolean("isSelect",false)){//选中标志,默认值为false
            String acc = spf.getString("account","");
            String pas = spf.getString("passWord","");
            account.setText(acc);
            password.setText(pas);
            saveData.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确认帐号密码
                if(account.getText().toString().equals("visen") && password.getText().toString().equals("dsy402645063!")){

                    //复选框是否被勾选，若被勾选，则需要保存账户后登录；否则直接登录且不保存账户
                    if(saveData.isChecked()){
                        save();
                    }else {
                        spfe.clear();
                        spfe.commit();
                    }

                    //页面跳转
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {//账户或密码错误
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void save(){
        //读取EditText中的内容
        String acc = account.getText().toString();
        String pas = password.getText().toString();
        //保存数据
        spfe.putString("account",acc);
        spfe.putString("passWord",pas);
        spfe.putBoolean("isSelect",true);
        //提交
        spfe.commit();
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

