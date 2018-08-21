package com.example.android.lab11_listview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

public class AddActivity extends AppCompatActivity {

    static final String BUNDLE_KEY_POKEMON = "com.example.android.lab11_listview.pokemon";

    private static final int requestPickImage = 0;

    private EditText mEtId;
    private EditText mEtName;
    private ImageView mIb;
    private int resId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
    }

    private void initView() {
        mEtId = findViewById(R.id.et_id);
        mEtName = findViewById(R.id.et_name);
        mIb = findViewById(R.id.ib);
    }

    public void pickImage(View view) {
        Intent intent = getIntent();
        intent.setClass(this, ListActivity.class);
        startActivityForResult(intent,requestPickImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case requestPickImage:
                    resId = data.getIntExtra(ListActivity.BUNDLE_KEY_INT_ITEM_IMAGE, -1); //略過bundle的取法
                    if (resId != -1){ //避免錯誤產生要加判斷
                        mIb.setImageResource(resId);
                    }
                    break;
                default:
                    break;
            }
        }
        //
        Log.d("TAG-onActivityResult","imgId:"+ resId +" KEY:"+ListActivity.BUNDLE_KEY_INT_ITEM_IMAGE);
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_ok:
                Pokemon pkm = new Pokemon(mEtId.getText().toString(), mEtName.getText().toString(), resId); //建立一個實體傳回去Main
                Intent intent = getIntent();
                intent.putExtra(BUNDLE_KEY_POKEMON, pkm); //把實體交給intent
                setResult(RESULT_OK, intent); //!!! 不要忘記 要把intent放進去Result
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
