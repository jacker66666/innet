package com.example.cj.innet;

import android.content.Intent;
import android.os.Bundle;
import com.example.cj.innit.R;

/**
 * 加载页面
 * Created by Studio-CJ on 2015/9/9.
 */
public class LoadAty extends BaseActivity{

    private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Thread.sleep(3000);
                    intent = new Intent(LoadAty.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}