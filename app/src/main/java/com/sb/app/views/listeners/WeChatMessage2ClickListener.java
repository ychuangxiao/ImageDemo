package com.sb.app.views.listeners;

import android.widget.RelativeLayout;

/**
 * 文件名称：{@link WeChatMessage2ClickListener}
 * <br/>
 * 功能描述：聊天单机事件
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：11/1/16 10:36
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：11/1/16 10:36
 * <br/>
 * 修改备注：
 */
public interface WeChatMessage2ClickListener<T, V extends RelativeLayout> {

    void onItemClickListener(T model, V v);
}
