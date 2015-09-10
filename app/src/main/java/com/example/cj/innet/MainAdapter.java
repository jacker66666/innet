package com.example.cj.innet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.cj.innit.R;
import com.example.cj.innit.util.XLog;
import com.example.cj.model.HomeActListModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * 主页
 * Created by Studio-CJ on 2015/9/9.
 */
public class MainAdapter extends BaseAdapter{

    private Context mContext;
    private List<HomeActListModel> list;
    private DisplayImageOptions options;
    private ImageLoader imageloder ;

    public List<HomeActListModel> getList() {
        return list;
    }

    public void setList(List<HomeActListModel> list) {
        this.list = list;
    }

    public MainAdapter(Context context, List<HomeActListModel> data) {
        mContext = context;
        list = data;
        imageloder = ImageLoader.getInstance();
        configurePic();
    }

    private void configurePic(){
        //配置图片加载及显示选项（还有一些其他的配置，查阅doc文档吧）
        options = new DisplayImageOptions.Builder()
                //.showStubImage(R.drawable.info_head_default)    //在ImageView加载过程中显示图片
                //.showImageForEmptyUri(R.drawable.info_head_default)  //image连接地址为空时
                //.showImageOnFail(R.drawable.info_head_default)  //image加载失败
                .cacheInMemory(true)//加载图片时会在内存中加载缓存
                .cacheOnDisc(true)//加载图片时会在磁盘中加载缓存
                // .displayer(new RoundedBitmapDisplayer(200))//设置用户加载图片task(这里是圆角图片显示)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.mainlist_item,null);
            holder.ming = (ImageView) convertView.findViewById(R.id.mainitem_iv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        imageloder.displayImage(list.get(position).getBackground(), holder.ming, options);
        return convertView;
    }

    static class ViewHolder{
        ImageView ming;
    }
}