package com.sb.app.views.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.AndroidApplication;
import com.sb.app.model.BankModel;
import com.sb.app.views.adapters.base.RecyclerViewAdapterBase;
import com.sb.app.views.adapters.base.ViewWrapper;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.app.views.viewgroup.ChatHomeItemView;
import com.sb.app.views.viewgroup.HomeItemView;
import com.sb.data.entitys.realm.ChatGroupRealm;

import javax.inject.Inject;

/**
 * 文件名称：{@link WeChatHomeAdapter}
 * <br/>
 * 功能描述：微信首页适配器
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/1 17:04
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/1 17:04
 * <br/>
 * 修改备注：
 */
public class WeChatHomeAdapter extends RecyclerViewAdapterBase<ChatGroupRealm, ChatHomeItemView> {


    private final String TAG = getClass().getName();
    Context mContext;


    @Inject
    public WeChatHomeAdapter(AndroidApplication context) {
        this.mContext = context;
    }

    @Override
    protected ChatHomeItemView onCreateItemView(ViewGroup parent, int viewType) {

        return ChatHomeItemView.build(mContext);
    }


    @Override
    public void onBindViewHolder(ViewWrapper<ChatHomeItemView> viewHolder, final int position) {
        ChatHomeItemView view = viewHolder.getView();
        view.binder(items.get(position));
        if (null != mRecyclerClickListener) {

            view.getChatRelativeLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerClickListener.onItemClickListener(items.get(position));
                }
            });

        }

    }


    RecyclerClickListener<ChatGroupRealm> mRecyclerClickListener;

    public void setRecyclerClickListener(RecyclerClickListener<ChatGroupRealm> recyclerClickListener) {
        this.mRecyclerClickListener = recyclerClickListener;
    }
}
