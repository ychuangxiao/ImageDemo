package com.sb.app.views.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.AndroidApplication;
import com.sb.app.views.adapters.base.RecyclerViewAdapterBase;
import com.sb.app.views.adapters.base.ViewWrapper;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.app.views.viewgroup.ChatContactItemView;
import com.sb.data.entitys.realm.ContactRealm;

import javax.inject.Inject;

/**
 * 文件名称：{@link ChatContactAdapter}
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
public class ChatContactAdapter extends RecyclerViewAdapterBase<ContactRealm, ChatContactItemView> {


    private final String TAG = getClass().getName();
    Context mContext;


    @Inject
    public ChatContactAdapter(AndroidApplication context) {
        this.mContext = context;
    }

    @Override
    protected ChatContactItemView onCreateItemView(ViewGroup parent, int viewType) {

        return ChatContactItemView.build(mContext);
    }


    @Override
    public void onBindViewHolder(ViewWrapper<ChatContactItemView> viewHolder, final int position) {
        ChatContactItemView view = viewHolder.getView();
        view.binder(items.get(position));
        if (null != onChooseUserItemClickListener) {

            view.getChatRelativeLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChooseUserItemClickListener.onItemClickListener(items.get(position));
                }
            });

        }

    }


    RecyclerClickListener<ContactRealm> onChooseUserItemClickListener;


    public void setOnChooseUserItemClickListener(RecyclerClickListener<ContactRealm> onChooseUserItemClickListener) {
        this.onChooseUserItemClickListener = onChooseUserItemClickListener;
    }
}
