package com.sb.app.views.viewgroup;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.utils.TimeUtils;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名称：{@link HomeItemView}
 * <br/>
 * 功能描述：朋友发的红包
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
public class ChatFriendRedPacketItemView extends RelativeLayout {


    @BindView(R.id.tvChatDateTime)
    AppCompatTextView mTvChatDateTime;
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.ivRed)
    AppCompatImageView mIvRed;
    @BindView(R.id.topRedContent)
    AppCompatTextView mTopRedContent;
    @BindView(R.id.tvRedDetails)
    AppCompatTextView mTvRedDetails;
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
    public static ChatFriendRedPacketItemView build(Context context) {
        ChatFriendRedPacketItemView instance = new ChatFriendRedPacketItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public ChatFriendRedPacketItemView(Context context) {
        super(context);
        mContext = context;
    }


    /**
     * 绑定消息
     */
    public Long binder(WebChatMessageRealm webChatMessageRealm,Long lastSendTime,boolean isFirst) {


        if (isFirst) {

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
        }


        mTopRedContent.setText(webChatMessageRealm.getMessage());


        if (webChatMessageRealm.getAmountStatus() == AppConstant.RECEIVED_ACTION_Y) {
            mRedPackedConstraintLayout.setBackgroundResource(R.drawable.ic_redpacket_left_default);


        } else {
            mRedPackedConstraintLayout.setBackgroundResource(R.drawable.ic_left_red_packet_default);

        }
        mRedPackedConstraintLayout.setPadding(mRedPackedConstraintLayout.getPaddingLeft()
                , 4
                , mRedPackedConstraintLayout.getPaddingRight()
                , mRedPackedConstraintLayout.getPaddingBottom());


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
            inflate(getContext(), R.layout.row_redpacket_we_chat, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
