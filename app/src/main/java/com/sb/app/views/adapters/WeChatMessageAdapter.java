package com.sb.app.views.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.sb.app.AndroidApplication;
import com.sb.app.views.adapters.base.RecyclerViewAdapterBase;
import com.sb.app.views.adapters.base.ViewWrapper;
import com.sb.app.views.listeners.WeChatMessageClickListener;
import com.sb.app.views.viewgroup.ChatMeRedPacketItemView;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import javax.inject.Inject;

/**
 * Created by admin on 2017/7/23.
 */

public class WeChatMessageAdapter extends RecyclerViewAdapterBase<WebChatMessageRealm, ChatMeRedPacketItemView> {

    static Context mContext;



    @Inject
    public WeChatMessageAdapter(AndroidApplication context) {
        this.mContext = context;
    }





    @Override
    protected ChatMeRedPacketItemView onCreateItemView(ViewGroup parent, int viewType) {

        return ChatMeRedPacketItemView.build(mContext);
    }


    @Override
    public void onBindViewHolder(ViewWrapper<ChatMeRedPacketItemView> viewHolder, final int position) {
        ChatMeRedPacketItemView view = viewHolder.getView();
        //view.binder(items.get(position));


    }


    WeChatMessageClickListener<WebChatMessageRealm> mMessageClickListener;

    public void setMessageClickListener(WeChatMessageClickListener<WebChatMessageRealm> messageClickListener) {
        mMessageClickListener = messageClickListener;
    }
}
