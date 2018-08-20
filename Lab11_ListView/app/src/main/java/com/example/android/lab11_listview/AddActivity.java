package com.example.android.lab11_listview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddActivity extends AppCompatActivity {

    /*
    static final String BUNDLE_KEY_STRING_ET_ID = "com.example.android.lab11_listview.et_id";
    static final String BUNDLE_KEY_STRING_ET_NAME = "com.example.android.lab11_listview.et_name";
    */

    private final int requestPickImage = 0;

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

    public void addItem(View view) {

        Pokemon pkm = new Pokemon(mEtId.getText().toString(), mEtName.getText().toString(), resId);

        Intent intent = getIntent();
        intent.put???
        setResult(RESULT_OK);
        finish();
    }
}
