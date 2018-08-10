package com.example.android.lab08_quizgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Lab08-Main";
    public static final String BUNDLE_KEY_CURRENT_INT = "com.example.android.lab08_quizgame.current";
    public static int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = 0;

        int total = 5; //總題數
        String[] items=new String[4]; //答案選項數
        ArrayList<String> quiz = new ArrayList<>();
        ArrayList<String[]> answer = new ArrayList<>();
        items[0]=""; //墊index的
        answer.add(items); //墊index的

        for (int i=1; i<=total; i++){
            quiz.add("這是題目"+i);
            for (int j=1; j<4; j++){
                items[j]="這是題目"+i+"的答案"+j;
                Log.d(TAG,"onCreate() quiz:"+quiz+" items:"+items[j]);
            }
            answer.add(items);
            Log.d(TAG,"onCreate() quiz:"+quiz+" answer:"+answer.get(i));
        }
    }


    public void startNew(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra(BUNDLE_KEY_CURRENT_INT,current);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}
