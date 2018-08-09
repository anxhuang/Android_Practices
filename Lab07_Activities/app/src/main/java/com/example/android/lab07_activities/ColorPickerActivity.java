package com.example.android.lab07_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends AppCompatActivity {

    //key值的最佳實踐方式，以App的package做、為前綴詞 (為了讓Main也能用 要public)
    public static final String BUNDLE_KEY_COLOR_INT = "com.example.android.lab07_activities.colorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.example.android.lab07_activities.colorName";

    private int mColorInt;
    private CharSequence mColorName; //記得用CharSequence方便處理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        mColorInt = getResources().getColor(android.R.color.holo_red_light);
        mColorName = "holo_red_light";
    }

    public void clickColor(View view) {
        RadioButton radio = (RadioButton)view;//因為接收一率都是View 所以一定要做轉型RadioButton
        mColorInt   = radio.getCurrentTextColor();
        mColorName  = radio.getText();
    }

    public void ok(View view) {
        //建立新傳令(意圖)
        Intent intent = new Intent();
        //設定包裹
        intent.putExtra(BUNDLE_KEY_COLOR_INT, mColorInt); //key: String, value: int
        intent.putExtra(BUNDLE_KEY_COLOR_NAME, mColorName); //key: String, value: String
        setResult(RESULT_OK, intent); //設定結果OK, 交代傳令
        finish(); //結束Activity並回到上一個Activity
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
