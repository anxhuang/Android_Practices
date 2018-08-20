package com.example.android.lab11_listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter { //BaseAdapter是抽象類別 裡面規定的方法都會自動執行


    private static int[] drawableIDs={
            R.drawable.p01, R.drawable.p02, R.drawable.p03, R.drawable.p04, R.drawable.p05,
            R.drawable.p06, R.drawable.p07, R.drawable.p08, R.drawable.p09, R.drawable.p10,
            R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15,
            R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20
    };

    public static int[] getDrawableIDs() {
        return drawableIDs;
    }

    private Activity activity; //因為常常要用到MainActivity的資料(例如打氣筒) 所以在建構的時候把他帶進來

    public MyListAdapter(Activity activity) { //加寫建構子 在建構的時候把Activity帶進來
        this.activity = activity;
    }

    @Override
    public int getCount() { //ListView.setEmptyView()會主動執行這個 如果回傳0表示沒有資料
        return 20; //要和自訂的項目數量相同
    }

    @Override
    public Object getItem(int i) { //結合資料庫才會用到
        return null;
    }

    @Override
    public long getItemId(int i) { //結合資料庫才會用到
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //系統會自動呼叫這個方法 一個個產生新item
        //跟Activity借打氣筒 將layout灌入view
        View v = activity.getLayoutInflater().inflate(R.layout.listview_item,null); //每個單位item的view
        //View產生了再來找TextView和ImageView
        TextView tvItemId = v.findViewById(R.id.itemId);
        tvItemId.setText(String.valueOf(i));

        ImageView ivItemImage = v.findViewById(R.id.itemImage);
        ivItemImage.setImageResource(drawableIDs[i]);

        return v; //要把view回傳給系統幫我們管理
    }
}
