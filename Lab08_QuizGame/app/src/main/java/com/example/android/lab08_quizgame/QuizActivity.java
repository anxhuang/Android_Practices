package com.example.android.lab08_quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.service.autofill.TextValueSanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG="TAG-Quiz";

    private TextView tv_no;
    private TextView tv_quiz;
    private TextView tv_bullet;
    private RadioButton radio_a;
    private RadioButton radio_b;
    private RadioButton radio_c;
    private RadioButton radio_picked;

    private int no;
    private int[] mAnswer;
    private StringBuilder mBullet = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tv_no = findViewById(R.id.tv_no);
        tv_quiz = findViewById(R.id.tv_quiz);
        tv_bullet = findViewById(R.id.tv_bullet);
        radio_a = findViewById(R.id.radio_a);
        radio_b = findViewById(R.id.radio_b);
        radio_c = findViewById(R.id.radio_c);

        Log.d(TAG,"onCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        no = bundle.getInt(MainActivity.BUNDLE_KEY_CURRENT, 0);
        mAnswer = bundle.getIntArray(MainActivity.BUNDLE_KEY_ANSWER);

        tv_no.setText(String.valueOf(no+1));
        tv_quiz.setText(MainActivity.quiz[no]);
        //更新bullet bar
        for(int i=0; i<mAnswer.length; i++) {
            if(i != no){
                mBullet.append(" ∙ ");
            }else{
                mBullet.append("・");
            }
        }
        tv_bullet.setText(mBullet);

        radio_a.setText(MainActivity.item[no][0]);
        radio_b.setText(MainActivity.item[no][1]);
        radio_c.setText(MainActivity.item[no][2]);

        if(mAnswer[no] != 0){
           radio_picked = findViewById(mAnswer[no]);
           radio_picked.setChecked(true);
        }

        Log.d(TAG,"onResume() no:"+no+" mAnswer[no]:"+mAnswer[no]);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getSharedPreferences("com.example.android.lab08_quizgame", MODE_PRIVATE).edit();
        //只能用for做editor.putInt();
        for(int i=0; i<mAnswer.length; i++){
            editor.putInt("save_"+i,mAnswer[i]);
        }
        editor.commit();
    }

    public void clickAnswer(View view){
        mAnswer[no] = view.getId();
    }

    public void clickNext(View view) {
        if (no < MainActivity.quiz.length-1){
            no += 1;
            doIntent(QuizActivity.class, R.anim.right_bottom_in, R.anim.left_bottom_out); //帶入bottom_in動畫
        }else {
            doIntent(ResultActivity.class, R.anim.right_bottom_in, R.anim.left_bottom_out);
        }

    }

    //自訂傳入參數分別為: 目標activity, 進場動畫, 退出動畫
    public void doIntent(Class c, int anim_in, int anim_out){
        Intent intent = getIntent();
        intent.setClass(this, c);
        intent.putExtra(MainActivity.BUNDLE_KEY_CURRENT, no);
        intent.putExtra(MainActivity.BUNDLE_KEY_ANSWER, mAnswer);
        startActivity(intent);
        overridePendingTransition(anim_in,anim_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (no > 0) {
            no -= 1;
            doIntent(QuizActivity.class, R.anim.left_push_in,R.anim.right_push_out); //帶入push_in動畫
        }else {
            doIntent(MainActivity.class, R.anim.left_push_in,R.anim.right_push_out);
        }
    }
}
