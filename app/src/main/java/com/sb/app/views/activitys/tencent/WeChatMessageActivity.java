package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.model.RedPackedDetailsModel;
import com.sb.app.model.RedPackedModel;
import com.sb.app.utils.LogUtils;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.fragment.BottomSheetUserFragment;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.app.views.listeners.WeChatMessage2ClickListener;
import com.sb.app.views.viewgroup.ChatFriendMessageItemView;
import com.sb.app.views.viewgroup.ChatFriendRedPacketItemView;
import com.sb.app.views.viewgroup.ChatFriendTransferItemView;
import com.sb.app.views.viewgroup.ChatMeMessageItemView;
import com.sb.app.views.viewgroup.ChatMeRedPacketItemView;
import com.sb.app.views.viewgroup.ChatMeTransferItemView;
import com.sb.app.views.viewgroup.chat.ReceiveRedPacketItemView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.math.BigDecimal;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class WeChatMessageActivity extends BaseActivity implements HasComponent<BizComponent>
        , WeChatMessage2ClickListener<WebChatMessageRealm, RelativeLayout>, RecyclerClickListener<ContactRealm> {

    @BindView(R.id.btnEmoji)
    AppCompatImageView mBtnEmoji;


    @BindView(R.id.btnSend)
    AppCompatButton mBtnSend;

    private String groupId;


    @BindView(R.id.emojiRecycler)
    RecyclerView emojiRecycler;


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


    BottomSheetUserFragment mBottomSheetUserFragment;


    RealmResults<WebChatMessageRealm> mWebChatMessageRealms;//聊天集合


    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mRealm = Realm.getDefaultInstance();
        this.mBizComponent = DaggerBizComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
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

    String defaultUserId;

    ContactRealm meContactRealm = null;

    ContactRealm otherContactRealm = null;

    ChatGroupRealm chatGroupRealm = null;

    ChatMeRedPacketItemView meRedPacketItemView;
    ChatFriendRedPacketItemView friendRedPacketItemView;
    ChatMeTransferItemView meTransferItemView;
    ChatFriendTransferItemView friendTransferItemView;
    ChatMeMessageItemView mMeMessageItemView;
    ChatFriendMessageItemView mFriendMessageItemView;
    ReceiveRedPacketItemView mReceiveRedPacketItemView;

    private void refreshData(final int x, final int y) {

        weChatLinearLayout.removeAllViews();
        int i = 0;
        for (WebChatMessageRealm webChatMessageRealm : mWebChatMessageRealms) {


            createMessageLayout(webChatMessageRealm, i == 0);
            i++;
        }


        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mNestedScrollView.scrollBy(x, y);
            }
        });

    }


    private void createMessageLayout(WebChatMessageRealm webChatMessageRealm, boolean isFirst) {


        switch (webChatMessageRealm.getMessageType()) {
            case AppConstant.MESSAGE_TYPE_RED_PACKED:

                if (webChatMessageRealm.getContactRealm().isMe()) {
                    meRedPacketItemView = ChatMeRedPacketItemView.build(this);
                    lastSendTime = meRedPacketItemView.binder(webChatMessageRealm, lastSendTime, isFirst);

                    meRedPacketItemView.setMessageClickListener(this);
                    weChatLinearLayout.addView(meRedPacketItemView);

                } else {
                    friendRedPacketItemView = ChatFriendRedPacketItemView.build(this);
                    lastSendTime = friendRedPacketItemView.binder(webChatMessageRealm, lastSendTime, isFirst);
                    friendRedPacketItemView.setMessageClickListener(this);
                    weChatLinearLayout.addView(friendRedPacketItemView);
                }

                break;
            case AppConstant.MESSAGE_TYPE_RECEIVE_TRANSFER:
            case AppConstant.MESSAGE_TYPE_TRANSFER:

                if (webChatMessageRealm.getContactRealm().isMe()) {
                    meTransferItemView = ChatMeTransferItemView.build(this);
                    lastSendTime = meTransferItemView.binder(webChatMessageRealm, lastSendTime, isFirst);
                    meTransferItemView.setMessageClickListener(this);
                    weChatLinearLayout.addView(meTransferItemView);

                } else {
                    friendTransferItemView = ChatFriendTransferItemView.build(this);
                    lastSendTime = friendTransferItemView.binder(webChatMessageRealm, lastSendTime, isFirst);
                    friendTransferItemView.setMessageClickListener(this);
                    weChatLinearLayout.addView(friendTransferItemView);
                }
                break;

            case AppConstant.MESSAGE_TYPE_MESSAGE:

                if (webChatMessageRealm.getContactRealm().isMe()) {
                    mMeMessageItemView = ChatMeMessageItemView.build(this);
                    lastSendTime = mMeMessageItemView.binder(webChatMessageRealm, lastSendTime, isFirst);

                    weChatLinearLayout.addView(mMeMessageItemView);

                } else {
                    mFriendMessageItemView = ChatFriendMessageItemView.build(this);
                    lastSendTime = mFriendMessageItemView.binder(webChatMessageRealm, lastSendTime, isFirst);

                    weChatLinearLayout.addView(mFriendMessageItemView);
                }
                break;
            case AppConstant.MESSAGE_TYPE_RECEIVE_RED_PACKET:

                mReceiveRedPacketItemView = ReceiveRedPacketItemView.build(this);

                lastSendTime = mReceiveRedPacketItemView.binder(webChatMessageRealm, lastSendTime, isFirst);
                weChatLinearLayout.addView(mReceiveRedPacketItemView);

                break;

        }


        LogUtils.d("ceshi", TimeUtils.millis2String(lastSendTime, TimeUtils.DEFAULT_PATTERN_9));
    }


    @OnTouch(R.id.recyclerList)
    boolean onChatLayoutTouch(android.view.View view, android.view.MotionEvent event) {
        hiddenBottomHandleLayout();
        etMessageContent.clearFocus();
        hideSoftInput(etMessageContent);
        return false;
    }


    @OnTouch(R.id.etMessageContent)
    boolean onMessageContentTouch(android.view.View view, android.view.MotionEvent event) {
        hiddenBottomHandleLayout();
        return false;
    }


    Long lastSendTime = 0L;

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

        defaultUserId = meContactRealm.getUserId();
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


        etMessageContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (StringUtils.isEmpty(etMessageContent.getText().toString())) {


                    if (btnPlus.getVisibility() != View.VISIBLE) {
                        btnPlus.setVisibility(View.VISIBLE);
                        mBtnSend.setVisibility(View.GONE);


                    }


                } else {

                    if (mBtnSend.getVisibility() != View.VISIBLE) {

                        mBtnSend.setVisibility(View.VISIBLE);
                        btnPlus.setVisibility(View.GONE);
                    }

                }

            }
        });


        mWebChatMessageRealms = mRealm.where(WebChatMessageRealm.class)
                .equalTo("groupId", chatGroupRealm.getId(), Case.INSENSITIVE).findAllSorted("sendTime", Sort.ASCENDING);


        //得到最后一个消息的记录

        if (ArrayUtils.isNotEmpty(mWebChatMessageRealms)) {
            lastSendTime = mWebChatMessageRealms.where().max("sendTime").longValue();
        }


        mWebChatMessageRealms.addChangeListener(new RealmChangeListener<RealmResults<WebChatMessageRealm>>() {
            @Override
            public void onChange(RealmResults<WebChatMessageRealm> results) {
                results.size(); // => 1


                if (results.size() >= 1) {
                    mRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            //拿到最后一个记录 更新分组表信息

                            WebChatMessageRealm chatMessageRealm = mWebChatMessageRealms.where().findAllSorted
                                    ("sendTime", Sort
                                            .DESCENDING).first();
                            //chatGroupRealm

                            chatGroupRealm.setLastTime(chatMessageRealm.getSendTime());
                            chatGroupRealm.setLastMessage(chatMessageRealm.getMessage());
                        }
                    });
                }

            }
        });

        refreshData(0, 0);

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


        //判断当前用户是不是登录中自己
        if (defaultUserId.equals(meContactRealm.getUserId())) {
            redPackedModel.setReceiveUserId(otherContactRealm.getUserId());
            redPackedModel.setSendUserId(meContactRealm.getUserId());
        } else {
            redPackedModel.setSendUserId(otherContactRealm.getUserId());
            redPackedModel.setReceiveUserId(meContactRealm.getUserId());

        }


        Intent intent = new Intent(this, TransferActivity.class);
        intent.putExtra(AppConstant.EXTRA_NO, redPackedModel);
        navigateActivity(intent, AppConstant.REQUEST_CODE);

    }

    @OnClick(R.id.relativeLayout4)
    void onRedPacketClick() {


        RedPackedModel redPackedModel = new RedPackedModel();

        if (defaultUserId.equals(meContactRealm.getUserId())) {
            redPackedModel.setSendUserId(otherContactRealm.getUserId());
        } else {
            redPackedModel.setSendUserId(meContactRealm.getUserId());
        }

        redPackedModel.setSource(AppConstant.MESSAGE_TYPE_ME_SEND_RED_PACKED);
        redPackedModel.setSendType(AppConstant.RESULT_CODE_RED_PACKET);
        Intent intent = new Intent(this, SendRedPacketActivity.class);
        intent.putExtra(AppConstant.EXTRA_NO, redPackedModel);
        navigateActivity(intent, AppConstant.REQUEST_CODE);

    }


    @OnClick(R.id.btnPlus)
    void onPlusClick() {

        if (btnPlus.getTag().toString().compareTo(String.valueOf(AppConstant.ACTION_10)) == 0) {
            showBottomHandleLayout();

        } else if (btnPlus.getTag().toString().compareTo(String.valueOf(AppConstant.ACTION_20)) == 0) {
            showSoftInput();
            hiddenBottomHandleLayout();
        }


    }

    private void hiddenBottomHandleLayout() {

        chatTypeConstraintLayout.setVisibility(View.GONE);
        btnPlus.setTag(String.valueOf(AppConstant.ACTION_10));
        etMessageContent.requestFocus();
    }

    private void showBottomHandleLayout() {
        hideSoftInput(etMessageContent);

        chatTypeConstraintLayout.setVisibility(View.VISIBLE);
        btnPlus.setTag(String.valueOf(AppConstant.ACTION_20));

        etMessageContent.clearFocus();
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


    @OnClick(R.id.btnSend)
    void onSendClick() {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                WebChatMessageRealm webChatMessageRealm;


                webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());

                if (defaultUserId.equals(meContactRealm.getUserId())) {

                    webChatMessageRealm.setContactRealm(meContactRealm);
                } else {
                    webChatMessageRealm.setContactRealm(otherContactRealm);

                }

                webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_MESSAGE);
                webChatMessageRealm.setGroupId(chatGroupRealm.getId());
                webChatMessageRealm.setMessage(etMessageContent.getText().toString().trim());
                webChatMessageRealm.setSendTime(System.currentTimeMillis());
                webChatMessageRealm.setSourceMessage("");
                createMessageLayout(webChatMessageRealm, (lastSendTime == 0L));
            }
        });

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mNestedScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        etMessageContent.setText("");
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


                webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());
                webChatMessageRealm.setAmount(Double.parseDouble(redPackedModel.getAmount().toString()));
                webChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_N);

                webChatMessageRealm.setGroupId(chatGroupRealm.getId());

                //谁发的

                webChatMessageRealm.setMessage(StringUtils.isNotEmpty(redPackedModel.getContent()) ? redPackedModel.getContent() : "恭喜发财，再接再厉！");


                webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RED_PACKED);
                if (defaultUserId.equals(meContactRealm.getUserId())) {

                    webChatMessageRealm.setContactRealm(meContactRealm);

                    webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RED_PACKED);
                } else {

                    webChatMessageRealm.setContactRealm(otherContactRealm);


                    webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RED_PACKED);
                }

                webChatMessageRealm.setSendTime(System.currentTimeMillis());
                webChatMessageRealm.setSourceMessage("");

                createMessageLayout(webChatMessageRealm, (lastSendTime == 0L));
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


                webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());
                webChatMessageRealm.setAmount(Double.parseDouble(redPackedModel.getAmount().toString()));
                webChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_N);

                webChatMessageRealm.setGroupId(chatGroupRealm.getId());


                webChatMessageRealm.setSubMessage(String.format("￥%s", MathUtils.toString(new BigDecimal
                        (webChatMessageRealm.getAmount()))));
                webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_TRANSFER);


                //判断发送者是不是自己
                if (meContactRealm.getUserId().equals(redPackedModel.getSendUserId())) {


                    webChatMessageRealm.setContactRealm(meContactRealm);

                    webChatMessageRealm.setMessage(StringUtils.isEmpty(redPackedModel.getContent()) ? "转账给" +
                            otherContactRealm.getUserNick()
                            : redPackedModel.getContent());

                } else {

                    webChatMessageRealm.setContactRealm(otherContactRealm);
                    webChatMessageRealm.setMessage(StringUtils.isEmpty(redPackedModel.getContent()) ? "转账给" +
                            meContactRealm.getUserNick()
                            : redPackedModel.getContent());
                }
                webChatMessageRealm.setSendTime(System.currentTimeMillis());
                webChatMessageRealm.setSourceMessage("");
                createMessageLayout(webChatMessageRealm, (lastSendTime == 0L));
            }
        });

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mNestedScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }


    @OnLongClick(R.id.btnModeVoice)
    boolean onModeVoiceLongClick() {
        if (mBottomSheetUserFragment == null) {
            mBottomSheetUserFragment = BottomSheetUserFragment.newInstance(groupId);
            mBottomSheetUserFragment.setOnChooseUserItemClickListener(this);

        }

        mBottomSheetUserFragment.show(getSupportFragmentManager(), BottomSheetUserFragment.class.getSimpleName());

        return true;
    }

    @Override
    public void onItemClickListener(final WebChatMessageRealm model, RelativeLayout relativeLayout) {
        //如果是转账
        if (model.getMessageType() == AppConstant.MESSAGE_TYPE_TRANSFER) {
            //判断钱被收了没有
            if (model.getAmountStatus() != AppConstant.RECEIVED_ACTION_Y) {

                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        model.setAmountStatus(AppConstant.RECEIVED_ACTION_Y);
                        WebChatMessageRealm messageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());

                        //添加一条消息
                        //说明是我点的
                        if (model.getContactRealm().getUserId().equals(meContactRealm.getUserId())) {

                            messageRealm.setContactRealm(otherContactRealm);
                        } else {
                            messageRealm.setContactRealm(meContactRealm);
                        }
                        messageRealm.setGroupId(groupId);
                        messageRealm.setMessageType(AppConstant.MESSAGE_TYPE_TRANSFER);
                        messageRealm.setSendTime(System.currentTimeMillis());
                        messageRealm.setSourceMessage(model.getId());
                        messageRealm.setMessage("已收钱");


                        messageRealm.setAmount(model.getAmount());
                        messageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_Y);

                        createMessageLayout(messageRealm, (lastSendTime == 0L));

                    }
                });
            } else {

                alertMsg("钱被收了");

            }
        }
        if (model.getMessageType() == AppConstant.MESSAGE_TYPE_RED_PACKED) {
            //如果没有收，那么就收

            if (model.getAmountStatus() != AppConstant.RECEIVED_ACTION_Y) {

                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        model.setAmountStatus(AppConstant.RECEIVED_ACTION_Y);


                        WebChatMessageRealm webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());

                        //添加一条消息
                        //说明是我点的
                        if (model.getContactRealm().getUserId().equals(meContactRealm.getUserId())) {

                            webChatMessageRealm.setContactRealm(otherContactRealm);
                        } else {
                            webChatMessageRealm.setContactRealm(meContactRealm);
                        }

                        webChatMessageRealm.setGroupId(groupId);
                        webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RECEIVE_RED_PACKET);
                        webChatMessageRealm.setSendTime(System.currentTimeMillis());
                        webChatMessageRealm.setSourceMessage(model.getId());
                        webChatMessageRealm.setAmount(model.getAmount());
                        webChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_Y);
                        createMessageLayout(webChatMessageRealm, (lastSendTime == 0L));

                    }
                });

            } else {


                /*mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        model.deleteFromRealm();
                    }
                });

                refreshData(relativeLayout.getScrollX(),relativeLayout.getScrollY());
*/
                Intent intent = new Intent(WeChatMessageActivity.this, RedPacketsDetailActivity.class);

                RedPackedDetailsModel model1 = new RedPackedDetailsModel();

                model1.setSendUserId(meContactRealm.getUserId());
                model1.setReceivedUserId(otherContactRealm.getUserId());
                model1.setMessageId(model.getId());
                model1.setGroupId(model.getGroupId());
                intent.putExtra(AppConstant.EXTRA_NO, model1);

                navigateActivity(intent);
            }

        }

    }

    BizComponent mBizComponent;


    @Override
    public BizComponent getComponent() {
        return mBizComponent;
    }

    @Override
    public void onItemClickListener(ContactRealm model) {
        defaultUserId = model.getUserId();
    }
}
