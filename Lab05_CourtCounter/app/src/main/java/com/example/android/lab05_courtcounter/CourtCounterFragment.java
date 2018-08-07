package com.example.android.lab05_courtcounter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourtCounterFragment extends Fragment {

    private TextView m_tv_team;
    private TextView m_tv_score;

    public TextView getM_tv_score(){
        return m_tv_score;
    }

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

    }


}
