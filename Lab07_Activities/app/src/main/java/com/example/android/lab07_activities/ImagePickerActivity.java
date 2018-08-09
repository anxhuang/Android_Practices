package com.example.android.lab07_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImagePickerActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY_IMAGE_INT = "com.example.android.lab07_activities.imageInt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
    }

    public void clickImage(View view) {

        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.imgbtn_hornets:
                intent.putExtra(BUNDLE_KEY_IMAGE_INT, R.drawable.hornets);
                break;
            case R.id.imgbtn_rockets:
                intent.putExtra(BUNDLE_KEY_IMAGE_INT, R.drawable.rockets);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();

    }
}
