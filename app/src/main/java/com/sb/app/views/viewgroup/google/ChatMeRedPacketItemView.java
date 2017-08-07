package com.sb.app.views.viewgroup.google;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.listeners.WeChatMessageLongClickListener;
import com.sb.app.views.viewgroup.HomeItemView;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文件名称：{@link HomeItemView}
 * <br/>
 * 功能描述：我发的转账
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
public class ChatMeRedPacketItemView extends RelativeLayout {

    @BindView(R.id.tvChatDateTime)
    AppCompatTextView mTvChatDateTime;
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.ivRed)
    AppCompatImageView mIvRed;
    @BindView(R.id.tvRedPacketsDesc)
    AppCompatTextView tvRedPacketsDesc;
    @BindView(R.id.redPackedConstraintLayout)
    ConstraintLayout mRedPackedConstraintLayout;

    private boolean alreadyInflated = false;

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static ChatMeRedPacketItemView build(Context context) {
        ChatMeRedPacketItemView instance = new ChatMeRedPacketItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public ChatMeRedPacketItemView(Context context) {
        super(context);
        mContext = context;
    }


    WebChatMessageRealm mChatMessageRealm;

    /**
     * 绑定消息
     */
    public long binder(WebChatMessageRealm webChatMessageRealm, Long lastSendTime, boolean isFirst) {

        mChatMessageRealm = webChatMessageRealm;
        mTvChatDateTime.setVisibility(View.GONE);
        /*if (isFirst) {

            if (lastSendTime < 1L) {
                mTvChatDateTime.setText(TimeUtils.millis2String(webChatMessageRealm.getSendTime(), TimeUtils
                        .DEFAULT_PATTERN_4));

            } else {
                mTvChatDateTime.setText(TimeUtils.getFirstDateTime(webChatMessageRealm.getSendTime(), System
                        .currentTimeMillis()));
            }

        } else {

            mTvChatDateTime.setText(TimeUtils.getLastDateTime(lastSendTime, webChatMessageRealm.getSendTime()));

        }


        if (StringUtils.isEmpty(mTvChatDateTime.getText().toString())) {
            mTvChatDateTime.setVisibility(View.GONE);
        } else {
            mTvChatDateTime.setVisibility(View.VISIBLE);

            lastSendTime = webChatMessageRealm.getSendTime();
        }*/

        tvRedPacketsDesc.setText(webChatMessageRealm.getMessage());

        if (webChatMessageRealm.getContactRealm().isSystem()) {
            mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[webChatMessageRealm.getContactRealm()
                    .getImageIndex()]);
        }
        else if (StringUtils.isNotEmpty(webChatMessageRealm.getContactRealm().getImgPath())){
            // 加载本地图片
            File file = new File(webChatMessageRealm.getContactRealm().getImgPath());
            Glide.with(mContext).load(file).into(mHeaderImage);
        }

        return lastSendTime;
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
            inflate(getContext(), R.layout.row_redpacket_right_we_chat, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


    WeChatMessageLongClickListener<WebChatMessageRealm,RelativeLayout> mMessageClickListener;

    public void setMessageClickListener(WeChatMessageLongClickListener<WebChatMessageRealm, RelativeLayout>
                                                messageClickListener) {
        mMessageClickListener = messageClickListener;
    }

    @OnClick(R.id.redPackedConstraintLayout)
    void onMessageClick()
    {
        if (mMessageClickListener != null)
        {
            mMessageClickListener.onItemClickListener(mChatMessageRealm,this);
        }
    }
}
