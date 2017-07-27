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
    public static ChatMeRedPacketItemView build(Context context) {
        ChatMeRedPacketItemView instance = new ChatMeRedPacketItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public ChatMeRedPacketItemView(Context context) {
        super(context);
        mContext = context;
    }


    /**
     * 绑定消息
     */
    public void binder(WebChatMessageRealm webChatMessageRealm) {
        mTvChatDateTime.setText(TimeUtils.getLastDateTime(webChatMessageRealm.getSendTime(), System.currentTimeMillis(), false));


        if (StringUtils.isEmpty(mTvChatDateTime.getText().toString())) {
            mTvChatDateTime.setVisibility(View.GONE);
        } else {
            mTvChatDateTime.setVisibility(View.VISIBLE);
        }

        mTopRedContent.setText(webChatMessageRealm.getMessage());


        if (webChatMessageRealm.getAmountStatus() == AppConstant.RECEIVED_ACTION_Y) {
            mRedPackedConstraintLayout.setBackgroundResource(R.drawable.ic_redpacket_right_default);


        } else {
            mRedPackedConstraintLayout.setBackgroundResource(R.drawable.ic_right_red_packet_default);

        }


        mRedPackedConstraintLayout.setPadding(mRedPackedConstraintLayout.getPaddingLeft()
                , 4
                , mRedPackedConstraintLayout.getPaddingRight()
                , mRedPackedConstraintLayout.getPaddingBottom());
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


}
