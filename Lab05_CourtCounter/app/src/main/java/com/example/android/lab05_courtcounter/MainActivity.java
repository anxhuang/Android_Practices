package com.example.android.lab05_courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private int scoreTeamA = 0;
    //private int scoreTeamB = 0;
    private CourtCounterFragment a;
    private CourtCounterFragment b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (CourtCounterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_team_a);
        b = (CourtCounterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_team_b);

        /* ======> NullPointerException
        //Activity onCreate()階段，Fragment尚未執行onStart()，在此設定片段TextView過早
        a.setTeamName("黃蜂");
        b.setTeamName("火箭");
        */

    }

    //這個階段是在onStart()之後，所以就可以找到TextView了
    @Override
    protected void onResume() {
        super.onResume();
        //使用Fragment提供的方法來設定隊名，值在value要用getString從string資源調用
        a.setTeamName(getString(R.string.team_a_name));
        b.setTeamName(getString(R.string.team_b_name));

        //設定隊伍圖片，傳送resId給Fragment
        a.setTeamLogo(R.drawable.team_a_logo);
        b.setTeamLogo(R.drawable.team_b_logo);
    }

    public void onBtnReset(View view) {
        a.resetScore();
        b.resetScore();
    }


    /* ======捨棄此作法2======
    public void onButtonDown(View view){

         ======捨棄此作法1======
        //作法1: 要先用自訂的Fragment類別建立物件 透過getSupportFragmentManager()片段管理員去取得片段
        CourtCounterFragment a = (CourtCounterFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_team_a);
        //透過getter去取得Fragment內私有的TextView
        TextView team_score_a = a.getM_tv_score();
           ======捨棄此作法1======

        //作法2: 用getParent()去找出view的外層父容器, 再回來找內部我們要的TextView
        LinearLayout ll = (LinearLayout)view.getParent();
        TextView team_score = ll.findViewById(R.id.tv_score);

        switch (view.getId()){
            case R.id.btn_triple:
                scoreTeamA += 3;
                team_score.setText(String.valueOf(scoreTeamA));
                break;
            case R.id.btn_double:
                scoreTeamA += 2;
                team_score.setText(String.valueOf(scoreTeamA));
                break;
            case R.id.btn_penalty:
                scoreTeamA += 1;
                team_score.setText(String.valueOf(scoreTeamA));
                break;
        }
    }
        ======捨棄此作法2======  */
}
