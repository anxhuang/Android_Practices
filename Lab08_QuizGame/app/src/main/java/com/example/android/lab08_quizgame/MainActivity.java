package com.example.android.lab08_quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY_CURRENT = "com.example.android.lab08_quizgame.current";
    public static final String BUNDLE_KEY_ANSWER = "com.example.android.lab08_quizgame.answer";

    private static final String TAG="TAG-Main";

    private static int current = 0;
    private static int quiz_qty = 5; //總題數
    private static int item_qty= 3; //總題數

    static String[] quiz=new String[quiz_qty]; //題目 default package access only
    static String[][] item=new String[quiz_qty][item_qty]; //[題號][答案選項數]

    private static int[] answer=new int[quiz_qty]; //玩家作答

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ex.1 用for產生題目和答案
        /*
        for (int i = 0; i< quiz_qty; i++){
            quiz[i]="這是題目"+(i+1);
            for (int j=0; j<item_qty; j++){
                char c=(char)(65+j);
                item[i][j]=c+" 這是題目"+(i+1)+"的答案"+(j+1);
                Log.d(TAG,"onCreate() quiz:"+quiz[i]+" items:"+item[i][j]);
            }
        }
        */
        //ex.2 從strings.xml帶入
        quiz = getResources().getStringArray(R.array.quiz_text);
        item[0] = getResources().getStringArray(R.array.item_text_q1);
        item[1] = getResources().getStringArray(R.array.item_text_q2);
        item[2] = getResources().getStringArray(R.array.item_text_q3);
        item[3] = getResources().getStringArray(R.array.item_text_q4);
        item[4] = getResources().getStringArray(R.array.item_text_q5);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("com.example.android.lab08_quizgame", MODE_PRIVATE);
        for(int i=0; i<answer.length; i++) {
            answer[i]=prefs.getInt("save_"+i,0);
            if (answer[i] != 0){
                //有進度才出現
                Button btn_load = findViewById(R.id.btn_load);
                btn_load.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getSharedPreferences("com.example.android.lab08_quizgame", MODE_PRIVATE).edit();
        //只能用for做editor.putInt();
        for(int i=0; i<answer.length; i++){
            editor.putInt("save_"+i,answer[i]);
        }
        editor.commit();
    }

    public void startNew(View view) {
        current = 0;
        Arrays.fill(answer,0);
        doIntent();
        Log.d(TAG,"startNew() current:"+current);
    }

    public void loadGame(View view) {
        doIntent();
    }

    private void doIntent(){
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(BUNDLE_KEY_CURRENT, current);
        intent.putExtra(BUNDLE_KEY_ANSWER, answer);
        startActivity(intent);
        overridePendingTransition(R.anim.right_bottom_in,R.anim.left_bottom_out);
    }
}
