package com.sb.app.views.viewgroup.google;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.listeners.MessageClickListener;
import com.sb.app.views.listeners.WeChatMessageLongClickListener;
import com.sb.app.views.viewgroup.HomeItemView;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.io.File;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * 文件名称：{@link HomeItemView}
 * <br/>
 * 功能描述：朋友转账
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
public class SendTransferItemView extends RelativeLayout {


    @BindView(R.id.tvChatDateTime)
    AppCompatTextView mTvChatDateTime;
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.ivRed)
    AppCompatImageView mIvRed;


    @BindView(R.id.tvTransferExplain)
    AppCompatTextView mTvTransferExplain;
    @BindView(R.id.textTransferAmount)
    AppCompatTextView mTextTransferAmount;
    private boolean alreadyInflated = false;

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static SendTransferItemView build(Context context) {
        SendTransferItemView instance = new SendTransferItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public SendTransferItemView(Context context) {
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

        if (webChatMessageRealm.getAmountStatus() != null &&  webChatMessageRealm.getAmountStatus() == AppConstant.RECEIVED_ACTION_Y) {

            mIvRed.setImageResource(R.mipmap.ic_transfer_received_we_chat);

        } else {
            mIvRed.setImageResource(R.mipmap.ic_transfer_we_chat);
        }

        mTvTransferExplain.setText(webChatMessageRealm.getMessage());


        mTextTransferAmount.setText(String.format("￥%s", MathUtils.toString(new BigDecimal
                (webChatMessageRealm.getAmount()))));



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
            inflate(getContext(), R.layout.row_send_transfer_we_chat, this);
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
