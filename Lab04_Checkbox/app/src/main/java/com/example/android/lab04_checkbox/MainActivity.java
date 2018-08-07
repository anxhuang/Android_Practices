package com.example.android.lab04_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox m_cb_toppings;
    private TextView m_tv_qty;
    private TextView m_tv_total;
    private int m_int_qty = 0;
    private int m_int_eachPrice = 100;
    private StringBuilder sb = new StringBuilder(); //放在class避免一直不斷產生物件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        m_cb_toppings = findViewById(R.id.cb_toppings);
        m_tv_qty = findViewById(R.id.tv_qty);
        m_tv_total = findViewById(R.id.tv_total);
    }

    private void clearMsg(){
        sb.delete(0, sb.length());
        m_tv_total.setText(sb);
    }

    public void onButtonDown(View view) {
        m_int_qty = Integer.parseInt(m_tv_qty.getText().toString());

        //因為是重複用同一個物件 一定要先歸零
        clearMsg();

        switch (view.getId()) {
            case R.id.cb_toppings:
                System.out.println(m_cb_toppings.isChecked() ? "有勾" : "沒有勾");
                break;
            case R.id.btn_increase:
                m_int_qty++;
                break;
            case R.id.btn_decrease:
                m_int_qty = Math.max(0, --m_int_qty);
                break;
            case R.id.btn_order:
                sb  .append("Name: 鳴人")
                    .append("\n臭豆腐")
                    .append("\n加泡菜？ ")
                    .append(m_cb_toppings.isChecked());
                if (m_int_qty != 0) {
                    sb  .append("\nQuantity: ")
                        .append(m_int_qty)
                        .append("\nTotal NT$ ")
                        .append(String.valueOf(m_int_qty * m_int_eachPrice))
                        .append("\nThank you!");
                }else{
                    sb.append("\nFree!");
                }
                m_tv_total.setText(sb);
                break;
        }
        m_tv_qty.setText(String.valueOf(m_int_qty));
    }
}
