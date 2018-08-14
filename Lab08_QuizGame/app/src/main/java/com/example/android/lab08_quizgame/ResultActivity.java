package com.example.android.lab08_quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private int score = 0;
    private int[] result;
    private TextView tv_no;
    private TextView tv_result;
    private TextView tv_feedback;
    private TextView tv_score;
    private final String[] correct={"A","B","C","B","A"}; //正確答案
    private HashMap hm = new HashMap();
    private StringBuilder sb_no = new StringBuilder();
    private StringBuilder sb_rs = new StringBuilder();
    private StringBuilder sb_fb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //對照用的Map
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
            if (hm.get(result[i]) == correct[i]){
                sb_fb.append("讚喔!\n");
                score += 200;
            }else if (hm.get(result[i]) == "No Answered"){
                sb_fb.append("猜嘛~\n");
            }else{
                sb_fb.append("認真?\n");
            }
        }

        tv_no = findViewById(R.id.tv_no_list);
        tv_no.setText(sb_no);

        tv_result = findViewById(R.id.tv_result_list);
        tv_result.setText(sb_rs);

        tv_feedback = findViewById(R.id.tv_feedback_list);
        tv_feedback.setText(sb_fb);

        tv_score = findViewById(R.id.tv_score);
        tv_score.setText(String.valueOf(score));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //返回的動畫版本
        overridePendingTransition(R.anim.left_push_in,R.anim.right_push_out);
    }

    public void goHome(View view) {
        Intent intent = getIntent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_push_in,R.anim.right_push_out);
    }
}
