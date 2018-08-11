package com.example.android.lab08_quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private int[] result;
    private TextView no_list;
    private TextView result_list;
    private HashMap hm = new HashMap();
    private StringBuilder sb_no = new StringBuilder();
    private StringBuilder sb_rs = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        hm.put(R.id.radio_a,"A");
        hm.put(R.id.radio_b,"B");
        hm.put(R.id.radio_c,"C");
        hm.put(0,"No Answered");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        result = bundle.getIntArray(MainActivity.BUNDLE_KEY_ANSWER);

        for (int i=0; i<result.length; i++){
            sb_no.append((i+1)+"."+"\n");
            sb_rs.append(hm.get(result[i])+"\n");
        }

        no_list = findViewById(R.id.no_list);
        no_list.setText(sb_no);

        result_list = findViewById(R.id.result_list);
        result_list.setText(sb_rs);
    }
}
