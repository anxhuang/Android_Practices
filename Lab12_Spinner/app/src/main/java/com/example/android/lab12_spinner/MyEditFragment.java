package com.example.android.lab12_spinner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

public class MyEditFragment extends MyDialogFragment {

    private MyEditInterface myEditHandler;
    private Coffee mCoffee;

    interface MyEditInterface{
        void onClickModify(Coffee coffee);
        void onClickDelete();
    }

    //因為要保留empty constructor所以改寫成自訂的initializer
    public static MyEditFragment newInstance(Coffee mCoffee){ //自訂initializer必為public static, return type = 自己
        MyEditFragment f = new MyEditFragment();
        f.mCoffee = mCoffee;
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        initMyEditHandler();

        return super.onCreateDialog(savedInstanceState);
    }

    private void initMyEditHandler() {
        //提早確定getActivity()有沒有實作MyDialogInterface 沒有就丟出自訂例外
        try{
            myEditHandler = (MyEditInterface) getActivity(); //這裡的myDialogHandler就是MainActivity
        }catch(ClassCastException e){
            String message = "Activity沒有實作介面 無法處理刪除";
            throw new MyDialogFragmentException(message, e);
        }
    }

    @Override
    protected AlertDialog initDialog() {
        //因為initDialog在父類別是OnCreateDialog最後一個執行的，所以用來作追加設定剛好
        et_price.setText(String.valueOf(mCoffee.getPrice()));
        mSpinner.setSelection(mCoffee.getId());
        //

        //設定AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("修改商品")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(mView)
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Coffee coffee = getCoffee();
                        myEditHandler.onClickModify(coffee);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myDialogHandler.onClickCancel();
                    }
                })
                .setNeutralButton("刪除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myEditHandler.onClickDelete();
                    }
                });


        AlertDialog dialog = builder.create();

        //設定EditText輸入文字的監視器
        setEditTextWatcher(dialog);

        return dialog;
    }
}
