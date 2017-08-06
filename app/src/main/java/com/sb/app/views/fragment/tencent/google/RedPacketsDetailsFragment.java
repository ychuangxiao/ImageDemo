package com.sb.app.views.fragment.tencent.google;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ilogie.android.library.common.util.StringUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.sb.app.R;
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

import java.io.File;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

public class RedPacketsDetailsFragment extends BaseFragmentDaggerActivity {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    static BottomNavigationView mBottomNavigationView;

    RedPackedDetailsModel mRedPackedDetailsModel;

    ContactRealm mContactRealm;
    ContactRealm mOtherContactRealm;

    Realm mRealm;
    @BindView(R.id.otherAvatarImage)
    RoundedImageView otherAvatarImage;


    @BindView(R.id.tvSendUserName)
    TextView mTvSendUserName;
    @BindView(R.id.tvSendDesc)
    TextView mTvSendDesc;
    @BindView(R.id.tvRedPacketsInfo)
    TextView mTvRedPacketsInfo;

    @BindView(R.id.tvReceiveUserName)
    AppCompatTextView mTvReceiveUserName;
    @BindView(R.id.tvReceiveTime)
    TextView mTvReceiveTime;
    @BindView(R.id.tvAmount)
    TextView mTvAmount;

    Unbinder unbinder;


    public RedPacketsDetailsFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReceiveRedPacketsFragment.
     */

    public static RedPacketsDetailsFragment newInstance(RedPackedDetailsModel redPackedDetailsModel,
                                                        BottomNavigationView
            bottomNavigationView) {
        RedPacketsDetailsFragment fragment = new RedPacketsDetailsFragment();
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


        mOtherContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getReceivedUserId()).findFirst();

        mTvReceiveUserName.setText(mOtherContactRealm.getUserNick());

        if (mOtherContactRealm.isSystem()) {
            otherAvatarImage.setImageResource(ViewUtils.getDefaultFace()[mOtherContactRealm
                    .getImageIndex()]);
        }

        else if (StringUtils.isNotEmpty(mOtherContactRealm.getImgPath())){
            // 加载本地图片
            File file = new File(mOtherContactRealm.getImgPath());
            Glide.with(getActivity()).load(file).into(otherAvatarImage);
        }

        WebChatMessageRealm messageRealm = mRealm.where(WebChatMessageRealm.class).equalTo(TextConstant
                        .COLUMN_NAME_FOR_ID,
                mRedPackedDetailsModel.getMessageId()).findFirst();

        mTvSendDesc.setText(messageRealm.getMessage());
        mTvAmount.setText(String.format("%s 元", MathUtils.toString(new BigDecimal(messageRealm.getAmount().toString()
        ))));

        mTvRedPacketsInfo.setText(String.format("1个红包共%s元",MathUtils.toString(new BigDecimal(messageRealm.getAmount().toString()
        ))));
        mTvReceiveTime.setText(TimeUtils.millis2String(messageRealm.getSendTime(), TimeUtils.DEFAULT_PATTERN_4));



    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_details_red_packets;
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
