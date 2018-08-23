package com.example.android.lab11_listview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class UpdateActivity extends AddActivity {

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    private Pokemon pkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        Log.d(TAG,"onCreate after init()");
    }

    private void init(){
        Intent intent = getIntent();
        pkm = (Pokemon) intent.getSerializableExtra(MainActivity.BUNDLE_KEY_UPDATE_POKEMON);
        mEtId.setText(pkm.getId());
        mEtName.setText(pkm.getName());
        resId = pkm.getDrawableId();//因為Add是由resId在記錄圖片id 要避免沒有重選圖的情況 所以先給他
        mIb.setImageResource(resId);
        enable_mBtn_ok(); //不用重選圖就可以按
        Log.d(TAG,"init() pkm.getId()="+pkm.getId());
    }

    @Override
    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_ok:
                Pokemon pkm = new Pokemon(mEtId.getText().toString(), mEtName.getText().toString(), resId); //因為Pokemen沒有setter所以要新建一個實體回傳
                Intent intent = getIntent();
                intent.putExtra(MainActivity.BUNDLE_KEY_UPDATE_POKEMON, pkm); //和AddActivity的click只差在這ㄧ行
                setResult(RESULT_OK, intent);
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}

/*
*  資料處理的觀念裡 盡量只要用getter 不要放setter 讓資料盡可能在初始化之後就唯讀
*  因為在日後多緒處理的情況下 很容易會產生寫入衝突和需做lock的動作
*  故資料唯讀化並多利用抽換的動作可以讓效率提高
*  就算可能佔用多一些記憶體 也是值得
*/