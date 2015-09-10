package com.example.cj.innet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cj.innit.network.APICallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.slidingmenu.lib.app.SlidingActivity;
import com.slidingmenu.lib.app.SlidingListActivity;

/**
 * Created by Studio-CJ on 2015/9/8.
 */
public class BaseActivity extends Activity implements APICallBack{

    public void showshortToast(String str){
        Toast.makeText(BaseActivity.this,str,Toast.LENGTH_SHORT).show();
    }
    public ImageLoader imageloder = ImageLoader.getInstance();

    @Override
    public void apiOnFailure(int key, String strMsg) {

    }

    @Override
    public void apiOnSuccess(int key, String strMsg) {

    }

    @Override
    public void apiOnSuccess(int key, String strMsg, int rkey) {

    }
}
