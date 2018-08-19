package com.example.android.lab09_dialog;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener, MyDialogFragment.MyDialogInterface{ //要實作Listener

    private TextView m_tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_tv_message = findViewById(R.id.tv_message);
    }

    //====== 方法一: 由MainActivity來實作Listener =====
    public void clickAlertDialog(View view) {
        new AlertDialog.Builder(this) //使用這個建立警示對話框
                .setMessage("你好帥喔")
                .setPositiveButton("我知道", this) //這裡指定this為listener
                .show();

    }

    @Override //Listener的必要實作方法，這裡預設為Positive按下後作動
    public void onClick(DialogInterface dialogInterface, int i) {
        m_tv_message.setText("你好意思捏");
    }
    //===============================================

    //====== 方法二: 由自訂的inner class來實作Listener ======
    myYesNoListener myListener = new myYesNoListener();
    public void clickAlertDialogYesNo(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝", myListener) //將兩的按鈕交給同一個listener處理
                .setNegativeButton("狗腿", myListener)
                .show();
    }

    //自訂的inner class
    private class myYesNoListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int which_btn) {
            switch (which_btn){
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("真.不客氣");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("幹嘛這樣～");
                    break;
            }
        }
    }
    //====================================================

    //====== 方法三: 採用匿名inner class直接實作Listener ======
    public void clickAlertDialogYesNoCancel(View view) {
        new AlertDialog.Builder(this)
                .setMessage("你好帥喔")
                .setPositiveButton("謝謝讚美", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("不用客氣");
                    }
                })
                .setNegativeButton("太狗腿了吧", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("被發現了～");
                    }
                })
                .setNeutralButton("不知道該說什麼", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_tv_message.setText("我懂");
                    }
                }).show();
    }
    //====================================================

    //項目清單Dialog
    public void clickAlertDialogItems(View view) {
        //字串陣列
        final String[] items = getResources().getStringArray(R.array.myResponse); //匿名inner class只能讀取final區域變數

        new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar) //style可以自訂
                .setTitle("你好帥喔") //注意!!!這個是setTitle不是setMessage!!!!!!
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //i會回傳點選項目的index
                        m_tv_message.setText(items[i]);
                    }
                }).show();
    }

    //多選清單
    public void clickAlertDialogMultiChoice(View view) {

        final String[] items = getResources().getStringArray(R.array.myResponse);
        final boolean[] selected = new boolean[items.length]; //記錄每個項目的勾選狀態

        new AlertDialog.Builder(this)
                .setTitle("你好帥喔")
                .setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        //這個方法每次勾選都會執行, 會傳入第幾個被勾=i 和變更的狀態isChecked=b
                        //沒有要做什麼此區可省略，boolean陣列會自動更新
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //這裡的i是OK鈕
                        StringBuilder sb = new StringBuilder();
                        for (int j=0; j < selected.length; j++){ //i被用掉了改用j
                            if(selected[j]){
                                sb.append(items[j]).append("\n");
                            }
                        }
                        sb.setLength(sb.length() - 2 ); //去掉最後一個\n
                        m_tv_message.setText(sb);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //這裡的i是CANCEL鈕
                        m_tv_message.setText("無言");
                    }
                })
                .show();
    }

    //單選清單
    private  int mChoice;
    public void clickAlertDialogSingleChoice(View view) {

        final String[] items = getResources().getStringArray(R.array.myResponse);
        mChoice = 1; //將預設選項設為第1項

        new AlertDialog.Builder(this)
                .setTitle("你好帥喔")
                .setSingleChoiceItems(items, mChoice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //這裡的i是Radio鈕 <= 重點要這個
                        mChoice = i; //此OnClick執行完i會消失 所以要記錄在mChoice裡
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //這裡的i是OK鈕
                        m_tv_message.setText(items[mChoice]);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { //這裡的i是CANCEL鈕
                        m_tv_message.setText("無言薯條");
                    }
                }).show();
    }

    //自訂Fragment
    public void clickMyDialogFragment(View view) {
        //建立自訂的Dialog
        DialogFragment dialog = new MyDialogFragment(); //要注意import的DialogFragment版本和Fragment的是否一致
        //顯示Dialog    show(FragmentManager, tag);
        dialog.show(getSupportFragmentManager(), "MyDialogFragment");
    }

    //implement自訂的MyDialogInterface後必須要實作的兩個按鈕 (從fragment裡callback)
    @Override
    public void myDialogOk(CharSequence user) { //Fragment callback (由Fragment呼叫)
        m_tv_message.setText(user);
    }

    @Override
    public void myDialogCancel() { //Fragment callback (由Fragment呼叫)
        m_tv_message.setText("忘記登入資訊了嗎？");
    }

}
