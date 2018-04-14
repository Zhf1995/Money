package com.example.zhf.money_one;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.example.zhf.money_one.fragment.WebviewFragment;
import com.example.zhf.money_one.util.BaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //view
    private Button btnStartDemo;
    private View statusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseHelper.initImmersion(getWindow(),R.color.aliceblue);
        initView();
    }

    private void initView(){
        btnStartDemo = (Button)this.findViewById(R.id.btn_start_demo);
        btnStartDemo.setOnClickListener(this);
        statusBar = (View)this.findViewById(R.id.view_status_bar);
        initStatusBar(statusBar);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btn_start_demo:
                startDemo();
                break;
            default:
                break;
        }
    }

    private void startDemo(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        WebviewFragment fragment = new WebviewFragment();
        transaction.add(R.id.frame_fragment_container, fragment);
        transaction.commit();
    }

    private void initStatusBar(View statusBar){
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = BaseHelper.getStatusBarHeight(getBaseContext());
        statusBar.setLayoutParams(layoutParams);
    }
}
