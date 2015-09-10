package com.example.cj.innet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cj.innit.R;
import com.example.cj.innit.network.APIKey;
import com.example.cj.innit.network.NetworkRequest;
import com.example.cj.innit.pasing.HomeActListPasing;
import com.example.cj.innit.util.XLog;
import com.example.cj.model.HomeActListModel;
import com.google.gson.Gson;
import com.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{

    private ImageView ming_left,ming_head;
    private ImageView ming_home,ming_Collection,ming_post,ming_notice,ming_interest,ming_setting;
    private FrameLayout mlayout_home,mlayout_Collection,mlayout_post,mlayout_notice,mlayout_interest,mlayout_setting;
    private NetworkRequest request = new NetworkRequest(this);
    private Gson gson = new Gson();
    private List<HomeActListModel> data = new ArrayList<HomeActListModel>();
    private ListView mlistView;
    private MainAdapter adapter;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        preMenuWithView();
        initView();
        adapter = new MainAdapter(this,data);
        mlistView.setAdapter(adapter);
        request.obtainList(APIKey.KEY_OBTAIN_LIST, "0", "beijing", null, null, null);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(MainActivity.this,HomeDetailsAty.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        mlistView = (ListView) findViewById(R.id.main_lv);
    }

    private void preMenuWithView() {
        final SlidingMenu menu = new SlidingMenu(this);

        ming_left = (ImageView)findViewById(R.id.left);
        ming_left.setOnClickListener(new View.OnClickListener(){
                                         @Override
                                         public void onClick(View v){
                                             menu.toggle();
                                         }
                                     }
        );
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.slidingmenu_offset);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.shadow_width);
        menu.setBehindWidth(300);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.activity_main);
        initLeftView(menu);
    }

    private void initLeftView(SlidingMenu menu) {
        ming_home = (ImageView) menu.getMenu().findViewById(R.id.homebg);
        ming_head = (ImageView) menu.getMenu().findViewById(R.id.account_img);
        ming_Collection = (ImageView) menu.getMenu().findViewById(R.id.collectbg);
        ming_post = (ImageView) menu.getMenu().findViewById(R.id.postbg);
        ming_notice = (ImageView) menu.getMenu().findViewById(R.id.noticebg);
        ming_interest = (ImageView) menu.getMenu().findViewById(R.id.interestbg);
        ming_setting = (ImageView) menu.getMenu().findViewById(R.id.settingbg);
        mlayout_home = (FrameLayout) menu.getMenu().findViewById(R.id.layout_homebg);
        mlayout_Collection = (FrameLayout) menu.getMenu().findViewById(R.id.layout_collectbg);
        mlayout_post = (FrameLayout) menu.getMenu().findViewById(R.id.layout_postbg);
        mlayout_notice = (FrameLayout) menu.getMenu().findViewById(R.id.layout_noticebg);
        mlayout_interest = (FrameLayout) menu.getMenu().findViewById(R.id.layout_interestbg);
        mlayout_setting = (FrameLayout) menu.getMenu().findViewById(R.id.layout_settingbg);
        allclick();
    }



    @Override
    public void apiOnSuccess(int key, String strMsg) {
        super.apiOnSuccess(key, strMsg);
        switch (key) {
            case APIKey.KEY_OBTAIN_LIST:
                XLog.e("messi","获取的列表="+strMsg);
                HomeActListPasing homeActListPasing = gson.fromJson(strMsg, HomeActListPasing.class);
                if(homeActListPasing.getCode()==0){
                    data = homeActListPasing.getData();
                    adapter.setList(data);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void apiOnFailure(int key, String strMsg) {
        super.apiOnFailure(key, strMsg);
    }

    private void allclick() {
        ming_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,LoginWithRegistAty.class);
                startActivity(intent);
            }
        });
        mlayout_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = (AnimationSet) AnimationUtils.loadAnimation
                        (MainActivity.this, R.anim.imganimation);
                ming_home.setAnimation(animation);
                ming_home.setImageResource(R.mipmap.navbg_1);
                animation.start();
            }
        });
        mlayout_Collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = (AnimationSet) AnimationUtils.loadAnimation
                        (MainActivity.this, R.anim.imganimation);
                ming_Collection.setAnimation(animation);
                ming_Collection.setImageResource(R.mipmap.navbg_2);
                animation.start();
            }
        });
        mlayout_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = (AnimationSet) AnimationUtils.loadAnimation
                        (MainActivity.this, R.anim.imganimation);
                ming_post.setAnimation(animation);
                ming_post.setImageResource(R.mipmap.navbg_3);
                animation.start();
            }
        });
        mlayout_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = (AnimationSet) AnimationUtils.loadAnimation
                        (MainActivity.this, R.anim.imganimation);
                ming_notice.setAnimation(animation);
                ming_notice.setImageResource(R.mipmap.navbg_4);
                animation.start();
            }
        });
        mlayout_interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = (AnimationSet) AnimationUtils.loadAnimation
                        (MainActivity.this, R.anim.imganimation);
                ming_interest.setAnimation(animation);
                ming_interest.setImageResource(R.mipmap.navbg_5);
                animation.start();
            }
        });
        mlayout_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = (AnimationSet) AnimationUtils.loadAnimation
                        (MainActivity.this, R.anim.imganimation);
                ming_setting.setAnimation(animation);
                ming_setting.setImageResource(R.mipmap.navbg_6);
                animation.start();
            }
        });
    }
}