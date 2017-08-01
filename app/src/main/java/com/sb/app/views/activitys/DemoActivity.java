package com.sb.app.views.activitys;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import com.sb.app.R;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DemoActivity extends BaseActivity {


    @BindView(R.id.etPx)
    AppCompatEditText mEtPx;
    @BindView(R.id.etSp)
    AppCompatEditText mEtSp;
    @BindView(R.id.etDp)
    AppCompatEditText mEtDp;
    @BindView(R.id.etDip)
    AppCompatEditText mEtDip;
    @BindView(R.id.btnHandle)
    AppCompatButton mBtnHandle;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_demo;
    }



    @OnClick(R.id.btnHandle)
    void onHandleClick()
    {
        mEtSp.setText(String.valueOf(ViewUtils.px2sp(this,Float.parseFloat(mEtPx.getText().toString()))));
        mEtDip.setText(String.valueOf(ViewUtils.px2dip(this,Float.parseFloat(mEtPx.getText().toString()))));
        mEtDp.setText(String.valueOf(ViewUtils.px2dip(this,Float.parseFloat(mEtPx.getText().toString()))));
    }
}
