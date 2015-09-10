package com.example.cj.innet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.cj.innit.R;

/**
 * Created by Studio-CJ on 2015/9/10.
 */
public class LoginWithRegistAty extends BaseActivity implements View.OnClickListener{

    private TextView mtv_login;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginwithregist);
        initView();
    }

    private void initView() {
        mtv_login = (TextView) findViewById(R.id.logintv);
        mtv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.logintv:
                intent = new Intent(this,LoginAty.class);
                startActivity(intent);
                break;
        }

    }
}
