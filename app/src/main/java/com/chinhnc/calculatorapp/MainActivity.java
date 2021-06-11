/*
*
* Code by Nguyen Cong Chinh
*
*/

package com.chinhnc.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import nguyenvanquan7826.com.Balan;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvInput, tvResult;

    private int[] idBtnArr = {
            R.id.btn0,
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot,
            R.id.btnPlus, R.id.btnSub, R.id.btnMul, R.id.btnDiv,
            R.id.btnOpen, R.id.btnClose,
            R.id.btnC, R.id.btnResult
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectView();
    }

    private void connectView() {
        tvInput = (TextView) findViewById(R.id.tvInput);
        tvResult = (TextView) findViewById(R.id.tvResult);

        for (int i = 0; i < idBtnArr.length; i++) {
//            Log.i("MainActivity", "Btn" + i + ": " + idBtnArr[i]); // test
            findViewById(idBtnArr[i]).setOnClickListener(this);
        }

        init();
    }

    private void init() {
        tvInput.setText("0");
        tvResult.setText("0");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        for (int i = 0; i < idBtnArr.length - 2; i++) {
            if (id == idBtnArr[i]) {
                String text = ((Button) findViewById(id)).getText().toString();

                if (tvInput.getText().toString().trim().equals("0")) {
                    tvInput.setText("");
                }

                tvInput.append(text);
                return;
            }
        }

        if (id == R.id.btnC) {
            init();
            return;
        }

        if (id == R.id.btnResult) {
            calculate();
        }
    }

    private void calculate() {
        String math = tvInput.getText().toString().trim();

        if (math.length() > 0) {
            Balan balan = new Balan();
            String result = balan.valueMath(math) + "";
            String error = balan.getError();

            if (error != null) {
                tvResult.setText(error);
            } else {
                tvResult.setText(result);
            }
        }
    }
}