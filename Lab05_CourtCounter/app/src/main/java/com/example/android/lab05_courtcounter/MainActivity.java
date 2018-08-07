package com.example.android.lab05_courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonDown(View view){
        //要先用自訂的Fragment類別建立物件 透過getSupportFragmentManager()片段管理員去取得片段
        CourtCounterFragment a = (CourtCounterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_team_a);
        //透過getter去取得Fragment內私有的TextView
        TextView team_score_a = a.getM_tv_score();
        switch (view.getId()){
            case R.id.btn_triple:
                scoreTeamA += 3;
                team_score_a.setText(String.valueOf(scoreTeamA));
                break;
            case R.id.btn_double:
                scoreTeamA += 2;
                team_score_a.setText(String.valueOf(scoreTeamA));
                break;
            case R.id.btn_penalty:
                scoreTeamA += 1;
                team_score_a.setText(String.valueOf(scoreTeamA));
                break;
        }
    }
}
