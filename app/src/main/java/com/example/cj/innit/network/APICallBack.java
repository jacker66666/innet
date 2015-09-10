package com.example.cj.innit.network;

/**
 * Created by Studio-CJ on 2015/9/9.
 */
public interface APICallBack {

    void apiOnFailure(int key, String strMsg);
    void apiOnSuccess(int key, String strMsg);
    void apiOnSuccess(int key, String strMsg,int rkey);
}
