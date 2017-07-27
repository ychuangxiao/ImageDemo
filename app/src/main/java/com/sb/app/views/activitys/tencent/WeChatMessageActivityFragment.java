package com.sb.app.views.activitys.tencent;

import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ilogie.android.library.common.util.ArrayUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.components.BizComponent;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.app.views.viewgroup.ChatFriendRedPacketItemView;
import com.sb.app.views.viewgroup.ChatFriendTransferItemView;
import com.sb.app.views.viewgroup.ChatMeRedPacketItemView;
import com.sb.app.views.viewgroup.ChatMeTransferItemView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.math.BigDecimal;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeChatMessageActivityFragment extends BaseFragmentDaggerActivity {


    @BindView(R.id.emojiRecycler)
    RecyclerView emojiRecycler;

    @BindView(R.id.btnEmoji)
    AppCompatImageView btnEmoji;


    @BindView(R.id.recyclerList)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.weChatLinearLayout)
    LinearLayout weChatLinearLayout;


    Realm mRealm;


    @BindView(R.id.etMessageContent)
    AppCompatEditText etMessageContent;
    @BindView(R.id.tvPhoto)
    AppCompatTextView mTvPhoto;
    @BindView(R.id.relativeLayout1)
    RelativeLayout mRelativeLayout1;
    @BindView(R.id.tvCamera)
    AppCompatTextView mTvCamera;
    @BindView(R.id.relativeLayout2)
    RelativeLayout mRelativeLayout2;
    @BindView(R.id.tvVideoChat)
    AppCompatTextView mTvVideoChat;
    @BindView(R.id.relativeLayout3)
    RelativeLayout mRelativeLayout3;
    @BindView(R.id.tvRedPacket)
    AppCompatTextView mTvRedPacket;
    @BindView(R.id.relativeLayout4)
    RelativeLayout mRelativeLayout4;
    @BindView(R.id.consLayout1)
    ConstraintLayout mConsLayout1;
    @BindView(R.id.tvTransfer)
    AppCompatTextView mTvTransfer;
    @BindView(R.id.relativeLayout5)
    RelativeLayout mRelativeLayout5;
    @BindView(R.id.tvChatDateTime)
    AppCompatTextView mTvChatDateTime;
    @BindView(R.id.relativeLayout6)
    RelativeLayout mRelativeLayout6;
    @BindView(R.id.tvVoice)
    AppCompatTextView mTvVoice;
    @BindView(R.id.relativeLayout7)
    RelativeLayout mRelativeLayout7;
    @BindView(R.id.tvRetract)
    AppCompatTextView mTvRetract;
    @BindView(R.id.relativeLayout8)
    RelativeLayout mRelativeLayout8;

    @BindView(R.id.relativeLayout9)
    RelativeLayout relativeLayout9;

    @BindView(R.id.tvClear)
    AppCompatTextView tvClear;


    @BindView(R.id.chatTypeConstraintLayout)
    ConstraintLayout chatTypeConstraintLayout;
    @BindView(R.id.btnPlus)
    AppCompatImageView btnPlus;


    RealmResults<WebChatMessageRealm> mWebChatMessageRealms;//聊天集合

    public WeChatMessageActivityFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void DestroyView() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {


        //初始化图片

        ViewUtils.setCompoundTopDrawables(getActivity(), mTvPhoto, BaseFontAwesome.Icon.icon_phone, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), mTvCamera, BaseFontAwesome.Icon.icon_camera, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), mTvVideoChat, BaseFontAwesome.Icon.icon_video, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), mTvRedPacket, BaseFontAwesome.Icon.icon_red_packet, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), mTvTransfer, BaseFontAwesome.Icon.icon_transfer, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), mTvVoice, BaseFontAwesome.Icon.icon_voice, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), mTvChatDateTime, BaseFontAwesome.Icon.icon_chat_time, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), mTvRetract, BaseFontAwesome.Icon.icon_retract, getResources().getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(getActivity(), tvClear, BaseFontAwesome.Icon.icon_delete, getResources().getColor(R.color.md_grey_600), 10F);


        mWebChatMessageRealms = mRealm.where(WebChatMessageRealm.class)
                .equalTo("groupId", "A", Case.INSENSITIVE).findAllSorted("sendTime", Sort.ASCENDING);


        mWebChatMessageRealms.addChangeListener(new RealmChangeListener<RealmResults<WebChatMessageRealm>>() {
            @Override
            public void onChange(RealmResults<WebChatMessageRealm> results) {
                results.size(); // => 1

            }
        });


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                if (ArrayUtils.isNotEmpty(mWebChatMessageRealms)) {
                    mWebChatMessageRealms.deleteAllFromRealm();
                }


                WebChatMessageRealm webChatMessageRealm;

                ContactRealm me = realm.where(ContactRealm.class).equalTo("isMe", true).findFirst();


                ContactRealm other = realm.where(ContactRealm.class).equalTo("isMe", false).findFirst();


                webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());
                webChatMessageRealm.setAmount(222800.98d);
                webChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_N);
                webChatMessageRealm.setContactRealm(other);
                webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_TRANSFER);
                webChatMessageRealm.setGroupId("A");
                webChatMessageRealm.setMessage(String.format("￥%s", MathUtils.toString(new BigDecimal(webChatMessageRealm.getAmount()))));
                webChatMessageRealm.setSubMessage("转账给" + me.getUserNick());
                webChatMessageRealm.setSendTime(System.currentTimeMillis());
                webChatMessageRealm.setSourceMessage("");


            }
        });


        refreshData();
    }

    ChatMeRedPacketItemView meRedPacketItemView;
    ChatFriendRedPacketItemView friendRedPacketItemView;
    ChatMeTransferItemView meTransferItemView;
    ChatFriendTransferItemView friendTransferItemView;

    private void refreshData() {


        for (WebChatMessageRealm webChatMessageRealm : mWebChatMessageRealms) {


            createMessageLayout(webChatMessageRealm);
        }
    }

    private void createMessageLayout(WebChatMessageRealm webChatMessageRealm) {

        switch (webChatMessageRealm.getMessageType()) {
            case AppConstant.MESSAGE_TYPE_RED_PACKED:

                if (webChatMessageRealm.getContactRealm().isMe()) {
                    meRedPacketItemView = ChatMeRedPacketItemView.build(getActivity());
                    meRedPacketItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(meRedPacketItemView);

                } else {
                    friendRedPacketItemView = ChatFriendRedPacketItemView.build(getActivity());
                    friendRedPacketItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(friendRedPacketItemView);
                }

                break;
            case AppConstant.MESSAGE_TYPE_TRANSFER:

                if (webChatMessageRealm.getContactRealm().isMe()) {
                    meTransferItemView = ChatMeTransferItemView.build(getActivity());
                    meTransferItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(meTransferItemView);

                } else {
                    friendTransferItemView = ChatFriendTransferItemView.build(getActivity());
                    friendTransferItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(friendTransferItemView);
                }
                break;
        }
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_we_chat_message;
    }


    @OnClick(R.id.relativeLayout4)
    void onTransferClick() {
        sendMessage(false);
    }

    @OnClick(R.id.relativeLayout5)
    void onRedPacketClick() {

        sendMessage(true);

    }

    private void sendMessage(final boolean isMe) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                WebChatMessageRealm webChatMessageRealm;

                ContactRealm me = realm.where(ContactRealm.class).equalTo("isMe", true).findFirst();
                ContactRealm other = realm.where(ContactRealm.class).equalTo("isMe", false).findFirst();


                webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());
                webChatMessageRealm.setAmount(222800.98d);
                webChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_N);
                webChatMessageRealm.setContactRealm((isMe) ? me : other);

                webChatMessageRealm.setGroupId("A");

                if (isMe) {

                    webChatMessageRealm.setMessage(String.format("￥%s", MathUtils.toString(new BigDecimal(webChatMessageRealm.getAmount()))));
                    webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_TRANSFER);
                    webChatMessageRealm.setSubMessage("转账给" + other.getUserNick());
                } else {

                    webChatMessageRealm.setMessage("恭喜发财，再接再厉！");
                    webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RED_PACKED);
                }

                webChatMessageRealm.setSendTime(System.currentTimeMillis());
                webChatMessageRealm.setSourceMessage("");

                createMessageLayout(webChatMessageRealm);
            }
        });

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mNestedScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @OnClick(R.id.btnPlus)
    void onPlusClick() {

        if (btnPlus.getTag().toString().compareTo(String.valueOf(AppConstant.ACTION_10)) == 0) {
            chatTypeConstraintLayout.setVisibility(View.VISIBLE);
            btnPlus.setTag(String.valueOf(AppConstant.ACTION_20));

            etMessageContent.clearFocus();

        } else if (btnPlus.getTag().toString().compareTo(String.valueOf(AppConstant.ACTION_20)) == 0) {
            chatTypeConstraintLayout.setVisibility(View.GONE);
            btnPlus.setTag(String.valueOf(AppConstant.ACTION_10));
            etMessageContent.requestFocus();
        }


    }

    @OnClick(R.id.tvClear)
    void onClearClick() {


        if (ArrayUtils.isNotEmpty(mWebChatMessageRealms)) {
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    mWebChatMessageRealms.deleteAllFromRealm();
                }
            });
        }



        weChatLinearLayout.removeAllViews();
    }

    /**
     * 初始化注解
     */
    @Override
    public void initInjector() {
        this.getComponent(BizComponent.class).inject(this);
    }

    /**
     * 初始化中间者
     */
    @Override
    public void initPresenter() {

    }

}
