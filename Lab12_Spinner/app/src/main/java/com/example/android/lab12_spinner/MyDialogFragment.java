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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    protected MyDialogInterface myDialogHandler;
    protected View mView;
    protected EditText et_price;
    protected Spinner mSpinner;
    private MySpinnerAdapter mSpinnerAdapter;
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
        et_price = mView.findViewById(R.id.coffee_price);
    }

    protected void initSpinner() {
        //設定SpinnerAdapter
        mSpinner = mView.findViewById(R.id.coffee_spinner);
        mSpinnerAdapter = new MySpinnerAdapter(getActivity());
        mSpinner.setAdapter(mSpinnerAdapter);
        //mSpinner.setOnItemSelectedListener(this); *****沒用先暫時拿掉*****
        //mSpinner.setSelection(0);//預設選擇第0項 可不寫
    }

    protected AlertDialog initDialog() {
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

    //EditText輸入文字監視器
    protected void setEditTextWatcher(final AlertDialog dialog) {
        et_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //當文字修改時，字串長度需 > 0
                if(charSequence.length() > 0 && Pattern.matches("\\d+", charSequence)){
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }else {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    protected Coffee getCoffee(){
        //目前選到哪一個
        int i = mSpinner.getSelectedItemPosition();
        //咖啡名稱
        TypedArray title_array = mSpinnerAdapter.getTitle_array();
        String title = title_array.getText(i).toString();
        //咖啡價格
        int price = 0; //可能沒有任何輸入 要給初值
        try {
             price = Integer.parseInt(et_price.getText().toString());
        }catch (NumberFormatException e){
            System.out.println("price數字轉換有誤");
        }
        //咖啡圖片
        TypedArray imgId_array = mSpinnerAdapter.getDrawable_array();
        int imgId = imgId_array.getResourceId(i, -1);//*****把DrawableId轉resId

        Log.d(TAG, "title="+title+" price="+price+" imgId="+imgId+" imgId="+imgId);

        return new Coffee(title, price, imgId);

    }

}
