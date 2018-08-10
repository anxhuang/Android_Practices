package com.example.android.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Lab07-Main";
    private static final String BUNDLE_KEY_COLOR_INT = "com.example.android.lab07_activities.colorInt";
    private static final String BUNDLE_KEY_COLOR_NAME = "com.example.android.lab07_activities.colorName";
    private static final String BUNDLE_KEY_IMAGE_INT = "com.example.android.lab07_activities.imageInt";

    //傳令兵的請求碼 只要是不重複的數字都行
    private static final int colorRequest = 999;
    private static final int imageRequest = 777;

    private TextView tv_color;
    private int colorInt;
    private CharSequence colorName;

    private ImageView tv_image;
    private int imageInt;

    private void saveData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(BUNDLE_KEY_COLOR_INT, colorInt);
        editor.putString(BUNDLE_KEY_COLOR_NAME, colorName.toString());
        editor.putInt(BUNDLE_KEY_IMAGE_INT, imageInt);
        editor.commit();

    }

    private void restoreData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        colorInt = prefs.getInt(BUNDLE_KEY_COLOR_INT, 0);
        colorName = prefs.getString(BUNDLE_KEY_COLOR_NAME, "顏色未設定");
        imageInt = prefs.getInt(BUNDLE_KEY_IMAGE_INT, 0);
        if(colorInt != 0){
            tv_color.setBackgroundColor(colorInt);
            tv_color.setText(colorName);
            tv_color.setTextColor(getResources().getColor(android.R.color.white));
        }
        if(imageInt != 0){
            tv_image.setImageResource(imageInt);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
        Log.d(TAG, "onPause() colotInt="+colorInt+" colorName="+colorName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_color = findViewById(R.id.tv_color);
        tv_image = findViewById(R.id.tv_image);
        restoreData();
        Log.d(TAG, "onCreate() colotInt="+colorInt+" colorName="+colorName);
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
                    colorInt = bundle.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT);
                    tv_color.setBackgroundColor(colorInt);
                    colorName = bundle.getCharSequence(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME);
                    tv_color.setText(colorName);
                    tv_color.setTextColor(getResources().getColor(android.R.color.white));
                    break;
                case imageRequest:
                    imageInt = bundle.getInt(ImagePickerActivity.BUNDLE_KEY_IMAGE_INT);
                    tv_image.setImageResource(imageInt);
                    break;
            }

        }

    }

}
