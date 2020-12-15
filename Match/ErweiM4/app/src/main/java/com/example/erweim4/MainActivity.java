package com.example.erweim4;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                if (TextUtils.isEmpty(edit)){
                    Toast.makeText(MainActivity.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    GenerateQrCode generateQrCode = new GenerateQrCode();
                    Bitmap bitmap = generateQrCode.getBitmap(edit);
                    imageView.setImageBitmap(bitmap);

                }
            }
        });
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
}