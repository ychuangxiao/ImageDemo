package com.sb.app.views.fragment.tencent.ios;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.RedPackedDetailsModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.MobileStyleRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.math.BigDecimal;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class TransferConfirmDetailsIosFragment extends BaseFragmentDaggerActivity {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    static BottomNavigationView mBottomNavigationView;

    RedPackedDetailsModel mRedPackedDetailsModel;


    Realm mRealm;


    @BindView(R.id.tvTransferAmount)
    AppCompatTextView mTvTransferAmount;
    @BindView(R.id.btnHandle)
    AppCompatButton mBtnHandle;
    @BindView(R.id.tvTransferTime)
    TextView mTvTransferTime;



    ContactRealm currentContactRealm = null;

    ContactRealm meContactRealm = null;

    ContactRealm receivedUserRealm = null;
    ContactRealm sendUserRealm = null;
    WebChatMessageRealm mChatMessageRealm;
    @BindView(R.id.tvConfirmMessage)
    AppCompatTextView mTvConfirmMessage;
    @BindView(R.id.tvOtherMessage)
    AppCompatTextView mTvOtherMessage;
    @BindView(R.id.tvConfirmTopMessage)
    AppCompatTextView mTvConfirmTopMessage;

    public TransferConfirmDetailsIosFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReceiveRedPacketsFragment.
     */

    public static TransferConfirmDetailsIosFragment newInstance(RedPackedDetailsModel redPackedDetailsModel,
                                                                BottomNavigationView
                                                                     bottomNavigationView) {
        TransferConfirmDetailsIosFragment fragment = new TransferConfirmDetailsIosFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, redPackedDetailsModel);
        mBottomNavigationView = bottomNavigationView;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRedPackedDetailsModel = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public void initInjector() {

    }


    @Override
    public void initPresenter() {

    }


    @Override
    protected void DestroyView() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

    @Override
    public void initView() {
        mBottomNavigationView.setVisibility(View.GONE);


        //判断当前用户是那个，如果是朋友，那么现实的是待对方确认，如果是自己，现实为待自己收钱


        refreshData(mRedPackedDetailsModel);

    }

    private void refreshData(RedPackedDetailsModel redPackedDetailsModel) {
        mRedPackedDetailsModel = redPackedDetailsModel;

        currentContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getCurrentUserId()).findFirst();


        meContactRealm = mRealm.where(ContactRealm.class).equalTo("isMe",
                true).findFirst();

        sendUserRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getSendUserId()).findFirst();

        receivedUserRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getReceivedUserId()).findFirst();


        //判断 当前用户是自己，同时发送者是自己，那么现实为 对方确认
        if (currentContactRealm.getUserId().equals(sendUserRealm.getUserId())) {

            mBtnHandle.setVisibility(View.GONE);
            mTvOtherMessage.setText("重发转账信息");
            mTvConfirmMessage.setText("1天内朋友未确认，将退还给你。");
            mTvConfirmTopMessage.setText(String.format("待%s确认收钱", receivedUserRealm.getUserNick()));
        } else if (!currentContactRealm.getUserId().equals(sendUserRealm.getUserId()) ) {
            //判断 当前用户是自己，同时发送者不是自己，那么现实为确认
            mBtnHandle.setVisibility(View.VISIBLE);
            mTvOtherMessage.setText("立即退还");
            mTvConfirmMessage.setText("1天内未确认，将退还给对方。");
            mTvConfirmTopMessage.setText("待确认收钱");

        }


        mChatMessageRealm = mRealm.where(WebChatMessageRealm.class).equalTo(TextConstant
                        .COLUMN_NAME_FOR_ID,
                mRedPackedDetailsModel.getMessageId()).findFirst();

        mTvTransferTime.setText(TimeUtils.millis2String(mChatMessageRealm.getSendTime(), TimeUtils.DEFAULT_PATTERN));
        mTvTransferAmount.setText(String.format("￥%s", MathUtils.toString(new BigDecimal(mChatMessageRealm.getAmount()
                .toString()
        ))));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_transfer_confirm_ios;
    }

    MobileChangeListener<MobileStyleRealm> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<MobileStyleRealm>
                                                modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @OnClick(R.id.btnHandle)
    void onHandleClick() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mChatMessageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_Y);
                mChatMessageRealm.setReceiveTransferTime(TimeUtils.addHour(ViewUtils.getRandomIndex(10),mChatMessageRealm.getSendTime(),TimeUtils.DEFAULT_PATTERN));
                WebChatMessageRealm messageRealm = realm.createObject(WebChatMessageRealm.class, UUID
                        .randomUUID().toString());

                messageRealm.setSendContact(mChatMessageRealm.getContactRealm());
                messageRealm.setContactRealm(currentContactRealm);

                messageRealm.setSendTransferTime(mChatMessageRealm.getSendTime());
                messageRealm.setReceiveTransferTime(mChatMessageRealm.getReceiveTransferTime());

                messageRealm.setGroupId(mChatMessageRealm.getGroupId());
                messageRealm.setMessageType(AppConstant.MESSAGE_TYPE_TRANSFER);
                messageRealm.setSendTime(System.currentTimeMillis());
                messageRealm.setSourceMessage(mChatMessageRealm.getId());
                messageRealm.setMessage("已收钱");
                messageRealm.setAmount(mChatMessageRealm.getAmount());
                messageRealm.setAmountStatus(AppConstant.RECEIVED_ACTION_Y);
            }
        });


        Intent intent = new Intent();

        intent.putExtra(AppConstant.EXTRA_NO, "");

        getActivity().setResult(AppConstant.REQUEST_CODE, intent);


        getActivity().finish();
    }

}
