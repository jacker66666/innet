package com.example.cj.innet;

import android.os.Bundle;

import com.example.cj.innit.R;
import com.example.cj.innit.network.NetworkRequest;

/**
 * Created by Studio-CJ on 2015/9/10.
 */
public class ProfileAty extends BaseActivity{

    private NetworkRequest request = new NetworkRequest(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }
}
