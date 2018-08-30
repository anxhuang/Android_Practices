package com.example.android.lab12_spinner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Coffee> coffees;

    public MyListAdapter(Activity activity, List<Coffee> coffees) {
        this.activity = activity;
        this.coffees = coffees;
    }


    @Override
    public int getCount() {
        return coffees.size();
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
        View v = activity.getLayoutInflater().inflate(R.layout.listview_layout, null);

        TextView tvItemId = v.findViewById(R.id.item_id);
        TextView tvItemTitle = v.findViewById(R.id.item_title);
        TextView tvItemPrice = v.findViewById(R.id.item_price);
        ImageView ivItemImage = v.findViewById(R.id.itemImage);

        tvItemId.setText(String.valueOf(coffees.get(i).getId()));
        tvItemTitle.setText(coffees.get(i).getTitle());
        tvItemPrice.setText(String.valueOf(coffees.get(i).getPrice()));
        ivItemImage.setImageResource(coffees.get(i).getImgId());

        return v;
    }
}
