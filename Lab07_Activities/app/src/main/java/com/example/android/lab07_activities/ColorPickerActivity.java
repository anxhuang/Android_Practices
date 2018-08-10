package com.example.android.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends AppCompatActivity {

    private static final String TAG = "Lab07-ColorPicker";

    //key值的最佳實踐方式，以App的package做、為前綴詞 (為了讓Main也能用 要public)
    public static final String BUNDLE_KEY_COLOR_INT = "com.example.android.lab07_activities.mColorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.example.android.lab07_activities.mColorName";

    private int mColorInt;
    private CharSequence mColorName; //記得用CharSequence方便處理

    private void saveData(){
        //取得偏好設定並僅允許App本身私有存取
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        //取得偏好設定編輯物件
        SharedPreferences.Editor editor = prefs.edit();
        //寄放資料
        editor.putInt(BUNDLE_KEY_COLOR_INT, mColorInt);
        editor.putString(BUNDLE_KEY_COLOR_NAME, mColorName.toString());
        //送出資料
        editor.commit();
        Log.d(TAG, "saveData() mColotInt="+mColorInt+" mColorName="+mColorName);
    }

    private void restoreData(){
        //取得偏好設定並僅允許App本身私有存取
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        //取回資料
        mColorInt = prefs.getInt(BUNDLE_KEY_COLOR_INT, 0); //設定0為key不存在時的預設值
        mColorName = prefs.getString(BUNDLE_KEY_COLOR_NAME, "holo_red_light"); //設定holo_red_light為key不存在時的預設值
        //要把按鈕狀態還原
        RadioButton radio=null;
        switch(mColorName.toString()){
            case "holo_red_light":
                radio = findViewById(R.id.radio_holo_red_light);
                break;
            case "holo_orange_dark":
                radio = findViewById(R.id.radio_holo_orange_dark);
                break;
            case "holo_green_light":
                radio = findViewById(R.id.radio_holo_green_light);
                break;
            case "holo_blue_dark":
                radio = findViewById(R.id.radio_holo_blue_dark);
                break;
        }
        if (radio != null){
            radio.setChecked(true);
        }

        Log.d(TAG, "restoreData() mColotInt="+mColorInt+" mColorName="+mColorName);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
        Log.d(TAG, "onPause() mColotInt="+mColorInt+" mColorName="+mColorName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
        Log.d(TAG, "onResume() mColotInt="+mColorInt+" mColorName="+mColorName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        //預設紅色
        RadioButton radio = findViewById(R.id.radio_holo_red_light);
        mColorInt = radio.getCurrentTextColor();
        mColorName = radio.getText();
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
