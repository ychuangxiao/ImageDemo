package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.views.base.BaseActivity;

import butterknife.OnClick;

public class ContactDetailActivity extends BaseActivity {


    private String userId;

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


        userId = extras_.getString(AppConstant.EXTRA_NO);


        if (StringUtils.isEmpty(userId)) {
            finish();
            return;
        }


    }

    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_contact_detail)).setDisplayHome(true)
                .setHomeOnClickListener();

        injectExtras();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contact_detail;
    }

    @OnClick(R.id.btnHandle)
    void onHandleClick() {
        Intent intent = new Intent(this, WeChatMessageActivity.class);


        intent.putExtra(AppConstant.EXTRA_NO, userId);

        navigateActivity(intent);
    }

}
