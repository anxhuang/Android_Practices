package com.example.android.lab11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddActivity extends AppCompatActivity {

    private final int requestPickImage = R.id.itemImage;

    private ImageView mIb;
    private int imgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mIb = findViewById(R.id.ib);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        imgId = bundle.getInt(ListActivity.BUNDLE_KEY_INT_ITEM_IMAGE,0);
        mIb.setImageResource(imgId);*/
    }

    public void pickImage(View view) {
        Intent intent = getIntent();
        intent.setClass(this, ListActivity.class);
        startActivity(intent);
    }
}
