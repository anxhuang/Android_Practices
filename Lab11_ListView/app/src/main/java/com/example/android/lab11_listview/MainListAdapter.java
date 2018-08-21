package com.example.android.lab11_listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Pokemon> list;

    public MainListAdapter(Activity activity, List<Pokemon> list) { //要求使用這個Adapter的對象提供一個Pokemon陣列
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.activity_main_item, null);
        TextView tv_ItemID = v.findViewById(R.id.itemId);
        TextView tv_ItemName = v.findViewById(R.id.itemName);
        ImageView iv_ItemImage = v.findViewById(R.id.itemImage);

        Pokemon pkm = list.get(i); //根據i取出目前要的第i個Pokemon的
        tv_ItemID.setText(pkm.getId()); //透過get跟他要這些基本資料
        tv_ItemName.setText(pkm.getName());
        iv_ItemImage.setImageResource(pkm.getDrawableId());

        return v;
    }
}
