package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.RedPackedModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.widget.ClearEditText;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;

import java.io.File;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class TransferActivity extends BaseActivity {


    RedPackedModel mRedPackedModel;
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.tvUserNick)
    AppCompatTextView mTvUserNick;
    @BindView(R.id.tvTransferAmount)
    ClearEditText mEtTransferAmount;
    @BindView(R.id.tvRemark)
    AppCompatTextView mTvRemark;
    @BindView(R.id.tvHandleRemark)
    AppCompatTextView mTvHandleRemark;
    @BindView(R.id.watermarkImageView)
    AppCompatImageView watermarkImageView;

    Realm mRealm;


    ContactRealm mContactRealm;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        injectExtras();


        if (getApplicationComponent().context()
                .sharedpreferences.Watermark().get()) {


            watermarkImageView.setVisibility(View.VISIBLE);
        } else {
            watermarkImageView.setVisibility(View.GONE);
        }


        mRealm = Realm.getDefaultInstance();


        setToolTitle(getString(R.string.title_activity_transfer)).setDisplayHome(true)
                .setHomeOnClickListener();

        mContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedModel.getReceiveUserId()).findFirst();

        mTvUserNick.setText(mContactRealm.getUserNick());

        if (mContactRealm.isSystem()) {
            mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        }
        else if (StringUtils.isNotEmpty(mContactRealm.getImgPath())){
            // 加载本地图片
            File file = new File(mContactRealm.getImgPath());
            Glide.with(this).load(file).into(mHeaderImage);
        }
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_transfer;
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


        mRedPackedModel = extras_.getParcelable(AppConstant.EXTRA_NO);


        if (mRedPackedModel == null) {
            finish();
            return;
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }


    @OnClick(R.id.btnHandle)
    void onHandleClick() {


        if (!validViewEmpty(mEtTransferAmount, "金额至少为0.01元")) {
            return;
        }

        if (MathUtils.compareTo(new BigDecimal(mEtTransferAmount.getText().toString().trim()), new BigDecimal(99999999)) > 0) {
            alertMsg("金额不能大于99999999元");
            return;
        }

        if (MathUtils.compareTo(new BigDecimal(mEtTransferAmount.getText().toString().trim()), new BigDecimal(0.01)) < 0) {
            alertMsg("金额至少为0.01元");
            return;
        }


        mRedPackedModel.setAmount(new BigDecimal(mEtTransferAmount.getText().toString()));
        mRedPackedModel.setContent(mTvRemark.getText().toString());

        Intent intent = new Intent();

        intent.putExtra(AppConstant.EXTRA_NO, mRedPackedModel);

        setResult(mRedPackedModel.getSendType(), intent);
        finish();
    }

}
