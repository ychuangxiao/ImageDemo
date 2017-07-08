package com.banditcat.app.views.activitys.google;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;

import butterknife.BindView;
import butterknife.OnClick;
import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseActivity;
import com.banditcat.app.constant.AppConstant;
import com.banditcat.app.model.AliPaymentModel;
import com.banditcat.app.model.BankModel;


public class ChangeReceiptActivity extends BaseActivity {

    @BindView(R.id.tvBank)
    AppCompatTextView tvBank;

    @BindView(R.id.etUserName)
    AppCompatEditText etUserName;

    @BindView(R.id.etBankCard)
    AppCompatEditText etBankCard;

    AliPaymentModel mAliPaymentModel;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_change_receipt)).setDisplayHome(true).setHomeOnClickListener();

        injectExtras();
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_receipt;
    }

    @OnClick(R.id.tvBank)
    void onBankClick() {
        Intent intent = new Intent(this, BankActivity.class);
        intent.putExtra(AppConstant.EXTRA_NO, mAliPaymentModel.getBankModel().getType());

        navigateActivity(intent, 1000);
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        Intent intent = new Intent();
        intent.putExtra(AppConstant.EXTRA_NO, mAliPaymentModel);


        mAliPaymentModel.setReceiptUserName(etUserName.getText().toString());
        mAliPaymentModel.setBankNo(etBankCard.getText().toString());

        setResult(2000, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (resultCode) {
            case 1000: /* 取得数据，并显示于画面上 */
                Bundle bunde = data.getExtras();
                BankModel bankModel = (BankModel) bunde.getSerializable(AppConstant.EXTRA_NO);
                tvBank.setText(bankModel.getBankName());
                mAliPaymentModel.getBankModel().setBankName(bankModel.getBankName());
                mAliPaymentModel.getBankModel().setType(bankModel.getType());
                break;
            default:
                break;
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

        mAliPaymentModel = (AliPaymentModel) extras_.getSerializable(AppConstant.EXTRA_NO);


        if (mAliPaymentModel == null) {
            finish();
            return;
        }
        etBankCard.setText(mAliPaymentModel.getBankNo());
        etUserName.setText(mAliPaymentModel.getReceiptUserName());
        tvBank.setText(mAliPaymentModel.getBankModel().getBankName());

        etUserName.setSelection(etUserName.length());
    }
}
