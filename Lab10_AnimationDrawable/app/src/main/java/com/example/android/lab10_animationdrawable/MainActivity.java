package com.example.android.lab10_animationdrawable;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView m_img_duke;
    private AnimationDrawable m_animationDrawable; //動畫物件
    private TextView m_tv_message;

    private View m_v_nba_logo;
    private TextView m_tv_nba_team;
    private TextView m_tv_nba_message;
    private Button m_btn_go;

    private TypedArray mNbaLogos; //資源檔 陣列
    private String[] mNbaTeams; //隊名 陣列
    private int mNbaLogosCount; //一共有多少張圖
    private boolean mNbaBtnSw=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFrameAnimation();
        initNbaLogos();
    }

    private void initView(){
        m_img_duke = findViewById(R.id.img_duke);
        m_tv_message = findViewById(R.id.tv_message);

        m_v_nba_logo = findViewById(R.id.v_nba_logo);
        m_tv_nba_team = findViewById(R.id.tv_nba_team);
        m_tv_nba_message = findViewById(R.id.tv_nba_message);
        m_btn_go = findViewById(R.id.btn_go);
    }

    private void initFrameAnimation() {
        m_img_duke.setBackgroundResource(R.drawable.frame_animation); //設定動畫資源
        m_animationDrawable = (AnimationDrawable) m_img_duke.getBackground(); //取得ImageView背景
    }

    private void initNbaLogos() {
        mNbaLogos = getResources().obtainTypedArray(R.array.nba_logos); //取得圖片背景
        mNbaLogosCount = mNbaLogos.length(); //取得陣列長度 (多少項目)
        m_v_nba_logo.setBackground(mNbaLogos.getDrawable(0)); //取得陣列第0項的圖，設定給view當背景

        mNbaTeams = getResources().getStringArray(R.array.nba_teams); //取得隊名資源
        m_tv_nba_team.setText(mNbaTeams[0]);  //取得陣列第0項的文字，設定隊名
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                m_animationDrawable.start();
                m_tv_message.setText("連續播放");
                break;
            case R.id.btn_stop:
                m_animationDrawable.stop();
                m_tv_message.setText("停止播放");
                break;
            case R.id.btn_5_secs:
                animation5secs();
                break;
        }
    }

    // Handler類別: 處理代辦事項
    private Handler m_handler = new Handler();

    private void animation5secs() {
        int delayMillis = 5*1000; //5秒
        //先建立結束動畫的工作
        Runnable task = new Task();
        //將工作(Runnable的Thread)交付給Handler，Handler會在系統安排好工作，並在你指定的時間點執行
        boolean result = m_handler.postDelayed(task, delayMillis);

        m_tv_message.setText((result ? "交付成功" : "交付失敗"));
        m_animationDrawable.start(); //開始動畫
    }

    private class Task implements Runnable{

        @Override
        public void run() {
            m_animationDrawable.stop(); //結束動畫
            m_tv_message.setText("時間到");
        }
    }

    //以下為NBA隨機換圖功能的部分　

    Runnable mStartRandomTask = new StartRandomTask(); //因為要讓StopRandomTask來中斷所以要寫在class scope
    Runnable mStopRandomTask = new StopRandomTask();
    Runnable mCountDown = new CountDown();
    private int countDownTime = 3000; //結束前倒數3秒

    public void go(View view) {

        if(mNbaBtnSw) {
            boolean resultStart = m_handler.post(mStartRandomTask); //立刻執行"隨機換圖任務"
            m_tv_nba_message.setText((resultStart ? "換圖任務交付成功" : "換圖任務交付失敗"));
            m_btn_go.setText("Stop");
        }else {
            m_handler.post(mCountDown); //立刻開始倒數
            m_btn_go.setText("CountDown");
            m_btn_go.setEnabled(false); //倒數中禁止按下，避免重複觸發
        }
        mNbaBtnSw = !mNbaBtnSw; //按鈕狀態切換

    }

    public class StartRandomTask implements Runnable{

        @Override
        public void run() {
            int idx = (int)(Math.random()*(mNbaLogosCount)); //Math.random()會產生 0~0.999999...之間的Double
            m_v_nba_logo.setBackground(mNbaLogos.getDrawable(idx));
            m_tv_nba_team.setText(mNbaTeams[idx]);
            m_handler.postDelayed(this, 50); //this是StartRandomTask物件自己
        }
    }

    public class CountDown implements Runnable{

        @Override
        public void run() {
            countDownTime -= 100;
            if(countDownTime > 0) {
                m_tv_nba_message.setText(String.format("%.2f", (float) countDownTime / 1000));
                m_handler.postDelayed(this, 100);
            }else {
                m_handler.post(mStopRandomTask); //跳出CountDown並執行"停止換圖任務"
            }
        }
    }

    public class StopRandomTask implements Runnable{

        @Override
        public void run() {
            m_handler.removeCallbacks(mStartRandomTask); //停止"隨機換圖任務"
            m_tv_nba_message.setText("時間到");
            m_btn_go.setText("Go");
            m_btn_go.setEnabled(true); //重新允許按下
            countDownTime = 3000; //重置倒數時間
        }
    }
}
