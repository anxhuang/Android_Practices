package com.example.android.lab05_courtcounter;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourtCounterFragment extends Fragment implements View.OnClickListener{ //要實作View.onClickListener這個介面

    private TextView m_tv_team;
    private TextView m_tv_score;
    private ImageView m_iv_team;
    private Button m_btn_triple;
    private Button m_btn_double;
    private Button m_btn_penalty;
    private int m_int_score;

    public CourtCounterFragment() {
        // Required empty public constructor (無參數建構是必須的，不可刪除)
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment (產生 Fragment 回應給 Activity)
        return inflater.inflate(R.layout.fragment_court_counter, container, false);
    }

    @Override
    public void onStart() { //onStart是在onCreateView後才執行 因為找view要等Fragment畫面建立才找得到所以寫在這裡
        super.onStart();

        m_tv_team   =   getView().findViewById(R.id.tv_team); //在fragment裡面找view要用getView()
        m_tv_score  =   getView().findViewById(R.id.tv_score);
        m_iv_team   =   getView().findViewById(R.id.iv_team);

        //找到button的view
        m_btn_triple    =   getView().findViewById(R.id.btn_triple);
        m_btn_double    =   getView().findViewById(R.id.btn_double);
        m_btn_penalty   =   getView().findViewById(R.id.btn_penalty);
        //view.setOnClickListener(this); this就是這個fragment class
        m_btn_triple.setOnClickListener(this);
        m_btn_double.setOnClickListener(this);
        m_btn_penalty.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) { //View.onClickListener這個介面交代要實作的方法
        switch (view.getId()){
            case R.id.btn_triple:
                m_int_score+=3;
                break;
            case R.id.btn_double:
                m_int_score+=2;
                break;
            case R.id.btn_penalty:
                m_int_score+=1;
                break;
        }
        m_tv_score.setText(String.valueOf(m_int_score));
    }

    //歸零專用方法
    public void resetScore(){
        m_int_score = 0;
        m_tv_score.setText(String.valueOf(m_int_score));
    }

    //設定隊伍名稱
    public void setTeamName(String teamName) {
        m_tv_team.setText(teamName);
    }

    //設定隊伍圖片 要排除橫擺時m_iv_team為null的情況
    public void setTeamLogo(int imageResId){
        if (m_iv_team != null) {
            m_iv_team.setImageResource(imageResId);
        }
    }
}
