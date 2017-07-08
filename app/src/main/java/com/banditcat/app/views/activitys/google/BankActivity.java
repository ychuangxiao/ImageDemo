package com.banditcat.app.views.activitys.google;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import com.banditcat.app.R;
import com.banditcat.app.views.adapters.BankAdapter;
import com.banditcat.app.views.base.BaseActivity;
import com.banditcat.app.constant.AppConstant;
import com.banditcat.app.views.listeners.RecyclerClickListener;
import com.banditcat.app.model.BankModel;

public class BankActivity extends BaseActivity implements RecyclerClickListener<BankModel> {
    @BindView(R.id.recycle_list)
    RecyclerView mRecyclerView;//列表控件


    BankAdapter mBankAdapter;

    Integer mBankType;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_bank)).setDisplayHome(true).setHomeOnClickListener();

        injectExtras();


        mBaseRecyclerView = mRecyclerView;

        mBankAdapter = new BankAdapter(this, mBankType);

        initLinearLayoutRecyclerView(new LinearLayoutManager(this)).setAdapter(mBankAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.bg_decoration_bottom));
        mBaseRecyclerView.addItemDecoration(dividerItemDecoration);
        mBankAdapter.setRecyclerClickListener(this);


        mBankAdapter.setItems(initBankInfo());


    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_bank;
    }

    @Override
    public void onItemClickListener(BankModel model) {


        Intent intent = new Intent();
        intent.putExtra(AppConstant.EXTRA_NO, model);

        setResult(1000, intent);
        finish();
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

        mBankType = extras_.getInt(AppConstant.EXTRA_NO);


        if (mBankType <= 0) {
            finish();
        }

    }
}
