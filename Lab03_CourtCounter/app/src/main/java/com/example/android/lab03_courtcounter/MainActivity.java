package com.example.android.lab03_courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView m_tv_score;
    private int m_int_score =0;
    private TextView m_tv_scoreB;
    private int m_int_scoreB =0;

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
        }
        m_tv_score = findViewById(R.id.tv_score);
        m_tv_score.setText(String.valueOf(m_int_score));
    }

    public void onButtonDownB(View view){
        switch (view.getId()){
            case R.id.btn_tripleB:
                m_int_scoreB += 3;
                break;
            case R.id.btn_doubleB:
                m_int_scoreB += 2;
                break;
            case R.id.btn_penaltyB:
                m_int_scoreB += 1;
                break;
        }
        m_tv_scoreB = findViewById(R.id.tv_scoreB);
        m_tv_scoreB.setText(String.valueOf(m_int_scoreB));
    }


    public void onBtnReset(View view) {
        m_int_score = 0;
        m_int_scoreB = 0;
        m_tv_score.setText(String.valueOf(m_int_score));
        m_tv_scoreB.setText(String.valueOf(m_int_scoreB));
    }
}
