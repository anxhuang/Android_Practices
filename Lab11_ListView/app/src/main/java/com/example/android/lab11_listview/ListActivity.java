package com.example.android.lab11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{ //要讓Adapter的Item可以點選要實作這個介面

    static String BUNDLE_KEY_INT_ITEM_IMAGE = "com.example.android.lab11_listview.itemImage";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initListView();
    }

    private void initListView() {
        mListView = findViewById(R.id.listview);
        mListView.setEmptyView(findViewById(R.id.empty)); //設定當getCount回傳0時要呈現的View
        mListView.setAdapter(new MyListAdapter(this)); //設定資料轉接到我們自訂的MyListAdapter類別
        mListView.setOnItemClickListener(this); //設定處理點選項目的外包商
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //
        Intent intent = getIntent();
        intent.putExtra(BUNDLE_KEY_INT_ITEM_IMAGE,i);
        setResult(RESULT_OK,intent);
        //
        Log.d("TAG-ListActivity","第"+i+"項 被點選了");
        finish(); //返回前一畫面
    }
}
