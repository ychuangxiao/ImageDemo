package com.banditcat.app.views.activitys;

import android.support.design.widget.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;
import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseActivity;

public class DemoActivity extends BaseActivity {
    @BindView(R.id.fab)
    FloatingActionButton fab;

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

    @OnClick(R.id.fab)
    void onFabClick()
    {

    }
}
