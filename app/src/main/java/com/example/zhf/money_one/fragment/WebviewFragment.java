package com.example.zhf.money_one.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.zhf.money_one.R;

/**
 * Created by Zhf on 2018/4/14.
 */

public class WebviewFragment extends BaseFragment implements View.OnClickListener{

    //sign
    private final int TIMER_TASK_START = 1;
    private final int TIMER_TASK_STOP = 2;
    private final int TIMER_TASK_RESTART = 3;
    private final String TAG = "WebviewFragment";

    //data
    private String url;
    private String testUrl = "https://m.bilibili.com/video/ac21844673.html";

    //view
    private WebView wbMain;
    private TextView tvWebUrl;
    private AppCompatButton btnReload;
    private WebSettings webSettings;

    //class
    private MyThread myThread;
    private MyHandler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new MyHandler();
    }

    private void netInit() {
        //请求服务器指令
        String url = testUrl;
        refreshUrl(url);
    }

    private void refreshUrl(String url){
        tvWebUrl.setText(url);
        wbMain.loadUrl(url);
        wbMain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url == null) return false;

                try {
                    if (url.startsWith("http:") || url.startsWith("https:"))
                    {
                        view.loadUrl(url);
                        return true;
                    }
                    else
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_fragment,container,false);
        wbMain = view.findViewById(R.id.web_view);
        tvWebUrl = view.findViewById(R.id.tv_weburl);
        btnReload = view.findViewById(R.id.btn_reload);
        btnReload.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initWebview();
        startService();
    }

    private void startService() {
        netInit();
    }

    private void initWebview(){
        webSettings = wbMain.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reload:
                refreshUrl(testUrl);
                new Thread(new MyThread()).start();
            break;
        }
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIMER_TASK_START:
                    Log.d(TAG,"start timerTask");
                    break;
                case TIMER_TASK_STOP:
                    Log.d(TAG,"stop timerTask");
                    new Thread(new MyThread()).start();
                    break;
            }
        }
    }


    /**
     * 计时线程
     */
    public class MyThread implements Runnable {      // thread
        @Override
        public void run() {
            while (true) {
                try {
                    sendMyhandlerMess(TIMER_TASK_START);
                    Thread.sleep(1000);     // sleep 1000ms
                    sendMyhandlerMess(TIMER_TASK_STOP);
                } catch (Exception e) {
                }
            }

        }
    }

    /**
     * 向消息队列发送信息
     * @param Type
     */
    private void sendMyhandlerMess(int Type){
        Message message = new Message();
        message.what = Type;
        handler.sendMessage(message);
    }

}
