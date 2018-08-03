package com.example.android.lab03_courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView m_tv_score;
    private int m_int_score =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonDown(View view){
        switch (view.getId()){
            case R.id.btn_triple:
                m_int_score += 3;
                break;
            case R.id.btn_double:
                m_int_score += 2;
                break;
            case R.id.btn_penalty:
                m_int_score += 1;
                break;
            case R.id.btn_reset:
                m_int_score = 0;
                break;
        }
        m_tv_score = findViewById(R.id.tv_score);
        m_tv_score.setText(String.valueOf(m_int_score));
    }
}
