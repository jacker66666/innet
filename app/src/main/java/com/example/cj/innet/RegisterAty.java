package com.example.cj.innet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.cj.innit.R;
import com.example.cj.innit.network.APIKey;
import com.example.cj.innit.network.NetworkRequest;
import com.example.cj.innit.util.XLog;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 注册界面
 * Created by Studio-CJ on 2015/9/10.
 */
public class RegisterAty extends BaseActivity implements View.OnClickListener{

    private EditText medt_email,medt_password,medt_repeatpsd;
    private Button mbtn_submit;
    private ImageView ming_back;
    private NetworkRequest request = new NetworkRequest(this);
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered);
        initView();
    }

    private void initView() {
        ming_back = (ImageView) findViewById(R.id.register_back);
        mbtn_submit = (Button) findViewById(R.id.register_btn);
        medt_email = (EditText) findViewById(R.id.register_email);
        medt_password = (EditText) findViewById(R.id.register_pwd);
        medt_repeatpsd = (EditText) findViewById(R.id.register_repwd);
        ming_back.setOnClickListener(this);
        mbtn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.register_btn:
                String email = medt_email.getText().toString();
                String password = medt_password.getText().toString();
                String repeatword = medt_repeatpsd.getText().toString();
                if(TextUtils.isEmpty(email)){
                    showshortToast("email null!!");
                    return;
                }else if(TextUtils.isEmpty(password)){
                    showshortToast("password null!!");
                    return;
                }else if(TextUtils.isEmpty(repeatword)){
                    showshortToast("password null!!");
                    return;
                }else if(!password.equals(repeatword)){
                    showshortToast("Two times the password is not the same!!");
                    return;
                }else{
                    request.register(APIKey.KEY_SIGNUP,email,password);
                }

                break;
        }
    }

    @Override
    public void apiOnFailure(int key, String strMsg) {
        super.apiOnFailure(key, strMsg);
    }

    @Override
    public void apiOnSuccess(int key, String strMsg) {
        super.apiOnSuccess(key, strMsg);
        switch (key){
            case APIKey.KEY_SIGNUP:
                XLog.e("messi","创建一个新账号="+strMsg);
                try {
                    JSONObject object = new JSONObject(strMsg);
                    String code = object.getString("code");
                    if(code.equals("0")){
                       intent = new Intent(RegisterAty.this,ProfileAty.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
