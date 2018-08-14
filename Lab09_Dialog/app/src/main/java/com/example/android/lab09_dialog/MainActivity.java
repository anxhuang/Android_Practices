package com.example.android.lab09_dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener{ //要實作Listener

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
                .setPositiveButton("謝謝", myListener)
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
}
