package com.example.zhf.money_one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhf.money_one.util.BaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseHelper.initImmersion(getWindow());
    }
}
