package com.sb.app.views.fragment.tencent.ios;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sb.app.R;
import com.sb.app.model.RedPackedDetailsModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.MobileStyleRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

public class ReceiveRedPacketsIosFragment extends BaseFragmentDaggerActivity {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    static BottomNavigationView mBottomNavigationView;

    RedPackedDetailsModel mRedPackedDetailsModel;

    ContactRealm mContactRealm;

    Realm mRealm;
    @BindView(R.id.tvSendUserName)
    AppCompatTextView mTvSendUserName;
    @BindView(R.id.tvSendDesc)
    TextView mTvSendDesc;
    @BindView(R.id.tvAmount)
    TextView mTvAmount;
    @BindView(R.id.tvRedPacketsInfo)
    TextView mTvRedPacketsInfo;
    @BindView(R.id.tvMessage)
    TextView mTvMessage;
    Unbinder unbinder;


    public ReceiveRedPacketsIosFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReceiveRedPacketsFragment.
     */

    public static ReceiveRedPacketsIosFragment newInstance(RedPackedDetailsModel redPackedDetailsModel,
                                                           BottomNavigationView
            bottomNavigationView) {
        ReceiveRedPacketsIosFragment fragment = new ReceiveRedPacketsIosFragment();
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




        mContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getSendUserId()).findFirst();

        mTvSendUserName.setText(mContactRealm.getUserNick());

        WebChatMessageRealm messageRealm = mRealm.where(WebChatMessageRealm.class).equalTo(TextConstant
                        .COLUMN_NAME_FOR_ID,
                mRedPackedDetailsModel.getMessageId()).findFirst();

        mTvSendDesc.setText(messageRealm.getMessage());
        mTvAmount.setText(MathUtils.toString(new BigDecimal(messageRealm.getAmount().toString()
        )));


    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_receive_red_packets_ios;
    }

    MobileChangeListener<MobileStyleRealm> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<MobileStyleRealm>
                                                modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvAmount)
    void onAmountClick()
    {

    }
}
