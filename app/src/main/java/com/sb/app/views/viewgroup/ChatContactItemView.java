package com.sb.app.views.viewgroup;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import com.sb.app.R;
import com.sb.app.utils.ViewUtils;
import com.sb.data.entitys.realm.ContactRealm;

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

    /**
     * 绑定按钮标题
     */
    public void binder(ContactRealm contactRealm) {

        mContactRealm = contactRealm;
        mTvUserNick.setText(contactRealm.getUserNick());


        if (contactRealm.isSystem()) {

            mAvatarImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        }


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
