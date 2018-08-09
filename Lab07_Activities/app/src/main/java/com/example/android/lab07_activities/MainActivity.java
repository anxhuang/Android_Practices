package com.example.android.lab07_activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //傳令兵的請求碼 只要是不重複的數字都行
    private static final int colorRequest = 999;
    private static final int imageRequest = 777;

    private TextView m_tv_color;
    private ImageView m_tv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_tv_color = findViewById(R.id.tv_color);
        m_tv_image = findViewById(R.id.tv_image);
    }

    public void selectColor(View view) {
        //Intent (傳令的Activity, 接受傳令的Activity)
        Intent intent = new Intent(this, ColorPickerActivity.class);
        //startActivity(intent); 叫傳令出發 只適用於沒有要回來時
        startActivityForResult(intent, colorRequest); //要傳令帶返回結果時用這個
    }

    public void selectImage(View view) {
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, imageRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            Bundle bundle = data.getExtras(); //叫data把包包交出來

            switch(requestCode){
                case colorRequest:
                    int colorInt = bundle.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT);
                    m_tv_color.setBackgroundColor(colorInt);
                    CharSequence colorName = bundle.getCharSequence(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME);
                    m_tv_color.setText(colorName);
                    m_tv_color.setTextColor(getResources().getColor(android.R.color.white));
                    break;
                case imageRequest:
                    int imageInt = bundle.getInt(ImagePickerActivity.BUNDLE_KEY_IMAGE_INT);
                    m_tv_image.setImageResource(imageInt);
                    break;
            }

        }

    }

}
