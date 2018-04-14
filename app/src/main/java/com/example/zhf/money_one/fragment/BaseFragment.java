package com.example.zhf.money_one.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Zhf on 2018/4/14.
 */

public abstract class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 在宿主Activity中被移除View时候调用
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * Fragment整体被销毁时调用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
