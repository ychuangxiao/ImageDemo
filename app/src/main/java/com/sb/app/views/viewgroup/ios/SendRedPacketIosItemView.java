package com.sb.app.views.viewgroup.ios;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.sb.common.utils.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.listeners.MessageClickListener;
import com.sb.app.views.listeners.WeChatMessageLongClickListener;
import com.sb.app.views.viewgroup.HomeItemView;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

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
public class SendRedPacketIosItemView extends RelativeLayout {

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

    @BindView(R.id.tvSubDesc)
    AppCompatTextView tvSubDesc;

    private boolean alreadyInflated = false;

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static SendRedPacketIosItemView build(Context context) {
        SendRedPacketIosItemView instance = new SendRedPacketIosItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public SendRedPacketIosItemView(Context context) {
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


        if (webChatMessageRealm.getContactRealm().isSystem()) {
            mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[webChatMessageRealm.getContactRealm()
                    .getImageIndex()]);
        } else if (StringUtils.isNotEmpty(webChatMessageRealm.getContactRealm().getImgPath())) {
            // 加载本地图片
            File file = new File(webChatMessageRealm.getContactRealm().getImgPath());
            Glide.with(mContext).load(file).into(mHeaderImage);
        }

        mIvRed.setBackground(null);
        if (mChatMessageRealm.getAmountStatus() == AppConstant.RECEIVED_ACTION_Y) {

            tvRedPacketsDesc.setText(webChatMessageRealm.getMessage());
            tvSubDesc.setText("红包已被领完");
            mIvRed.setBackgroundResource(R.mipmap.ic_redpacket_we_chat);
            mRedPackedConstraintLayout.setBackgroundResource(R.drawable.ic_redpacket_right_default);
        } else {
            tvRedPacketsDesc.setText(webChatMessageRealm.getMessage());
            tvSubDesc.setText("领取红包");
            mIvRed.setBackgroundResource(R.mipmap.ic_red_packet);
            mRedPackedConstraintLayout.setBackgroundResource(R.drawable.ic_right_red_packet_default);
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
            inflate(getContext(), R.layout.row_send_redpacket_we_chat_ios, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


    MessageClickListener<WebChatMessageRealm,RelativeLayout> mMessageClickListener;

    public void setMessageClickListener(MessageClickListener<WebChatMessageRealm, RelativeLayout>
                                                messageClickListener) {
        mMessageClickListener = messageClickListener;
    }

    @OnClick(R.id.redPackedConstraintLayout)
    void onMessageClick()
    {
        if (mMessageClickListener != null)
        {
            mMessageClickListener.onMessageClickListener(mChatMessageRealm,this);
        }
    }

    @OnLongClick(R.id.redPackedConstraintLayout)
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
