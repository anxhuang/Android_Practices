package com.example.android.lab12_spinner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

public class MyEditFragment extends MyDialogFragment {

    private MyEditInterface myEditHandler;

    interface MyEditInterface{
        void onClickEdit(Coffee coffee);
        void onClickDelete();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        initMyEditHandler();

        return super.onCreateDialog(savedInstanceState);

        //et_price.setText();******************要再傳入coffee來取值？

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
    protected void initSpinner() {
        super.initSpinner();

        //mSpinner.setSelection();*******************要再傳入coffee來取值？
    }

    @Override
    protected AlertDialog initDialog() {
        //設定AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("修改商品")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(mView)
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Coffee coffee = getCoffee();
                        myEditHandler.onClickEdit(coffee);
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

        //讓"確定"按鈕預設不可按
        final AlertDialog dialog = builder.create(); //setOnShowListener要在AlertDialog設定 不是Builder

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        });

        //設定EditText輸入文字的監視器
        setEditTextWatcher(dialog);

        return dialog;
    }
}
