package com.example.autotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_search;
    AutoCompleteTextView autoCompleteTextView;
    String[]str=new String[]{"android","android studio","android demo","android project","baidu.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_search=findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在浏览器浏览这个网址
                Uri uri = Uri.parse("http://"+autoCompleteTextView.getText().toString().trim());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        autoCompleteTextView=findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.item,str);
        autoCompleteTextView.setAdapter(adapter);

    }
}