package com.example.wechat;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    //private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            inputFocus();//输入框获取焦点时
            final TextView name=(TextView)findViewById(R.id.reg_name);
            final TextView id=(TextView)findViewById(R.id.reg_id);
            final TextView passwd=(TextView)findViewById(R.id.reg_passwd);
            //dbHelper=new MyDatabaseHelper(this,"RegisterDate.db",null,2);
            findViewById(R.id.register_return).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
           /*
            findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db=dbHelper.getReadableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("name",name.getText().toString());
                    values.put("id",id.getText().toString());
                    values.put("passwd",passwd.getText().toString());
                    db.insert("Login",null,values);
                }
            });*/
        }


    }
    public void inputFocus(){
        findViewById(R.id.reg_name).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    ImageView imageView=(ImageView)findViewById(R.id.reg_diver1);
                    imageView.setBackgroundResource(R.color.input_dvier_focus);
                } else {
                    // 此处为失去焦点时的处理内容
                    ImageView imageView=(ImageView)findViewById(R.id.reg_diver1);
                    imageView.setBackgroundResource(R.color.input_dvier);
                }
            }
        });
        findViewById(R.id.reg_id).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    ImageView imageView=(ImageView)findViewById(R.id.reg_diver2);
                    imageView.setBackgroundResource(R.color.input_dvier_focus);
                } else {
                    // 此处为失去焦点时的处理内容
                    ImageView imageView=(ImageView)findViewById(R.id.reg_diver2);
                    imageView.setBackgroundResource(R.color.input_dvier);
                }
            }
        });
        findViewById(R.id.reg_passwd).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    ImageView imageView=(ImageView)findViewById(R.id.reg_diver3);
                    imageView.setBackgroundResource(R.color.input_dvier_focus);
                } else {
                    // 此处为失去焦点时的处理内容
                    ImageView imageView=(ImageView)findViewById(R.id.reg_diver3);
                    imageView.setBackgroundResource(R.color.input_dvier);
                }
            }
        });
    }
}
