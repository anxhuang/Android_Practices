package com.example.android.lab12_spinner;


import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener{

    interface MyDialogInterface{
        void onClickOk(String title);
        void onClickCancel();
    }

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    private String selected_title;

    public MyDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        //設定Fragment的layout
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);

        //設定SpinnerAdapter
        Spinner spinner = view.findViewById(R.id.coffee_spinner);
        SpinnerAdapter adapter = new MySpinnerAdapter(getActivity());
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //設定AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("新增商品")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(view)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if( getActivity() instanceof MyDialogInterface){
                            MyDialogInterface x = (MyDialogInterface) getActivity();
                            x.onClickOk(selected_title);
                        }
                    }
                })
                .setNegativeButton("取消",null);

        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TypedArray title_array = getResources().obtainTypedArray(R.array.title_array);
        selected_title = title_array.getString(i);
        Log.d(TAG, "title="+title_array.getText(i));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
