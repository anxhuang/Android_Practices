package com.example.android.lab09_dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    private EditText m_et_username;

    //自訂一個interface讓想要使用這個Fragment的Activity只要implement後就可以遙控
    interface MyDialogInterface{ //預設就是 public abstract
        void myDialogOk(CharSequence user); //預設就是 public abstract
        void myDialogCancel();
    }


    public MyDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //透過inflater，讀取fragment.xml資源來生成view
        LayoutInflater inflater = getActivity().getLayoutInflater(); //取得inflater
        //從fragment.xml取得自訂畫面 inflate(resource, viewGroup);
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);
        //初始化 edit_view
        m_et_username = view.findViewById(R.id.et_username);

        //建立AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view) //!!!在這裡設定自訂view
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //正面按鈕描述myDialogOk()的callback
                        if(getActivity() instanceof MyDialogInterface){ //要判斷目前的對象Activity是否有實作我們自訂的interface
                            MyDialogInterface x = (MyDialogInterface)getActivity(); //先幫他轉型讓他可以遙控interface的功能
                            x.myDialogOk(m_et_username.getText());
                        }else{
                            throw new RuntimeException("Activity沒有實作MyDialogInterface");
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //負面按鈕描述myDialogCancel()的callback
                        if(getActivity() instanceof MyDialogInterface){ //要判斷目前的對象Activity是否有實作我們自訂的interface
                            MyDialogInterface x = (MyDialogInterface)getActivity(); //先幫他轉型讓他可以遙控interface的功能
                            x.myDialogCancel();
                        }
                    }
                }); //不用show() 要在return回傳create()建立的Dialog

        //return super.onCreateDialog(savedInstanceState);
        return builder.create();
    }
}
