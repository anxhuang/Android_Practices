package com.example.android.lab11_listview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    static final String BUNDLE_KEY_ADD_POKEMON = "com.example.android.lab11_listview.add_pokemon";
    static final String BUNDLE_KEY_ADD_STATE = "com.example.android.lab11_listview.add_state";

    private final int requestPickImage = 0;

    protected EditText mEtId; //因為要給子類別UpdateActivity使用 所以都宣告為protected
    protected EditText mEtName;
    protected ImageButton mIb;
    protected int resId;
    protected Button mBtn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG," in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        //復原savedInstanceState要在找完View後復原
        if(savedInstanceState != null){
            Pokemon pkmld = (Pokemon) savedInstanceState.getSerializable(BUNDLE_KEY_ADD_STATE);
            mEtId.setText(pkmld.getId());
            mEtName.setText(pkmld.getName());
            resId = pkmld.getDrawableId();
            if(resId != R.drawable.no_image_box){
                mIb.setImageResource(resId);
                enable_mBtn_ok();
            }
        }
    }

    private void initView() {
        mEtId = findViewById(R.id.et_id);
        mEtName = findViewById(R.id.et_name);
        mIb = findViewById(R.id.ib);
        mBtn_ok = findViewById(R.id.btn_ok);
    }

    public void pickImage(View view) {
        Log.d(TAG, "click pickImage Button");
        Intent intent = new Intent(this, ListActivity.class); // !!!因為有要Result一定要new 混用會頁面錯亂
        startActivityForResult(intent,requestPickImage);
    }

    protected void enable_mBtn_ok(){
        mBtn_ok.setEnabled(true); //按鈕恢復可按
        mBtn_ok.setBackgroundColor(getResources().getColor(R.color.colorPrimary));  //復原按鈕背景色
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
                        enable_mBtn_ok();
                    }
                    break;
                default:
                    break;
            }
        }
        //
        Log.d(TAG,"onActivityResult imgId:"+ resId);
    }

    public void click(View view) {
        Log.d(TAG,"onClick view.getId:"+ view.getId());
        switch (view.getId()){
            case R.id.btn_ok:
                Pokemon pkm = new Pokemon(mEtId.getText().toString(), mEtName.getText().toString(), resId); //建立一個實體傳回去Main
                Intent intent = getIntent();
                intent.putExtra(BUNDLE_KEY_ADD_POKEMON, pkm); //把實體交給intent
                setResult(RESULT_OK, intent); //!!! 不要忘記 要把intent放進去Result
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG,"onSaveInstanceState ");
        super.onSaveInstanceState(outState);
        Pokemon pkmsv = new Pokemon(mEtId.getText().toString(), mEtName.getText().toString(), resId);
        outState.putSerializable(BUNDLE_KEY_ADD_STATE, pkmsv);
    }

}
