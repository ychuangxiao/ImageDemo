package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ilogie.android.library.common.util.ArrayUtils;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;

import com.sb.app.model.RedPackedModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.viewgroup.ChatFriendRedPacketItemView;
import com.sb.app.views.viewgroup.ChatFriendTransferItemView;
import com.sb.app.views.viewgroup.ChatMeRedPacketItemView;
import com.sb.app.views.viewgroup.ChatMeTransferItemView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ChatGroupRealm;
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

public class WeChatMessageActivity extends BaseActivity {

    private String groupId;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mRealm = Realm.getDefaultInstance();
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

    /**
     * 初始化参数
     */
    private void injectExtras() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_ == null) {

            finish();
            return;
        }

        if (!extras_.containsKey(AppConstant.EXTRA_NO)) {
            finish();
            return;
        }


        groupId = extras_.getString(AppConstant.EXTRA_NO);


        if (StringUtils.isEmpty(groupId)) {
            finish();
            return;
        }


    }


    ContactRealm meContactRealm = null;

    ContactRealm otherContactRealm = null;

    ChatGroupRealm chatGroupRealm = null;

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
                    meRedPacketItemView = ChatMeRedPacketItemView.build(this);
                    meRedPacketItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(meRedPacketItemView);

                } else {
                    friendRedPacketItemView = ChatFriendRedPacketItemView.build(this);
                    friendRedPacketItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(friendRedPacketItemView);
                }

                break;
            case AppConstant.MESSAGE_TYPE_TRANSFER:

                if (webChatMessageRealm.getContactRealm().isMe()) {
                    meTransferItemView = ChatMeTransferItemView.build(this);
                    meTransferItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(meTransferItemView);

                } else {
                    friendTransferItemView = ChatFriendTransferItemView.build(this);
                    friendTransferItemView.binder(webChatMessageRealm);
                    weChatLinearLayout.addView(friendTransferItemView);
                }
                break;
        }
    }


    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {


        injectExtras();

        //获取用户信息

        chatGroupRealm = mRealm.where(ChatGroupRealm.class).equalTo(TextConstant
                .COLUMN_NAME_FOR_ID, groupId).findFirst();

        if (chatGroupRealm == null || ArrayUtils.isEmpty(chatGroupRealm.getContactRealms())) {
            finish();
            return;
        }


        meContactRealm = chatGroupRealm.getContactRealms().where().equalTo("isMe", true).findFirst();
        otherContactRealm = chatGroupRealm.getContactRealms().where().equalTo("isMe", false).findFirst();


        setToolTitle(otherContactRealm.getUserNick()).setDisplayHome(true)
                .setHomeOnClickListener();


        //初始化图片

        ViewUtils.setCompoundTopDrawables(this, mTvPhoto, BaseFontAwesome.Icon.icon_phone, getResources().getColor(R
                .color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, mTvCamera, BaseFontAwesome.Icon.icon_camera, getResources().getColor
                (R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, mTvVideoChat, BaseFontAwesome.Icon.icon_video, getResources()
                .getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, mTvRedPacket, BaseFontAwesome.Icon.icon_red_packet, getResources()
                .getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, mTvTransfer, BaseFontAwesome.Icon.icon_transfer, getResources()
                .getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, mTvVoice, BaseFontAwesome.Icon.icon_voice, getResources().getColor(R
                .color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, mTvChatDateTime, BaseFontAwesome.Icon.icon_chat_time, getResources()
                .getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, mTvRetract, BaseFontAwesome.Icon.icon_retract, getResources()
                .getColor(R.color.md_grey_600), 10F);
        ViewUtils.setCompoundTopDrawables(this, tvClear, BaseFontAwesome.Icon.icon_delete, getResources().getColor(R
                .color.md_grey_600), 10F);


        mWebChatMessageRealms = mRealm.where(WebChatMessageRealm.class)
                .equalTo("groupId", chatGroupRealm.getId(), Case.INSENSITIVE).findAllSorted("sendTime", Sort.ASCENDING);


        mWebChatMessageRealms.addChangeListener(new RealmChangeListener<RealmResults<WebChatMessageRealm>>() {
            @Override
            public void onChange(RealmResults<WebChatMessageRealm> results) {
                results.size(); // => 1

            }
        });

        refreshData();

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {

        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return R.layout.activity_we_chat_message;
    }


    @OnClick(R.id.relativeLayout5)
    void onTransferClick() {


        RedPackedModel redPackedModel = new RedPackedModel();

        redPackedModel.setSource(AppConstant.MESSAGE_TYPE_OTHER_SEND_TRANSFER);
        redPackedModel.setSendType(AppConstant.RESULT_CODE_TRANSFER);


        Intent intent = new Intent(this, SendRedPacketActivity.class);
        intent.putExtra(AppConstant.EXTRA_NO, redPackedModel);
        navigateActivity(intent, AppConstant.REQUEST_CODE);

    }

    @OnClick(R.id.relativeLayout4)
    void onRedPacketClick() {


        RedPackedModel redPackedModel = new RedPackedModel();

        redPackedModel.setSource(AppConstant.MESSAGE_TYPE_ME_SEND_RED_PACKED);
        redPackedModel.setSendType(AppConstant.RESULT_CODE_RED_PACKET);
        Intent intent = new Intent(this, SendRedPacketActivity.class);
        intent.putExtra(AppConstant.EXTRA_NO, redPackedModel);
        navigateActivity(intent, AppConstant.REQUEST_CODE);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode != AppConstant.REQUEST_CODE || data == null || data.getExtras() == null) {
            return;
        }


        Bundle extras_ = data.getExtras();
        if (extras_ == null) {

            finish();
            return;
        }

        if (!extras_.containsKey(AppConstant.EXTRA_NO)) {
            finish();
            return;
        }


        switch (resultCode) {
            case AppConstant.RESULT_CODE_RED_PACKET:


                RedPackedModel redPackedModel = extras_.getParcelable(AppConstant.EXTRA_NO);

                sendRedPacketMessage(redPackedModel);

                break;
            case AppConstant.RESULT_CODE_TRANSFER:


                RedPackedModel redPackedModelA = extras_.getParcelable(AppConstant.EXTRA_NO);

                sendTransferMessage(redPackedModelA);

                break;
        }


    }


    private void sendRedPacketMessage(final RedPackedModel redPackedModel) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                WebChatMessageRealm webChatMessageRealm;

                ContactRealm me = realm.where(ContactRealm.class).equalTo("isMe", true).findFirst();
                ContactRealm other = realm.where(ContactRealm.class).equalTo("isMe", false).findFirst();


                webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());
                webChatMessageRealm.setAmount(Double.parseDouble(redPackedModel.getAmount().toString()));
                webChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_N);

                webChatMessageRealm.setGroupId(chatGroupRealm.getId());

                if (redPackedModel.getSource() == AppConstant.MESSAGE_TYPE_ME_SEND_RED_PACKED) {

                    webChatMessageRealm.setContactRealm(meContactRealm);

                    webChatMessageRealm.setMessage("恭喜发财，再接再厉！");
                    webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RED_PACKED);
                } else {

                    webChatMessageRealm.setContactRealm(otherContactRealm);

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


    private void sendTransferMessage(final RedPackedModel redPackedModel) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                WebChatMessageRealm webChatMessageRealm;

                ContactRealm me = realm.where(ContactRealm.class).equalTo("isMe", true).findFirst();
                ContactRealm other = realm.where(ContactRealm.class).equalTo("isMe", false).findFirst();


                webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());
                webChatMessageRealm.setAmount(Double.parseDouble(redPackedModel.getAmount().toString()));
                webChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_N);

                webChatMessageRealm.setGroupId(chatGroupRealm.getId());


                webChatMessageRealm.setMessage(String.format("￥%s", MathUtils.toString(new BigDecimal
                        (webChatMessageRealm.getAmount()))));
                webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_TRANSFER);
                webChatMessageRealm.setSubMessage("转账给" + other.getUserNick());


                if (redPackedModel.getSource() == AppConstant.MESSAGE_TYPE_ME_SEND_RED_PACKED) {

                    webChatMessageRealm.setContactRealm(meContactRealm);

                } else {

                    webChatMessageRealm.setContactRealm(otherContactRealm);

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
}
