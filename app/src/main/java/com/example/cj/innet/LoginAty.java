package com.example.cj.innet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cj.innit.R;

/**
 * 登陆界面
 * Created by Studio-CJ on 2015/9/10.
 */
public class LoginAty extends BaseActivity implements View.OnClickListener{

    private TextView mtv_signup;
    private Intent intent;
    private ImageView ming_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
    }

    private void initView() {
        mtv_signup = (TextView) findViewById(R.id.signup);
        ming_back = (ImageView) findViewById(R.id.loginback);
        mtv_signup.setOnClickListener(this);
        ming_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup:
                intent = new Intent(this,RegisterAty.class);
                startActivity(intent);
                break;
            case R.id.loginback:
                finish();
                break;
        }
    }
}
