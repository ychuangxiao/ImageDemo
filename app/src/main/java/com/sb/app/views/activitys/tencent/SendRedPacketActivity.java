package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.RedPackedModel;
import com.sb.app.views.base.BaseActivity;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendRedPacketActivity extends BaseActivity {
    RedPackedModel mRedPackedModel;
    @BindView(R.id.etAmount)
    AppCompatEditText mEtAmount;
    @BindView(R.id.etContent)
    AppCompatEditText mEtContent;
    @BindView(R.id.btnHandle)
    AppCompatButton mBtnHandle;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        injectExtras();

        setToolTitle(getString(R.string.title_activity_send_red_packet)).setDisplayHome(true)
                .setHomeOnClickListener();
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_send_red_packet;
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

    @OnClick(R.id.btnHandle)
    void onHandleClick() {


        mRedPackedModel.setAmount(new BigDecimal(mEtAmount.getText().toString()));
        mRedPackedModel.setContent(StringUtils.isEmpty(mEtContent.getText().toString()) ? "恭喜发财，大吉大利" : mEtContent
                .getText().toString());

        Intent intent = new Intent();

        intent.putExtra(AppConstant.EXTRA_NO, mRedPackedModel);

        setResult(mRedPackedModel.getSendType(), intent);
        finish();
    }

}
