package com.sb.app.views.activitys.tencent;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.RedPackedDetailsModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.math.BigDecimal;

import butterknife.BindView;
import io.realm.Realm;

public class RedPacketsDetailActivity extends BaseActivity {

    @BindView(R.id.avatarImage)
    AppCompatImageView mAvatarImage;
    @BindView(R.id.tvSendUserName)
    TextView mTvSendUserName;
    @BindView(R.id.tvSendDesc)
    TextView mTvSendDesc;
    @BindView(R.id.tvRedPacketsInfo)
    TextView mTvRedPacketsInfo;
    @BindView(R.id.imgReceiveUser)
    AppCompatImageView mImgReceiveUser;
    @BindView(R.id.tvReceiveUserName)
    AppCompatTextView mTvReceiveUserName;
    @BindView(R.id.tvReceiveTime)
    TextView mTvReceiveTime;
    @BindView(R.id.tvAmount)
    TextView mTvAmount;
    RedPackedDetailsModel mRedPackedDetailsModel;

    Realm mRealm;


    ContactRealm mContactRealm;
    ContactRealm mOtherContactRealm;

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


        mRedPackedDetailsModel = extras_.getParcelable(AppConstant.EXTRA_NO);


        if (mRedPackedDetailsModel == null) {
            finish();
            return;
        }


    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_red_packets_detail)).setDisplayHome(true)
                .setHomeOnClickListener();

        injectExtras();
        mRealm = Realm.getDefaultInstance();


        mContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getSendUserId()).findFirst();

        mTvSendUserName.setText(mContactRealm.getUserNick());

        if (mContactRealm.isSystem()) {
            mAvatarImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        }

        mOtherContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getReceivedUserId()).findFirst();

        mTvReceiveUserName.setText(mOtherContactRealm.getUserNick());

        if (mOtherContactRealm.isSystem()) {
            mImgReceiveUser.setImageResource(ViewUtils.getDefaultFace()[mOtherContactRealm
                    .getImageIndex()]);
        }

        WebChatMessageRealm messageRealm = mRealm.where(WebChatMessageRealm.class).equalTo(TextConstant
                        .COLUMN_NAME_FOR_ID,
                mRedPackedDetailsModel.getMessageId()).findFirst();


        mTvAmount.setText(String.format("%s 元", MathUtils.toString(new BigDecimal(messageRealm.getAmount().toString()
        ))));

        mTvRedPacketsInfo.setText(String.format("1个红包共%s元",MathUtils.toString(new BigDecimal(messageRealm.getAmount().toString()
        ))));
        mTvReceiveTime.setText(TimeUtils.millis2String(messageRealm.getSendTime(), TimeUtils.DEFAULT_PATTERN_4));
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_red_packets_detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

}
