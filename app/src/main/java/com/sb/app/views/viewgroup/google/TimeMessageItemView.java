package com.sb.app.views.viewgroup.google;


import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sb.app.R;
import com.sb.app.utils.TimeUtils;
import com.sb.app.views.listeners.WeChatMessageLongClickListener;
import com.sb.app.views.viewgroup.HomeItemView;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

/**
 * 文件名称：{@link HomeItemView}
 * <br/>
 * 功能描述：领取红包
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
public class TimeMessageItemView extends RelativeLayout {


    @BindView(R.id.tvChatDateTime)
    AppCompatTextView mTvChatDateTime;
    @BindView(R.id.tvMessage)
    AppCompatTextView mTvMessage;
    private boolean alreadyInflated = false;

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static TimeMessageItemView build(Context context) {
        TimeMessageItemView instance = new TimeMessageItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public TimeMessageItemView(Context context) {
        super(context);
        mContext = context;
    }


    WebChatMessageRealm mChatMessageRealm;

    /**
     * 绑定消息
     */
    public void binder(WebChatMessageRealm webChatMessageRealm, Long lastSendTime, boolean isFirst) {

        mChatMessageRealm = webChatMessageRealm;
        mTvMessage.setVisibility(View.GONE);

        mTvChatDateTime.setText(TimeUtils.getFirstDateTime(webChatMessageRealm.getSendTime(), System
                .currentTimeMillis()));
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
            inflate(getContext(), R.layout.row_center_message, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


    @OnLongClick(R.id.messageContainer)
            boolean onMessageLongClick()
    {
        if(mMessageLongClickListener != null)
        {
            mMessageLongClickListener.onItemLongClickListener(this.mChatMessageRealm,this);
        }

        return  true;
    }

    WeChatMessageLongClickListener<WebChatMessageRealm,RelativeLayout> mMessageLongClickListener;

    public void setMessageLongClickListener(WeChatMessageLongClickListener<WebChatMessageRealm, RelativeLayout>
                                                    messageLongClickListener) {
        mMessageLongClickListener = messageLongClickListener;
    }
}
