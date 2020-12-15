package com.example.sqlite2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login,sign;
    private EditText ename,epass;
    private ImageView back;
    SQLiteDatabase db2;
    SQLiteOpenHelper sql;
    String psdQuess = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        login.setOnClickListener(this);
        sign.setOnClickListener(this);
    }

    private void initView() {
        login = findViewById(R.id.login);
        sign = findViewById(R.id.sign);
        ename = findViewById(R.id.ename);
        epass = findViewById(R.id.epass);
        back = findViewById(R.id.back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                break;
            case R.id.login:
                getSata();
                break;
            case R.id.sign:
                sign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,Sign.class));
                    }
                });
                break;
        }
    }

    private void getSata() {
        sql = new SQLite(MainActivity.this,"user_info.db",null,1);
        String Ename = ename.getText().toString();
        String Epass = epass.getText().toString();
        if (Ename.equals("")||Epass.equals("")){
            nullTip();
        }else{
            quessInfo(Ename,Epass);
        }
    }

    private void quessInfo(String ename, String epass) {
        db2 = sql.getReadableDatabase();
        Cursor cursor =db2.query("user_info",new String[]{"user_pass"},
                "user_name= ?",new String[]{ename},null,null,null);
        if (cursor.getCount()<0){

        }else {
            while (cursor.moveToNext()){
            psdQuess = cursor.getString(cursor.getColumnIndex("user_pass"));}

        }
        if (epass.equals(psdQuess)){
            startActivity(new Intent(MainActivity.this,Scuess.class));
        }else {
            noPus();
        }

    }

    private void noPus() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("账号或密码马错误")
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