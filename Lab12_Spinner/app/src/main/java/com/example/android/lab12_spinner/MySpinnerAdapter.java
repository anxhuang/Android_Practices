package com.example.android.lab12_spinner;

import android.app.Activity;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySpinnerAdapter extends BaseAdapter {

    private final String TAG = "TAG-"+this.getClass().getSimpleName();

    private Activity activity;
    private TypedArray title_array;
    private TypedArray drawable_array;

    public MySpinnerAdapter(Activity activity) {
        this.activity = activity;
        title_array = activity.getResources().obtainTypedArray(R.array.title_array);
        drawable_array = activity.getResources().obtainTypedArray(R.array.drawable_array);
    }

    @Override
    public int getCount() {
        return 4;
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
        View v = activity.getLayoutInflater().inflate(R.layout.spinner_layout, null);

        TextView title = v.findViewById(R.id.coffee_title);
        title.setText(title_array.getText(i));

        ImageView drawable = v.findViewById(R.id.coffee_drawable);
        drawable.setImageDrawable(drawable_array.getDrawable(i));

        //Log.d(TAG, "title="+title_array.getText(i)+"; drawable="+drawable_array.getDrawable(i));

        return v;
    }

    public TypedArray getTitle_array() {
        return title_array;
    }

    public TypedArray getDrawable_array() {
        return drawable_array;
    }
}
