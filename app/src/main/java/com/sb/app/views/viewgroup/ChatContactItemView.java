package com.sb.app.views.viewgroup;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.sb.common.utils.StringUtils;
import com.sb.app.R;
import com.sb.app.utils.ViewUtils;
import com.sb.common.fontawesom.IconicsDrawable;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.entitys.realm.ContactRealm;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名称：{@link ChatContactItemView}
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
public class ChatContactItemView extends RelativeLayout {


    @BindView(R.id.avatarImage)
    AppCompatImageView mAvatarImage;
    @BindView(R.id.chatRelativeLayout)
    RelativeLayout mChatRelativeLayout;
    @BindView(R.id.tvUserNick)
    AppCompatTextView mTvUserNick;
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
    public static ChatContactItemView build(Context context) {
        ChatContactItemView instance = new ChatContactItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public ChatContactItemView(Context context) {
        super(context);
        mContext = context;
    }

    Drawable mDrawable;

    ContactRealm mContactRealm;
    Drawable drawableRight = null;
    /**
     * 绑定按钮标题
     */
    public void binder(ContactRealm contactRealm,String defaultUserId) {

        mContactRealm = contactRealm;
        mTvUserNick.setText(contactRealm.getUserNick());


        if (contactRealm.isSystem()) {

            mAvatarImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        }
        else if (StringUtils.isNotEmpty(contactRealm.getImgPath())){
            // 加载本地图片
            File file = new File(contactRealm.getImgPath());
            Glide.with(mContext).load(file).into(mAvatarImage);
        }
        drawableRight = null;
        if (mContactRealm.getUserId().endsWith(defaultUserId)) {
            drawableRight = new IconicsDrawable(mContext, BaseFontAwesome.Icon.icon_checked).actionBar(6F).color(mContext.getResources().getColor(R.color
                    .md_red_500)).paddingDp(2);
        }


        mTvUserNick.setCompoundDrawables(null, null, drawableRight, null);


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
            inflate(getContext(), R.layout.item_chat_contact, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
