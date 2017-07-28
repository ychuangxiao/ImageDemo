package com.sb.app.views.viewgroup;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.utils.TimeUtils;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名称：{@link HomeItemView}
 * <br/>
 * 功能描述：朋友发消息
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/1 17:08
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/1 17:08
 * <br/>
 * 修改备注：
 */
public class ChatFriendMessageItemView extends RelativeLayout {


    @BindView(R.id.tvChatDateTime)
    AppCompatTextView mTvChatDateTime;
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;

    @BindView(R.id.topRedContent)
    AppCompatTextView mTopRedContent;

    private boolean alreadyInflated = false;

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static ChatFriendMessageItemView build(Context context) {
        ChatFriendMessageItemView instance = new ChatFriendMessageItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public ChatFriendMessageItemView(Context context) {
        super(context);
        mContext = context;
    }


    /**
     * 绑定消息
     */
    public void binder(WebChatMessageRealm webChatMessageRealm) {


        mTvChatDateTime.setText(TimeUtils.getLastDateTime(webChatMessageRealm.getSendTime(), System.currentTimeMillis(), true));


        if (StringUtils.isEmpty(mTvChatDateTime.getText().toString())) {
            mTvChatDateTime.setVisibility(View.GONE);
        } else {
            mTvChatDateTime.setVisibility(View.VISIBLE);
        }

        mTopRedContent.setText(Html.fromHtml(webChatMessageRealm.getMessage()));


    }


    /**
     * The malreadyInflated hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     */
    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.row_message_we_chat, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
