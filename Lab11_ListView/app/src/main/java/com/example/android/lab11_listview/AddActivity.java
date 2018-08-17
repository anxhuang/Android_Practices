package com.example.android.lab11_listview;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AddActivity extends AppCompatActivity {

    private final int requestPickImage = 0;

    private ImageView mIb;
    private int imgIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
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
                    imgIdx = data.getIntExtra(ListActivity.BUNDLE_KEY_INT_ITEM_IMAGE, 0);
                    mIb.setImageResource(MyListAdapter.drawableIDs[imgIdx]);
                    break;
                default:
                    break;
            }
        }
        //
        Log.d("TAG-onActivityResult","imgId:"+imgIdx+" KEY:"+ListActivity.BUNDLE_KEY_INT_ITEM_IMAGE);
    }
}
