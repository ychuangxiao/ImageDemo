package com.sb.app.views.viewgroup;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名称：{@link ChatHomeItemView}
 * <br/>
 * 功能描述：微信视图
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
public class ChatHomeItemView extends RelativeLayout {


    @BindView(R.id.avatarImage)
    AppCompatImageView mAvatarImage;
    @BindView(R.id.groupRecycler)
    RecyclerView mGroupRecycler;
    @BindView(R.id.tvUnReadMessage)
    AppCompatTextView mTvUnReadMessage;
    @BindView(R.id.tvGroupName)
    AppCompatTextView mTvGroupName;
    @BindView(R.id.tvContent)
    AppCompatTextView mTvContent;
    @BindView(R.id.tvTime)
    AppCompatTextView mTvTime;
    @BindView(R.id.chatRelativeLayout)
    RelativeLayout mChatRelativeLayout;
    private boolean alreadyInflated = false;


    public RelativeLayout getChatRelativeLayout() {
        return mChatRelativeLayout;
    }

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static ChatHomeItemView build(Context context) {
        ChatHomeItemView instance = new ChatHomeItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public ChatHomeItemView(Context context) {
        super(context);
        mContext = context;
    }

    Drawable mDrawable;

    ContactRealm mContactRealm;

    /**
     * 绑定按钮标题
     *
     * @param groupRealm 分组模型
     */
    public void binder(ChatGroupRealm groupRealm) {

        mTvGroupName.setText(groupRealm.getGroupName());

        mTvGroupName.setTextColor(mContext.getResources().getColor(R.color.wechat_color_group_default));
        if (groupRealm.isPay()) {
            mTvGroupName.setTextColor(mContext.getResources().getColor(R.color.wechat_color_group_pay));

            mAvatarImage.setImageResource(R.mipmap.ic_we_chat_pay);
        } else {
            mContactRealm = groupRealm.getContactRealms().where().equalTo("isMe",false).findFirst();

            if (mContactRealm.isSystem()) {
                mAvatarImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                        .getImageIndex()]);
            }
            else if (StringUtils.isNotEmpty(mContactRealm.getImgPath())){
                // 加载本地图片
                File file = new File(mContactRealm.getImgPath());
                Glide.with(mContext).load(file).into(mAvatarImage);
            }

        }


        if (groupRealm.getLastTime() > 0) {
            mTvTime.setText(TimeUtils.getFirstDateTime(groupRealm.getLastTime(), System.currentTimeMillis()));
        } else {
            mTvTime.setText("");
        }

        mTvContent.setText((StringUtils.isNotEmpty(groupRealm.getLastMessage()) ? groupRealm.getLastMessage() : ""));
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
            inflate(getContext(), R.layout.item_home_we_chat, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
