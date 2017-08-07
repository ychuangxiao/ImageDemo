package com.sb.app.views.fragment.tencent.google;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.sb.app.R;
import com.sb.app.model.RedPackedDetailsModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.TimeUtils;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.MobileStyleRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.math.BigDecimal;

import butterknife.BindView;
import io.realm.Realm;

public class TransferSuccessDetailsFragment extends BaseFragmentDaggerActivity {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    static BottomNavigationView mBottomNavigationView;

    RedPackedDetailsModel mRedPackedDetailsModel;


    Realm mRealm;


    ContactRealm currentContactRealm = null;

    ContactRealm meContactRealm = null;

    ContactRealm receivedUserRealm = null;
    ContactRealm sendUserRealm = null;


    WebChatMessageRealm mChatMessageRealm;
    @BindView(R.id.tvUserNick)
    TextView mTvUserNick;
    @BindView(R.id.tvTransferAmount)
    AppCompatTextView mTvTransferAmount;
    @BindView(R.id.tvConfirmMessage)
    AppCompatTextView mTvConfirmMessage;
    @BindView(R.id.tvOtherMessage)
    AppCompatTextView mTvOtherMessage;
    @BindView(R.id.tvTransferTime)
    TextView mTvTransferTime;
    @BindView(R.id.tvReceiveTime)
    TextView mTvReceiveTime;


    public TransferSuccessDetailsFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReceiveRedPacketsFragment.
     */

    public static TransferSuccessDetailsFragment newInstance(RedPackedDetailsModel redPackedDetailsModel,
                                                             BottomNavigationView
                                                                     bottomNavigationView) {
        TransferSuccessDetailsFragment fragment = new TransferSuccessDetailsFragment();
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


            mTvOtherMessage.setText("");
            mTvConfirmMessage.setText("已存入对方零钱中");
            mTvUserNick.setText(receivedUserRealm.getUserNick());




        } else if (!currentContactRealm.getUserId().equals(sendUserRealm.getUserId()) ) {
            //判断 当前用户是自己，同时发送者不是自己，那么现实为确认

            mTvOtherMessage.setText("零钱");
            mTvConfirmMessage.setText("已存入你的");
            mTvUserNick.setText("");
        }





        mChatMessageRealm = mRealm.where(WebChatMessageRealm.class).equalTo(TextConstant
                        .COLUMN_NAME_FOR_ID,
                mRedPackedDetailsModel.getMessageId()).findFirst();

        mTvReceiveTime.setText(TimeUtils.millis2String(mChatMessageRealm.getReceiveTransferTime(), TimeUtils
                .DEFAULT_PATTERN_2));
        mTvTransferTime.setText(TimeUtils.millis2String(mChatMessageRealm.getSendTransferTime(), TimeUtils.DEFAULT_PATTERN_2));
        mTvTransferAmount.setText(String.format("￥%s", MathUtils.toString(new BigDecimal(mChatMessageRealm.getAmount()
                .toString()
        ))));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_transfer_success;
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


}
