package com.example.android.lab02_interactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView m_tv_qty;
    TextView m_tv_total;
    int m_int_qty = 0;
    int m_int_eachPrice = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_tv_qty = findViewById(R.id.tv_qty);
        m_tv_total = findViewById(R.id.tv_total);
    }

    public void onButtonDown(View view) {
        m_int_qty = Integer.parseInt(m_tv_qty.getText().toString());
        switch (view.getId()) {
            case R.id.btn_increase:
                m_int_qty++;
                break;
            case R.id.btn_decrease:
                m_int_qty = Math.max(0, --m_int_qty);
                break;
            case R.id.btn_order:
                String msg = (m_int_qty == 0 ? "\nFree!" : "\nThank you!");
                m_tv_total.setText("NT$ " + String.valueOf(m_int_qty * m_int_eachPrice) + msg);
                break;
        }
        m_tv_qty.setText(String.valueOf(m_int_qty));
    }
}
