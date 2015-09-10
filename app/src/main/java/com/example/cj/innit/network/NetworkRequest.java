package com.example.cj.innit.network;

import android.text.TextUtils;

import com.example.cj.innit.util.Constants;
import com.example.cj.innit.util.Utils;
import com.example.cj.innit.util.XLog;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.apache.http.HttpResponse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author iuxstudio-as
 */
public class NetworkRequest{

	private APICallBack apiCallBack;// 回调
	public long getTime() {
		return System.currentTimeMillis();
	}
	private String app_id = "app_id";
	private String nonce = "nonce";
	private String timestamp = "timestamp";
	private String sign = "sign";

	public NetworkRequest(APICallBack apiCallBack) {
		this.apiCallBack = apiCallBack;
	}
	/**
	 * @param apiUrl
	 *            请求方法
	 * @param apiKey
	 *            请求标识
	 * @param gop
	 *            请求方式
	 */
	public void apiCall(String apiUrl, AjaxParams params, final int apiKey,
			int gop) {
		FinalHttp fh = new FinalHttp();
		fh.configTimeout(10000);
		String url = Constants.BASE_HTTP_PATH + apiUrl;
		AjaxCallBack<String> ajaxCallBack = new AjaxCallBack<String>() {
			@Override
			public void onFailure(Throwable t, String strMsg) {
				if (null != apiCallBack) {
					apiCallBack.apiOnFailure(apiKey, strMsg);
				}
			}
			@Override
			public void onSuccess(String str) {
				if (null != apiCallBack) {
					apiCallBack.apiOnSuccess(apiKey, str);
				}
			}

			@Override
			public void onSuccess(String t, HttpResponse response) {
				super.onSuccess(t, response);
			}
		};
		switch (gop) {
		case Constants.API_GET:
			if (params != null){
				url = url + "&" + params.toString();
				XLog.e("messi", url);
				fh.get(url, ajaxCallBack);
			} else {
				fh.get(url, ajaxCallBack);
			}
			break;
		case Constants.API_POST:
			if(params != null){
				XLog.e("messi", "params.toString()====" + params.toString());
				XLog.e("messi", url + "?" + params.toString());
				fh.post(url, params, ajaxCallBack);
			} else {
				fh.post(url, ajaxCallBack);
			}
			break;
		}
	}

	/**
	 * 创建账户
	 * @param Username
	 * @param Password
	 */
	public void register(int key,String Username,String Password){
		String url = "/nggirl/app/cli/personalInfo/logout";
		AjaxParams params = new AjaxParams();
		Map<String, String> param4Sign = new HashMap<String, String>();
		params.put(app_id, Constants.APP_ID);
		param4Sign.put(app_id, Constants.APP_ID);
		params.put(nonce, Constants.NONCE);
		param4Sign.put(nonce, Constants.NONCE);
		params.put("Username", Username);
		param4Sign.put("Username", Username);
		String timeStamp = Long.toString(getTime() / 1000);
		params.put(timeStamp, getTime()+"");
		param4Sign.put(timeStamp, getTime() + "");
		params.put(sign,calSign(param4Sign));
		apiCall(url, params, key, Constants.API_POST);
	}

	/**
	 * 获取活动列表
	 */
	public void obtainList(int key,String Type,String CityName,String Keyword,String Page,String Num){
		String url = "?r=activity/getList";
		AjaxParams params = new AjaxParams();
		Map<String, String> param4Sign = new HashMap<String, String>();
		params.put(app_id, Constants.APP_ID);
		param4Sign.put(app_id, Constants.APP_ID);
		params.put(nonce, Constants.NONCE);
		param4Sign.put(nonce, Constants.NONCE);
		params.put("Type", Type);
		param4Sign.put("Type", Type);
		params.put("CityName", CityName);
		param4Sign.put("CityName", CityName);
		params.put("language", "zh");
		param4Sign.put("language", "zh");
		if(!TextUtils.isEmpty(Keyword)){
			params.put("Keyword", Keyword);
			param4Sign.put("Keyword", Keyword);
		}
		if(!TextUtils.isEmpty(Page)){
			params.put("Page", Page);
			param4Sign.put("Page", Page);
		}
		if(!TextUtils.isEmpty(Num)){
			params.put("Num", Num);
			param4Sign.put("Num", Num);
		}
		String timeStamp = Long.toString(getTime() / 1000);
		params.put(timestamp, timeStamp);
		param4Sign.put(timestamp, timeStamp);
		params.put(sign,calSign(param4Sign));
		apiCall(url, params, key, Constants.API_GET);


	}

	/**
	 * 1. 将请求的所有参数（包括系统参数和应用参数，不包含sign和资源类型文件）依照key name做快速排序 2.
	 * 将排序后的参数使用&符号，依照key=value的形式连接成一个字符串，并在字符串尾拼接app secret 3. 计算拼接后字符串的MD5值
	 *
	 * @param params
	 *            依据参数计算得到的md5值
	 * @return 加签值，如果params为空或者没有元素返回值为""
	 */
	private String calSign(Map<String, String> params) {
		if (params == null) {
			return "";
		}
		String[] keyArray = params.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);
		StringBuilder sb = new StringBuilder();
		for (String key : keyArray) {
			if (!sign.equalsIgnoreCase(key)) {
				sb.append(key);
				sb.append("=");
				sb.append(params.get(key));
				sb.append("&");
			}
		}
		String paramString = sb.toString();
		if (paramString.length() != 0) {
			paramString += Constants.SECRET;
			return Utils.encryption(paramString);
		} else {
			return "";
		}
	}
}