package com.example.sqlite2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sign extends AppCompatActivity {
    private EditText user_name,user_pass,user_again,user_tel;
    private Button sure;
    SQLiteOpenHelper sql;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();


    }


    private void initView() {
        user_again = findViewById(R.id.user_again);
        user_name = findViewById(R.id.user_name);
        user_pass = findViewById(R.id.user_pass);
        user_tel = findViewById(R.id.user_tel);
        sure = findViewById(R.id.sure);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSase();
            }
        });

    }


    private void getSase() {
        sql = new SQLite(Sign.this,"user_info.db",null,1);
        String Name = user_name.getText().toString();
        String Pass = user_pass.getText().toString();
        String Again = user_again.getText().toString();
        String Tel = user_tel.getText().toString();
        if (Name.equals("")||Pass.equals("")||Again.equals("")){
            nullTip();
        }else {
            if (!Pass.equals(Again)){
                disLike();
            }else {
                pIns(Name,Pass,Tel);
            }
        }
    }

    private void pIns(String name, String pass, String tel) {
        db = sql.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name",name);
        values.put("user_pass",pass);
        values.put("user_tel",tel);
        db.insert("'user_info",null,values);
        Success();

    }


    private void Success() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("输入成功")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    private void disLike() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("两次密码输入相同哦")
                .setPositiveButton("确定",null)
                .show();
    }

    private void nullTip() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("账号或密码马未填")
                .setPositiveButton("确定",null)
                .show();
    }
}