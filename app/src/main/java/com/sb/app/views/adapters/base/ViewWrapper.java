package com.sb.app.views.adapters.base;


import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 文件名称：ViewWrapper
 * <br/>
 * 功能描述：RecyclerView包装
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/1 16:52
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/1 16:52
 * <br/>
 * 修改备注：
 *
 *
 */ 
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}
