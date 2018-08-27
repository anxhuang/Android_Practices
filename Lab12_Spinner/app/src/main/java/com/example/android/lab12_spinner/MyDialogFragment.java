package com.example.android.lab12_spinner;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    private MyDialogInterface myDialogHandler;
    private View mView;
    private Spinner mSpinner;
    private SpinnerAdapter mAdapter;
    private AlertDialog mDialog;

    interface MyDialogInterface{
        void onClickOk(Coffee coffee);
        void onClickCancel();
    }

    public MyDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        initMyDialogHandler();

        initDialogView();

        initSpinner();

        mDialog = initDialog();

        return mDialog;
    }

    private void initMyDialogHandler() {
        //提早確定getActivity()有沒有實作MyDialogInterface 沒有就丟出自訂例外
        try{
            myDialogHandler = (MyDialogInterface) getActivity(); //這裡的myDialogHandler就是MainActivity
        }catch(ClassCastException e){
            String message = "Activity沒有實作介面 無法處理確定取消";
            throw new MyDialogFragmentException(message, e);
        }
    }

    private void initDialogView() {
        //設定Fragment的layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_my_dialog, null);
    }

    private void initSpinner() {
        //設定SpinnerAdapter
        mSpinner = mView.findViewById(R.id.coffee_spinner);
        mAdapter = new MySpinnerAdapter(getActivity());
        mSpinner.setAdapter(mAdapter);
        //mSpinner.setOnItemSelectedListener(this); *****沒用先暫時拿掉*****
        //mSpinner.setSelection(0);//預設選擇第0項 可不寫
    }

    private AlertDialog initDialog() {
        //設定AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("新增商品")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(mView)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Coffee coffee = getCoffee();
                        myDialogHandler.onClickOk(coffee);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myDialogHandler.onClickCancel();
                    }
                });

        return builder.create();
    }

    private Coffee getCoffee(){
        //目前選到哪一個
        int i = mSpinner.getSelectedItemPosition();
        //咖啡名稱
        TypedArray title_array = getResources().obtainTypedArray(R.array.title_array);
        String title = title_array.getText(i).toString();
        //咖啡價格
        EditText et_price = mView.findViewById(R.id.coffee_price);
        int price = Integer.parseInt(et_price.getText().toString()); //*****要加沒輸入的判斷*****
        //咖啡圖片
        TypedArray imgId_array = getResources().obtainTypedArray(R.array.drawable_array); //這裡要重新建立一個int[] 放resId
        String drawable_name = imgId_array.getDrawable(i).toString();//*****回傳錯誤要解*****
        int imgId = getResources().getIdentifier(drawable_name, "drawable", getActivity().getPackageName());//*****回傳錯誤要解*****

        Log.d(TAG, "title="+title+" price="+price+" imgId="+imgId+" drawable_name="+drawable_name);

        return new Coffee(title, price, imgId);

    }

}
