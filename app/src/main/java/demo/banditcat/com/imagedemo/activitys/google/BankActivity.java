package demo.banditcat.com.imagedemo.activitys.google;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import demo.banditcat.com.imagedemo.R;
import demo.banditcat.com.imagedemo.adapters.BankAdapter;
import demo.banditcat.com.imagedemo.base.BaseActivity;
import demo.banditcat.com.imagedemo.constant.AppConstant;
import demo.banditcat.com.imagedemo.listeners.RecyclerClickListener;
import demo.banditcat.com.imagedemo.model.BankModel;

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
