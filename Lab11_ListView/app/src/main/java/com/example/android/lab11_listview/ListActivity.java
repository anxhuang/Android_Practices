package com.example.android.lab11_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{ //要讓Adapter的Item可以點選要實作這個介面

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    static String BUNDLE_KEY_INT_ITEM_IMAGE = "com.example.android.lab11_listview.itemImage";

    private ListView mListView;
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initListView();
        Log.d(TAG," after onCreate");
    }

    private void initListView() {
        mAdapter = new MyListAdapter(this); //建立Adapter實體 1.setAdapter要用 2.讀取裡面的drawableIDs[]
        mListView = findViewById(R.id.listview);
        mListView.setEmptyView(findViewById(R.id.empty)); //設定當getCount回傳0時要呈現的View
        mListView.setAdapter(mAdapter); //設定資料轉接到我們自訂的MyListAdapter類別
        mListView.setOnItemClickListener(this); //設定處理點選項目的外包商
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //哪一個AdapterView被按就傳入該AdapterView
        //
        int[] drawableIDs= mAdapter.getDrawableIDs(); //透過Adapter的實體.getter()來讀取裡面的資料

        Intent intent = getIntent();
        intent.putExtra(BUNDLE_KEY_INT_ITEM_IMAGE,drawableIDs[i]);
        setResult(RESULT_OK,intent);
        //
        Log.d(TAG,"第"+i+"項 被點選了 drawableIDs[i]:"+drawableIDs[i]);
        finish(); //返回前一畫面
    }
}
