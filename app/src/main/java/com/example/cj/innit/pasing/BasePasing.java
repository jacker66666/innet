package com.example.cj.innit.pasing;

import android.text.TextUtils;

/**
 * Created by Studio-CJ on 2015/9/9.
 */
public class BasePasing {

    /**
     * 所有解析类都需要继承此类获取code
     * @author Cool
     */
    private String code;
    private String extra;

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getCode() {
        if (!TextUtils.isEmpty(code)) {
            return Integer.parseInt(code);
        }else{
            return -1;
        }
    }

    public void setCode(String code) {
        this.code = code;
    }
}
